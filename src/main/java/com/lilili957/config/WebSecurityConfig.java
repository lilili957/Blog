package com.lilili957.config;

import com.lilili957.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lilili957 on 2020/07/05
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

//	@Autowired
//	public WebSecurityConfig(UserService userService) {
//		this.userService = userService;
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin/category/all").authenticated()
				.antMatchers("/admin/**", "register").hasRole("超级管理员") // /admin/**的URL都需要有超级管理员角色，如果使用hasAuthority方法来配置，需要在参数中加上ROLE_，如下 .hasAuthority("ROLE_超级管理员")
				.anyRequest().authenticated() //其他的路径都是登陆后即可访问
				.and().formLogin().loginPage("/login_page").successHandler(new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
				httpServletResponse.setContentType("application/json; charset=utf-8");
				PrintWriter out = httpServletResponse.getWriter();
				out.write("{\"status\":\"success\",\"msg\":\"登录成功\"}");  //这里要用转义后的”双引号， 使用单引号的话前端收到的数据格式不对
				out.flush();
				out.close();
			}
		})
				.failureHandler(new AuthenticationFailureHandler() {
					@Override
					public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
						httpServletResponse.setContentType("application/json; charset=utf-8");
						PrintWriter out = httpServletResponse.getWriter();
						out.write("{\"status\": \"error\", \"msg\": \"登录失败\"}");
						out.flush();
						out.close();
					}
				}).loginProcessingUrl("/login")
				.usernameParameter("username").passwordParameter("password").permitAll()
				.and().logout().permitAll().and().csrf().disable().exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/blogimg/**", "/index.html", "/static/**");
	}

	@Bean
	public AccessDeniedHandler getAccessDeniedHandler(){
		return new AuthenticationAccessDeniedHandler();
	}
}

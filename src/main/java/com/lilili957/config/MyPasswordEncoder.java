package com.lilili957.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * Created by lilili957 on 2020/07/04
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword){
		return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword){
		return encodedPassword.equals(DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes()));
	}

}

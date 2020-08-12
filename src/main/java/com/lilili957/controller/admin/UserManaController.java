package com.lilili957.controller.admin;

import com.lilili957.bean.RespBean;
import com.lilili957.bean.Role;
import com.lilili957.bean.User;
import com.lilili957.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilili957 on 2020/07/08
 */
@RestController
@RequestMapping("/admin")
public class UserManaController {

	private final UserService userService;

	@Autowired
	public UserManaController(UserService userService){
		this.userService = userService;
	}

	@GetMapping("/user")
	public List<User> getUserByNickname(String nickname){
		return userService.getUserByNickname(nickname);
	}

	@GetMapping("/user/all")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}

	@GetMapping("/user/{id}")
	public User getUserByUserId(@PathVariable("id") Long id){
		return userService.getUserById(id);
	}

	@GetMapping("/roles")
	public List<Role> getAllRoles(){
		return userService.getAllRoles();
	}

	@DeleteMapping("/user/{uid}")
	public RespBean deleteUserById(@PathVariable("uid") Long uid){
		if (userService.deleteUserById(uid) == 1){
			return new RespBean("success", "删除成功！");
		} else {
			return new RespBean("error", "删除失败！");
		}
	}

	@PutMapping("/user/enabled")
	public RespBean updateUserEnabled(Boolean enabled, Long uid){
		if (userService.updateUserEnabled(enabled, uid) == 1){
			return new RespBean("success", "更新成功！");
		} else {
			return new RespBean("error", "更新失败！");
		}
	}


}

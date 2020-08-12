package com.lilili957.utils;

import com.lilili957.bean.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by lilili957 on 2020/07/05
 */
public class Util {
	public static User getCurrentUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
}

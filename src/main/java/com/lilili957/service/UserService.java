package com.lilili957.service;

import com.lilili957.bean.Role;
import com.lilili957.bean.User;
import com.lilili957.mapper.RolesMapper;
import com.lilili957.mapper.UserMapper;
import com.lilili957.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RolesMapper rolesMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

//	@Autowired
//	public UserService(
//
//					   PasswordEncoder passwordEncoder) {
//
//	}

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(name);
        if (user == null) {
            //避免返回null,这里返回一个不含任何对象的User对象，在后期密码验证中一样会失败
            return new User();
        }
        //查询用户的角色信息，并返回存入user中
        List<Role> role = rolesMapper
                .getRolesByUid(user.getId());
        user.setRoles(role);
        return user;
    }

    /**
     * @param user
     * @return 0 表示成功
     * 1表示用户名重复
     * 2表示失败
     */
    public int reg(User user) {
        System.out.print("reg第一行");
        User loadUserByUsername = userMapper.loadUserByUsername(user.getUsername());
        if (loadUserByUsername != null) {
            return 1;
        }
        //插入用户，插入之前先对密码进行加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true); //用户可用
        long result = userMapper.reg(user);
        //配置用户的角色，默认都是普通用户
        String[] roles = new String[]{"2"};
        int i = rolesMapper.addRoles(roles, user.getId());
        boolean b = i == roles.length && result == 1;
        System.out.println("reg最后一行");
        if (b) {
            return 0;
        } else {
            return 2;
        }
    }

    public String getUserEmail() {
        return userMapper.getUserEmail(Util.getCurrentUser().getId());
    }

    public int updateUserEmail(String email) {
        return userMapper.updateUserEmail(email, Util.getCurrentUser().getId());
    }

    public List<User> getUserByNickname(String nickname) {
        return userMapper.getUserByNickname(nickname);
    }

    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    public List<Role> getAllRoles() {
        return userMapper.getAllRole();
    }

    public int updateUserEnabled(Boolean enabled, Long uid) {
        return userMapper.updateUserEnabled(enabled, uid);
    }

    public int deleteUserById(Long id) {
        return userMapper.deleteUserById(id);
    }

    public int updateUserRoles(Long[] rids, Long id) {
        return updateUserRoles(rids, id);
    }

    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }
}

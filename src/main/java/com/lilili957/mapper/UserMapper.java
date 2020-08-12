package com.lilili957.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.lilili957.bean.Role;
import com.lilili957.bean.User;

import java.util.List;

/**
 * Created by lilili957 2020/07/09
 */
@Mapper
public interface UserMapper {


    User loadUserByUsername(@Param("username") String username);

    long reg(User user);

    String getUserEmail(@Param("id") Long id);

    int updateUserEmail(@Param("email") String email, @Param("id") Long id);

    List<User> getAllUser();

    List<User> getUserByNickname(@Param("nickname") String nickname);

    List<Role> getAllRole();

    int updateUserEnabled(@Param("enabled") Boolean enabled, @Param("uid") Long uid);

    int deleteUserById(Long uid);

    int deleteUserRolesByUid(Long id);

    int setUserRoles(@Param("rids") Long[] rids, @Param("id") Long id);

    User getUserById(@Param("id") Long id);
}

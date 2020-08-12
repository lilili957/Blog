package com.lilili957.mapper;


import com.lilili957.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
//@Repository
public interface RolesMapper {

	int addRoles(@Param("roles") String[] roles, @Param("uid") Long id);

	List<Role> getRolesByUid(Long id);
}

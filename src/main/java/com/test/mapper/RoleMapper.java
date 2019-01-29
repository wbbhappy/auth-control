package com.test.mapper;

import com.test.entity.Role;
import com.test.entity.RolePermission;
import java.util.List;

public interface RoleMapper {
	void addRole(Role role);
	void deleteRole(Long roleId);
	Role findById(Long roleId);
	List<Role> findRolesByUserName(String userName);
	List<Role> findAllRoles();
	void updateRole(Role role);
	void deleteUserRole(Long roleId);
	void deleteRolePermission(Long roleId);
	void addRolePermission(RolePermission rolePermission);
}

package com.test.service;

import com.test.entity.Role;
import java.util.List;

public interface RoleService {
	Long addRole(Role role,Long...permissionIds);
	void deleteRole(Long roleId);
	void deleteMoreRoles(Long...roleIds);
	Role getRoleById(Long roleId);
	List<Role> getRolesByUserName(String userName);
	List<Role> getAllRoles();
	void updateRole(Role role,Long...permIds);
	void addRolePermissions(Long roleId,Long...permissionIds);
}

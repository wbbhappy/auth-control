package com.test.service;

import com.test.entity.Permission;
import java.util.List;

public interface PermissionService {
	Long addPermission(Permission permission);
	void deletePermission(Long permissionId);
	void deleteMorePermissions(Long...permIds);
	Permission findById(Long permId);
	List<Permission> getPermissionsByRoleId(Long roleId);
	List<Permission> getAllPermissions();
	void updatePermission(Permission permission);
}

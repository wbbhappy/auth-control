package com.test.mapper;

import com.test.entity.Permission;
import java.util.List;

public interface PermissionMapper {
	void addPermission(Permission permission);
	void deletePermission(Long permissionId);
	Permission findById(Long permId);
	List<Permission> findNavisByRoleId(Long roleId);
	List<Permission> findPermissionsByRoleId(Long roleId);
	List<Permission> findAllPermissions();
	void updatePermission(Permission permission);
	void deleteRolePermission(Long permissionId);
}

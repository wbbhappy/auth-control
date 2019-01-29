package com.test.service;

import com.test.entity.Role;
import com.test.entity.RolePermission;
import com.test.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper roleMapper;

	public Long addRole(Role role, Long... permissionIds) {
		roleMapper.addRole(role);
		if(permissionIds != null && permissionIds.length>0){
			for(Long permissionId:permissionIds){
				roleMapper.addRolePermission(new RolePermission(role.getRoleId(),permissionId));
			}
		}
		return role.getRoleId();
	}

	public void deleteRole(Long roleId) {
		roleMapper.deleteUserRole(roleId);
		roleMapper.deleteRolePermission(roleId);
		roleMapper.deleteRole(roleId);
	}

	public void deleteMoreRoles(Long... roleIds) {
		if(roleIds != null && roleIds.length>0){
			for(Long roleId:roleIds){
				deleteRole(roleId);
			}
		}
	}

	public Role getRoleById(Long roleId) {
		return roleMapper.findById(roleId);
	}

	public List<Role> getRolesByUserName(String userName) {
		return roleMapper.findRolesByUserName(userName);
	}

	public List<Role> getAllRoles() {
		return roleMapper.findAllRoles();
	}

	public void updateRole(Role role,Long...permIds) {
		roleMapper.updateRole(role);
		roleMapper.deleteRolePermission(role.getRoleId());
		addRolePermissions(role.getRoleId(),permIds);
	}

	public void addRolePermissions(Long roleId, Long... permissionIds) {
		if(permissionIds != null && permissionIds.length>0){
			for(Long permissionId:permissionIds){
				roleMapper.addRolePermission(new RolePermission(roleId,permissionId));
			}
		}
	}
}

package com.test.service;

import com.test.entity.Navigation;
import com.test.entity.Role;
import com.test.entity.User;
import com.test.entity.UserRole;
import com.test.mapper.PermissionMapper;
import com.test.mapper.RoleMapper;
import com.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private PasswordService passwordService;

	public void addUser(User user, Long... roleIds) {
		passwordService.encryptPassword(user);
		userMapper.addUser(user);
		if(roleIds != null && roleIds.length>0){
			for(Long roleId:roleIds){
				userMapper.addUserRole(new UserRole(user.getUserId(),roleId));
			}
		}
	}

	public void deleteUser(Long userId) {
		userMapper.deleteUserRole(userId);
		userMapper.deleteUser(userId);
	}

	public void deleteMoreUsers(Long... userIds) {
		if(userIds != null && userIds.length>0){
			for(Long userId:userIds){
				deleteUser(userId);
			}
		}
	}

	public User getUserByUserName(String userName) {
		return userMapper.findUserByUserName(userName);
	}

	public List<User> getAllUsers() {
		return userMapper.findAllUsers();
	}

	public void updateUserRoles(Long userId, Long... roleIds) {
		userMapper.deleteUserRole(userId);
		if(roleIds != null && roleIds.length>0){
			for(Long roleId:roleIds){
				userMapper.addUserRole(new UserRole(userId,roleId));
			}
		}
	}

	public Set<String> findRolesByUserName(String userName) {
		return new HashSet<String>(userMapper.findRolesByUserName(userName));
	}

	public Set<String> findPermissionsByUserName(String userName) {
		return new HashSet<String>(userMapper.findPermissionsByUserName(userName));
	}

	public List<Navigation> getNavigationBar(String userName) {
		List<Navigation> navigationBar = new ArrayList<Navigation>();
		Navigation navigation;
		List<Role> roles = roleMapper.findRolesByUserName(userName);
		for(Role role:roles){
			navigation = new Navigation();
			navigation.setNavigationName(role.getRoleDesc());
			navigation.setChildNavigations(permissionMapper.findNavisByRoleId(role.getRoleId()));
			navigationBar.add(navigation);
		}
		return navigationBar;
	}
}

package com.test.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.test.entity.User;
import com.test.service.UserService;
/**
 * 扩展AuthorizingRealm:用于从数据库抓取密码等相关验证信息和相关权限信息
 */
public class UserRealm extends AuthorizingRealm{
	@Autowired
	private UserService userService;

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String)principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findRolesByUserName(userName));
		authorizationInfo.setStringPermissions(userService.findPermissionsByUserName(userName));
		
		return authorizationInfo;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal();
		User user = userService.getUserByUserName(userName);
		if(user == null){
			throw new UnknownAccountException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUserName(),
				user.getPassword(),
				ByteSource.Util.bytes(user.getUserName() + user.getPasswordSalt()),
				getName());
		return authenticationInfo;
	}
}

package com.test.shiro.filter;

import com.test.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class WithNavibarFormAuthenticationFilter extends FormAuthenticationFilter {
	@Autowired
	private UserService userService;

	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		HttpServletRequest httpReq = (HttpServletRequest)request;
		
		String userName = (String)SecurityUtils.getSubject().getPrincipal();
		List navigationBar = userService.getNavigationBar(userName);
		httpReq.getSession().setAttribute("navibar", navigationBar);
		return super.onLoginSuccess(token, subject, request, response);
	}
}

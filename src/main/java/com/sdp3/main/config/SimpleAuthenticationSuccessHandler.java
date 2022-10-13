package com.sdp3.main.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	
		@Override
		public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
				Authentication authentication) throws IOException, ServletException {
			 Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

		        if (roles.contains("ROLE_USER")) {
		            httpServletResponse.sendRedirect("/student/dashboard");
		        } else if(roles.contains("ROLE_TEACHER")){
		            httpServletResponse.sendRedirect("/teacher/dashboard");
		        }else{
					httpServletResponse.sendRedirect("/admin/dashboard");
				}
			
		}
		
}

package com.xabe.jersey.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component("customUserAuthenticationProvider")
/**
 * Clase que representa el autentication provider el encargado de comprobar que el usuario sea el corrector y asignarle los roles correspondientes
 * @author 47518418G
 *
 */
public class CustomUserAuthenticationProvider implements AuthenticationProvider{
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserAuthenticationProvider.class);
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		LOGGER.info("Se intenta logear el usuario : " + authentication.getPrincipal());
		
		if(authentication.getPrincipal().equals("user")&& authentication.getCredentials().equals("user"))
		{
			
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ANDROID"));
			CustomUserPasswordAuthenticationToken auth=new CustomUserPasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(),grantedAuthorities);
			return auth;
		
		}
		else if(authentication.getPrincipal().equals("admin")&& authentication.getCredentials().equals("admin"))
		{
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			CustomUserPasswordAuthenticationToken auth=new CustomUserPasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(),grantedAuthorities);
			
			return auth;
		}
		else if(authentication.getPrincipal().equals("user1")&& authentication.getCredentials().equals("user1"))
		{
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_WEB"));
			CustomUserPasswordAuthenticationToken auth=new CustomUserPasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(),grantedAuthorities);
			
			return auth;
		}
		else
		{
			LOGGER.error("el usuario : " + authentication.getPrincipal() + " no esta registrado");
			throw new BadCredentialsException("Bad User Credentials.");
		}
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return true;
	}


}


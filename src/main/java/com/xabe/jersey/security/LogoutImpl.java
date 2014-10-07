package com.xabe.jersey.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component("logoutSuccessHandler")
/**
 * Clase que se encarga de elimnar el token del almacen de token's
 * @author Chabir Atrahouch
 *
 */
public class LogoutImpl implements LogoutSuccessHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserPasswordAuthenticationToken.class);
	@Autowired
	private TokenStore tokenstore;

	@Override
	public void onLogoutSuccess(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse,
			Authentication paramAuthentication) throws IOException,
			ServletException {
		removeaccess(paramHttpServletRequest);
		paramHttpServletResponse.getOutputStream().write("Se ha deslogeado correctamente".getBytes());
	}

	public void removeaccess(HttpServletRequest req) {

		String tokens = req.getHeader("Authorization");
		LOGGER.info(tokens);
		String value = tokens.substring(tokens.indexOf(" ")).trim();
		DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(value);
		LOGGER.info(token.toString());
		((InMemoryTokenStore)tokenstore).removeAccessToken(value);
		LOGGER.info("\n\tToken eliminado correctamente!!!!!!!!");
	}

}

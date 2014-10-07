package com.xabe.jersey.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

/**
 * Clase que se encarga de buscar los clientId's y buscar su contraseña y darle los permisos
 * @author Chabir Atrahouch
 *
 */
@Service("clientDetails")
public class ClientDetailsServiceImpl implements ClientDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientDetailsServiceImpl.class);

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws OAuth2Exception {

		if (clientId.equals("client1")) {
			
			List<String> authorizedGrantTypes=new ArrayList<>();
			authorizedGrantTypes.add("password");
			authorizedGrantTypes.add("refresh_token");
			authorizedGrantTypes.add("client_credentials");
		
			BaseClientDetails clientDetails = new BaseClientDetails();
			clientDetails.setClientId("client1");
			clientDetails.setClientSecret("client1");
			//Necesario para las version 2.0 de spring oauth
			List<String> scopeTypes = new ArrayList<>();
			scopeTypes.add("read");
			scopeTypes.add("write");
			clientDetails.setScope(scopeTypes);

			
			clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
			LOGGER.info("Accede el client1");
			return clientDetails;
			
		} else if(clientId.equals("client2")){
			
			List<String> authorizedGrantTypes=new ArrayList<>();
			authorizedGrantTypes.add("password");
			authorizedGrantTypes.add("refresh_token");
			authorizedGrantTypes.add("client_credentials");
			
			
			BaseClientDetails clientDetails = new BaseClientDetails();
			clientDetails.setClientId("client2");
			clientDetails.setClientSecret("client2");
			//Necesario para las version 2.0 de spring oauth
			List<String> scopeTypes = new ArrayList<>();
			scopeTypes.add("read");
			scopeTypes.add("write");
			clientDetails.setScope(scopeTypes);

			
			clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
			LOGGER.info("Accede el client2");
			return clientDetails;
		}
		
		
		else{
			LOGGER.error("No se encuentra ningun client");
			throw new NoSuchClientException("No client with requested id: "
					+ clientId);
		}
	}

}


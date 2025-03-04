package com.ravi.authentication;

import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Logger;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
	private static Logger logger = Logger.getLogger(AuthenticationFilter.class.getName());

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		logger.info("Headers :" + authHeader);
		if (authHeader == null || !authHeader.startsWith("Basic ")) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			return;
		}

		String base64Credentials = authHeader.substring("Basic ".length()).trim();
		System.out.println("base64Credentials:" + base64Credentials);
		logger.info("base64Credentials:" + base64Credentials);
		String credentials = new String(Base64.getDecoder().decode(base64Credentials));
		System.out.println("credentials:" + credentials);
		String[] values = credentials.split(":", 2);

		if (!"admin".equals(values[0]) || !"password".equals(values[1])) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}
}

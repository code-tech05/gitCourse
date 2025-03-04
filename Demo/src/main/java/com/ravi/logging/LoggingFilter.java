package com.ravi.logging;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class LoggingFilter implements ContainerRequestFilter {
	private static final Logger logger = Logger.getLogger(LoggingFilter.class.getName());

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.info(
				"Request Received: " + requestContext.getMethod() + " " + requestContext.getUriInfo().getRequestUri());

		for (Map.Entry<String, java.util.List<String>> entry : requestContext.getHeaders().entrySet()) {

			logger.info(entry.getKey() + ": " + entry.getValue());

		}
	}
}

package com.xabe.jersey.exception.impl;

import com.xabe.jersey.exception.BaseWebApplicationException;

/**
 * Clase que se implementa la excepcion cuando se producce un error en al aplicacion
 * @author Chabir Atrahouch
 *
 */
public class ApplicationRuntimeException extends BaseWebApplicationException {

	private static final long serialVersionUID = 1L;

	public ApplicationRuntimeException(String applicationMessage) {
		super(500, "Internal System error", applicationMessage);
	}
}
package br.com.beibe.exception;

import javax.servlet.ServletException;

public class InvalidAccessRoleException extends ServletException {

    public InvalidAccessRoleException(String message) {
        super(message);
    }
}

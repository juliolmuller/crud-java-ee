package br.com.beibe.beans.exception;

import javax.servlet.ServletException;

public class InvalidAccessRoleException extends ServletException {

    public InvalidAccessRoleException(String message) {
        super(message);
    }
}

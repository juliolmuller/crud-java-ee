package br.com.beibe.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public final class ValError implements Serializable {

    private String field;
    private String message;

    public ValError() {}

    public ValError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


package br.ufpr.tads.web2.beans;

import java.io.Serializable;

public class ConfigBean implements Serializable {
    
    private String emailAdmin;

    public String getEmailAdmin() {
        return this.emailAdmin;
    }

    public void setEmailAdmin(String email) {
        this.emailAdmin = email;
    }
}

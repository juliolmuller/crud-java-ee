package br.com.beibe.beans;

import java.time.LocalDate;

@SuppressWarnings("serial")
public final class Cliente extends User {

    public Cliente() {
        super();
    }

    public Cliente(Long id, String firstName, String lastName, String cpf, String email, LocalDate dateBirth, String phone, Address address, String password) {
        super(id, firstName, lastName, cpf, email, dateBirth, phone, address, password);
    }
}

package br.com.beibe.beans;

import java.util.List;
import java.util.ArrayList;
import br.com.beibe.dao.StateDAO;
import br.com.beibe.utils.Converter;
import br.com.beibe.utils.Validator;

@SuppressWarnings("serial")
public final class Address implements Bean {

    private Long id;
    private String zipCode;
    private String street;
    private Integer number;
    private String complement;
    private String neightborhood;
    private String city;
    private State state;

    public Address() {}

    public Address(Long userId, String zipCode, String street, Integer number, String complement, String neightborhood, String city, State state) {
        setId(userId);
        setZipCode(zipCode);
        setStreet(street);
        setNumber(number);
        setComplement(complement);
        setNeightborhood(neightborhood);
        setCity(city);
        setState(state);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = Converter.removeNonDigits(zipCode);
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = Converter.nullable(street);
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return this.complement;
    }

    public void setComplement(String complement) {
        this.complement = Converter.nullable(complement);
    }

    public String getNeightborhood() {
        return this.neightborhood;
    }

    public void setNeightborhood(String neightborhood) {
        this.neightborhood = Converter.nullable(neightborhood);
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = Converter.nullable(city);
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public List<ValError> validate() {
        List<ValError> errors = new ArrayList<>();

        // Validar CEP
        if (this.zipCode != null) {
            if (!Validator.isCep(this.zipCode)) {
                errors.add(new ValError("zip_code", "O CEP fornecido é inválido"));
            }
        }

        // Validar rua
        if (this.street != null) {
            if (this.street.length() > 255) {
                errors.add(new ValError("street", "O campo 'RUA' deve ter até 255 caracteres"));
            }
        }

        // Validar complemento
        if (this.complement != null) {
            if (this.complement.length() > 30) {
                errors.add(new ValError("complement", "O campo 'COMPLEMENTO' deve ter até 30 caracteres"));
            }
        }

        // Validar bairro
        if (this.neightborhood != null) {
            if (this.neightborhood.length() > 80) {
                errors.add(new ValError("neightborhood", "O campo 'BAIRRO' deve ter até 80 caracteres"));
            }
        }

        // Validar cidade
        if (this.city != null) {
            if (this.city.length() > 80) {
                errors.add(new ValError("city", "O campo 'CIDADE' deve ter até 80 caracteres"));
            }
        }

        // Validar estado
        if (this.state != null && this.state.getId() != null) {
            if (StateDAO.find(this.state.getId()) == null) {
                errors.add(new ValError("state", "O estado selecionado é inválido"));
            }
        }

        return errors;
    }
}

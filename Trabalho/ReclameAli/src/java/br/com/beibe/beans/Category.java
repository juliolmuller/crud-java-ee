package br.com.beibe.beans;

import br.com.beibe.utils.Converter;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("serial")
public final class Category implements Bean {

    private Long id;
    private String name;

    public Category() {}

    public Category(Long id, String name) {
        setId(id);
        setName(name);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = Converter.nullable(name);
    }

    @Override
    public List<ValError> validate() {
        List<ValError> errors = new ArrayList<>();
        
        // Validar nome da categoria
        if (this.name == null) {
            errors.add(new ValError("name", "O campo 'NOME' é de preenchimento obrigatório"));
        } else if (this.name.length() > 255) {
            errors.add(new ValError("name", "O campo 'NOME' deve ter no máximo 255 caracteres"));
        }
        
        return errors;
    }
}

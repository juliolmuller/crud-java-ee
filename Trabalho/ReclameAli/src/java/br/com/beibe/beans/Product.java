package br.com.beibe.beans;

import java.util.List;
import java.util.ArrayList;
import br.com.beibe.dao.CategoryDAO;
import br.com.beibe.dao.ProductDAO;
import br.com.beibe.utils.Converter;

@SuppressWarnings("serial")
public final class Product implements Bean {

    private Long id;
    private String name;
    private String description;
    private Double weight;
    private String utc;
    private String ean;
    private Category category;

    public Product() {}

    public Product(Long id, String name, String description, Double weight, String utc, String ean, Category category) {
        setId(id);
        setName(name);
        setDescription(description);
        setWeight(weight);
        setUtc(utc);
        setEan(ean);
        setCategory(category);
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = Converter.nullable(description);
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getUtc() {
        return this.utc;
    }

    public void setUtc(String utc) {
        this.utc = Converter.nullable(Converter.removeNonDigits(utc));
    }

    public String getEan() {
        return this.ean;
    }

    public void setEan(String ean) {
        this.ean = Converter.nullable(Converter.removeNonDigits(ean));
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public List<ValError> validate() {
        List<ValError> errors = new ArrayList<>();

        // Validar atributos requeridos
        if (this.name == null)
            errors.add(new ValError("name", "O campo 'NOME' é de preenchimento obrigatório"));
        if (this.utc == null)
            errors.add(new ValError("utc_code", "O campo 'CÓDIGO UTC' é de preenchimento obrigatório"));
        if (this.category == null)
            errors.add(new ValError("category", "O campo 'CATEGORIA' é obrigatório"));

        // Validar nome do produto
        if (this.name != null) {
            if (this.name.length() < 5 || this.name.length() > 50)
                errors.add(new ValError("name", "O nome do produto deve ter entre 5 e 50 caracteres"));
        }

        // Validar descrição
        if (this.description != null) {
            if (this.description.length() > 255)
                errors.add(new ValError("description", "A descrição do produto deve ter no máximo 255 caracteres"));
        }

        // Validar peso
        if (this.weight != null) {
            if (this.weight < 0)
                errors.add(new ValError("weight", "O peso do produto deve ser um número positivo (em gramas)"));
        }

        // Validar código UTC
        if (this.utc != null) {
            if (this.utc.length() != 12)
                errors.add(new ValError("utc_code", "O código UTC deve ser uma sequência de 12 digitos"));
            else {
                Product product = ProductDAO.find(this.utc, ProductDAO.Fields.UTC);
                if (product != null && (this.id == null || !product.id.equals(this.id)))
                    errors.add(new ValError("utc_code", "O código UTC '" + this.utc + "' já está cadastrado"));
            }
        }

        // Validar código EAN
        if (this.ean != null) {
            if (this.ean.length() != 12)
                errors.add(new ValError("ean_code", "O código EAN deve ser uma sequência de 13 digitos"));
            else {
                Product product = ProductDAO.find(this.ean, ProductDAO.Fields.EAN);
                if (product != null && (this.id == null || !product.id.equals(this.id)))
                    errors.add(new ValError("ean_code", "O código EAN '" + this.ean + "' já está cadastrado"));
            }
        }

        // Validar categoria
        if (this.category != null) {
            if (this.category.getId() == null)
                errors.add(new ValError("ean_code", "O código EAN deve ser uma sequência de 13 digitos"));
            else {
                this.category = CategoryDAO.find(this.category.getId());
                if (this.category == null)
                    errors.add(new ValError("category", "A categoria selecionada não existe"));
            }
        }

        return errors;
    }
}

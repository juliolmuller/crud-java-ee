package br.ufpr.tads.web2.beans;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import br.ufpr.tads.web2.dao.ClienteDAO;
import br.ufpr.tads.web2.utils.Converter;
import br.ufpr.tads.web2.utils.Validator;

public class Cliente {

    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private Date dataNasc;
    private Endereco endereco;

    public Cliente() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf =  (cpf == null || cpf.equals("")) ? null : Converter.removeNonDigits(cpf);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome =  (nome == null || nome.equals("")) ? null : nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email =  (email == null || email.equals("")) ? null : email.toUpperCase();
    }

    public Date getDataNasc() {
        return this.dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public List<Erro> validar() {
        List<Erro> erros = new ArrayList<>();
        Cliente cliente;
        
        // Validar CPF
        if (this.cpf == null) {
            erros.add(new Erro("cpf", "O campo \"CPF\" é obrigatório."));
        } else {
            if (!Validator.isCpf(this.cpf)) {
                erros.add(new Erro("cpf", "O CPF fornecido é inválido."));
            }
            cliente = ClienteDAO.comCpf(this.cpf);
            if (cliente != null && (this.id == null || !cliente.id.equals(this.id))) {
                erros.add(new Erro("cpf", "O CPF \"" + Converter.toCpf(this.cpf) + "\" já está cadastrado."));
            }
        }
        
        // Validar nome
        if (this.nome == null) {
            erros.add(new Erro("nome", "O campo \"NOME\" é obrigatório."));
        } else {
            if (!Validator.isName(this.nome)) {
                erros.add(new Erro("nome", "O campo \"NOME\" contém caracteres inválidos."));
            } else if (this.nome.length() < 2 || this.nome.length() > 100) {
                erros.add(new Erro("nome", "O campo \"NOME\" deve ter entre 2 e 100 caracteres."));
            }
        }
        
        // Validar email
        if (this.email == null) {
            erros.add(new Erro("email", "O campo \"EMAIL\" é obrigatório."));
        } else {
            if (!Validator.isEmail(this.email)) {
                erros.add(new Erro("email", "O email fornecido é inválido."));
            }
            cliente = ClienteDAO.comEmail(this.email);
            if (cliente != null) {
                if (this.id == null || !cliente.id.equals(this.id)) {
                    erros.add(new Erro("email", "O email \"" + this.email + "\" já está cadastrado."));
                }
            }
        }
        
        // Validar data de nascimento
        if (this.dataNasc == null) {
            erros.add(new Erro("nasc", "A data de nascimento fornecida é inválida."));
        }
        
        // VAlidar endereço
        this.endereco.validar().forEach(erro -> erros.add(erro));
        
        return erros;
    }
}

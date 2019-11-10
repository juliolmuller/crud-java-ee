package br.ufpr.tads.web2.beans;

import java.util.List;
import java.util.ArrayList;
import br.ufpr.tads.web2.dao.CidadeDAO;
import br.ufpr.tads.web2.utils.Converter;

public class Endereco {

    private String rua;
    private int numero;
    private String cep;
    private Cidade cidade;

    public Endereco() {}

    public String getRua() {
        return this.rua;
    }

    public void setRua(String rua) {
        this.rua =  (rua == null || rua.equals("")) ? null : rua;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = Math.abs(numero);
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep =  (cep == null || cep.equals("")) ? null : Converter.removeNonDigits(cep);
    }

    public Cidade getCidade() {
        return this.cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    public List<Erro> validar() {
        List<Erro> erros = new ArrayList<>();
        
        // Validar rua
        if (this.rua != null && (this.rua.length() < 5 && this.rua.length() > 100)) {
            erros.add(new Erro("rua", "O campo \"RUA\" deve ter entre 5 e 100 caracteres."));
        }
        
        // Validar cidade/estado
        if (this.cidade != null) {
            List<Cidade> cidades = CidadeDAO.listar();
            System.out.print(this.cidade.getId());
            boolean match = false;
            for (Cidade c : cidades) {
                if (this.cidade.getId().equals(c.getId())) {
                    match = true;
                    break;
                }
            }
            if (!match)
                erros.add(new Erro("cidade", "A cidade ou o estado selecionado não consta no banco de dados."));
        }
        
        return erros;
    }
}

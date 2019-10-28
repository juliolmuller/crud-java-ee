package br.ufpr.tads.web2.facades;

import java.util.List;
import br.ufpr.tads.web2.beans.Cliente;
import br.ufpr.tads.web2.dao.ClienteDAO;
import br.ufpr.tads.web2.exception.ClienteDuplicadoException;

public class ClienteFacade {
    
    private ClienteFacade() {}
    
    public static void inserir(Cliente cliente) throws ClienteDuplicadoException {
        ClienteDAO.inserir(cliente);
    }
    
    public static void alterar(Cliente cliente) throws ClienteDuplicadoException {
        ClienteDAO.atualizar(cliente);
    }
    
    public static Cliente buscar(int id) {
        return ClienteDAO.comId(id);
    }
    
    public static Cliente buscar(String cpf) {
        return ClienteDAO.comCpf(cpf);
    }

    public static List<Cliente> buscarTodos() {
        return ClienteDAO.listar();
    }
    
    public static void remover(int id) {
        Cliente cliente = buscar(id);
        remover(cliente);
    }
    
    public static void remover(Cliente cliente) {
        ClienteDAO.excluir(cliente);
    }
}

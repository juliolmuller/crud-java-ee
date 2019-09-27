package br.ufpr.tads.web2.servlets;

import br.ufpr.tads.web2.beans.Cliente;
import br.ufpr.tads.web2.beans.Endereco;
import br.ufpr.tads.web2.dao.ClienteDAO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AlterarCliente", urlPatterns = {"/clientes-alterar"})
public class AlterarClienteServlet extends HttpServlet {

    // Validar se usuário está logado
    private boolean validarRequest(
        HttpServletRequest request
    ) throws IOException {
        if (request.getSession().getAttribute("login") != null) {
            request.setAttribute("msg", "Faça-me o favor de logar antes!");
            request.setAttribute("cor", "danger");
            return false;
        }
        return true;
    }
    
    // Retornar o formulário de edição do cliente
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        
        // Validar se usuário está logado
        if (!validarRequest(request)) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        // Verifica se há um parâmetro 'id' e se o registro existe
        Cliente cliente = null;
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            cliente = ClienteDAO.comId(id);
            if (cliente == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException | NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/clientes");
        }
        
        // Setar bean em atributo da requisição e redirecionar
        request.setAttribute("cliente", cliente);
        request.getRequestDispatcher("clientesForm.jsp").forward(request, response);
    }

    // Recebe os dados do cliente e salva em banco de dados
    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        
        // Validar se usuário está logado
        if (!validarRequest(request)) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    
        // Salvar parâmetros da requisição em bean
        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        cliente.setId(Integer.parseInt(request.getParameter("id")));
        cliente.setCpf(request.getParameter("cpf"));
        cliente.setNome(request.getParameter("nome"));
        cliente.setEmail(request.getParameter("email"));
        cliente.setDataNasc(new Date(request.getParameter("nasc")));
        endereco.setCep(request.getParameter("cep"));
        endereco.setRua(request.getParameter("rua"));
        endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setUf(request.getParameter("estado"));
        cliente.setEndereco(endereco);

        // Salvar cliente em banco de dados e redirecionar para lista
        ClienteDAO.atualizar(cliente);
        response.sendRedirect(request.getContextPath() + "/clientes");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

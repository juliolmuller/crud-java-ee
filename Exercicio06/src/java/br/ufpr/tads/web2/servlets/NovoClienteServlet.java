package br.ufpr.tads.web2.servlets;

import br.ufpr.tads.web2.beans.Cliente;
import br.ufpr.tads.web2.beans.Endereco;
import br.ufpr.tads.web2.dao.ClienteDAO;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "NovoCliente", urlPatterns = {"/clientes-novo"})
public class NovoClienteServlet extends HttpServlet {

    // Validar se usuário está logado
    private boolean validarRequest(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        if (request.getSession().getAttribute("login") == null) {
            request.setAttribute("msg", "Faça-me o favor de logar antes!");
            request.setAttribute("cor", "danger");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return false;
        }
        return true;
    }

    // Retornar o formulário de cadastro de cliente
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Validar se usuário está logado
        if (!validarRequest(request, response)) {
            return;
        }

        // Redirecionar para formulário de cadastro
        request.getRequestDispatcher("clientesForm.jsp").forward(request, response);
    }

    // Recebe os dados do cliente e salva em banco de dados
    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Validar se usuário está logado
        if (!validarRequest(request, response)) {
            return;
        }

        // Definir encoding dos dados da requisição
        request.setCharacterEncoding("UTF-8");

        // Salvar parâmetros da requisição em bean
        String cpf = request.getParameter("cpf");
        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        cliente.setCpf(cpf);
        cliente.setNome(request.getParameter("nome"));
        cliente.setEmail(request.getParameter("email"));
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            cliente.setDataNasc(formatter.parse(request.getParameter("nasc")));
        } catch (ParseException e) {}
        endereco.setCep(request.getParameter("cep"));
        endereco.setRua(request.getParameter("rua"));
        endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setUf(request.getParameter("estado"));
        cliente.setEndereco(endereco);

        // Validar se CPF já está cadastrado
        if (ClienteDAO.comCpf(cpf) != null) {
            request.setAttribute("cliente", cliente);
            request.setAttribute("erro", "CPF já cadastrado!");
            this.doGet(request, response);
            return;
        }

        // Salvar cliente em banco de dados e redirecionar para lista
        ClienteDAO.inserir(cliente);
        response.sendRedirect("clientes");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

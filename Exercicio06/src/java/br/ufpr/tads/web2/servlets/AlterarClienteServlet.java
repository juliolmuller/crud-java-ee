package br.ufpr.tads.web2.servlets;

import br.ufpr.tads.web2.beans.Cliente;
import br.ufpr.tads.web2.beans.Endereco;
import br.ufpr.tads.web2.facades.ClienteFacade;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AlterarCliente", urlPatterns = {"/clientes/alterar"})
public class AlterarClienteServlet extends HttpServlet {

    // Retornar o formulário de edição do cliente
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Verifica se há um parâmetro 'id' e se o registro existe
        Cliente cliente = null;
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            cliente = ClienteFacade.buscar(id);
            if (cliente == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException | NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/clientes");
        }

        // Setar bean em atributo da requisição e redirecionar
        request.setAttribute("cliente", cliente);
        request.getRequestDispatcher("/jsp/clientesForm.jsp").forward(request, response);
    }

    // Recebe os dados do cliente e salva em banco de dados
    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Definir encoding dos dados da requisição
        request.setCharacterEncoding("UTF-8");

        // Salvar parâmetros da requisição em bean
        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        cliente.setId(Integer.parseInt(request.getParameter("id")));
        cliente.setCpf(request.getParameter("cpf"));
        cliente.setNome(request.getParameter("nome"));
        cliente.setEmail(request.getParameter("email"));
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            cliente.setDataNasc(formatter.parse(request.getParameter("nasc")));
        } catch (ParseException ex) {}
        endereco.setCep(request.getParameter("cep"));
        endereco.setRua(request.getParameter("rua"));
        endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setUf(request.getParameter("estado"));
        cliente.setEndereco(endereco);

        // Salvar cliente em banco de dados e redirecionar para lista
        ClienteFacade.alterar(cliente);
        response.sendRedirect(request.getContextPath() + "/clientes");
    }
}

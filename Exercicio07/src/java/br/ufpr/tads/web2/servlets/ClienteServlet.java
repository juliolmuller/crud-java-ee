package br.ufpr.tads.web2.servlets;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import br.ufpr.tads.web2.beans.Cliente;
import br.ufpr.tads.web2.beans.Cidade;
import br.ufpr.tads.web2.beans.Endereco;
import br.ufpr.tads.web2.beans.Erro;
import br.ufpr.tads.web2.beans.Estado;
import br.ufpr.tads.web2.facades.Facade;
import br.ufpr.tads.web2.utils.Converter;

@WebServlet(name = "Clientes", urlPatterns = {"/clientes/*"})
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Salvar URI's em vetor
        String[] uri = request.getRequestURI().split("/");
        
        // Se rota não possuir parâmetros, retornar lista
        if (uri.length == 3) {
            List<Cliente> clientes = Facade.buscarTodos();
            request.setAttribute("clientes", clientes);
            request.getRequestDispatcher("/jsp/clientesListar.jsp").forward(request, response);
            return;
        }
        
        // Se não, avaliar parâmetro e preparar resposta adequada
        Long id = null;
        List<Estado> estados;
        List<Cidade> cidades;
        Cliente cliente;
        switch (uri[3]) {
            
            // Redirecionar para formulário de cadastro
            case "novo": 
                estados = Facade.buscarEstados();
                request.setAttribute("estados", estados);
                request.getRequestDispatcher("/jsp/clientesForm.jsp").forward(request, response);
                return;
            
            // Excluir registro do banco de dados
            case "excluir":
                id = Long.parseLong(request.getParameter("id"));
                Facade.remover(id);
                response.sendRedirect(request.getContextPath() + "/clientes");
                return;
            
            // Busca o cliente para exibir em formulário de visualização
            case "visualizar":
                id = Long.parseLong(request.getParameter("id"));
                cliente = Facade.buscar(id);
                request.setAttribute("cliente", cliente);
                estados = new ArrayList<>();
                estados.add(cliente.getEndereco().getCidade().getEstado());
                request.setAttribute("estados", estados);
                cidades = new ArrayList<>();
                cidades.add(cliente.getEndereco().getCidade());
                request.setAttribute("cidades", cidades);
                request.setAttribute("readOnly", true);
                request.getRequestDispatcher("/jsp/clientesForm.jsp").forward(request, response);
                return;
           
            // Busca o cliente para exibir em formulário de visualização
            case "alterar":
                try {
                    id = Long.parseLong(request.getParameter("id"));
                } catch (NumberFormatException e) {}
                cliente = Facade.buscar(id);
                request.setAttribute("cliente", cliente);
                estados = Facade.buscarEstados();
                request.setAttribute("estados", estados);
                cidades = Facade.buscarCidades(cliente.getEndereco().getCidade().getEstado().getId());
                request.setAttribute("cidades", cidades);
                request.setAttribute("alterando", true);
                request.getRequestDispatcher("/jsp/clientesForm.jsp").forward(request, response);
                return;
        }

        // Exibir erro em qualquer outra URI
        response.sendError(404);
    }
    
    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Salvar URI's em vetor
        String[] uri = request.getRequestURI().split("/");
        
        // Prosseguir apenas se houverem apenas 1 parâmetro adicional
        if (uri.length == 4) {
            
            // Definir encoding dos dados da requisição
            request.setCharacterEncoding("UTF-8");

            // Instanciar modelos e associar parâmetros
            Cidade cidade = new Cidade();
            try {
                cidade.setId(Long.parseLong(request.getParameter("cidade")));
            } catch (NumberFormatException e) {
                cidade = null;
            }
            Endereco endereco = new Endereco();
            endereco.setCep(request.getParameter("cep"));
            endereco.setRua(request.getParameter("rua"));
            try {
                endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
            } catch (NumberFormatException e) {}
            endereco.setCidade(cidade);
            Cliente cliente = new Cliente();
            try {
                cliente.setId(Long.parseLong(request.getParameter("id")));
            } catch (NumberFormatException e) {}
            cliente.setCpf(request.getParameter("cpf"));
            cliente.setNome(request.getParameter("nome"));
            cliente.setEmail(request.getParameter("email"));
            cliente.setDataNasc(Converter.toDate(request.getParameter("nasc")));
            cliente.setEndereco(endereco);

            // Converter e validar dados
            List<Erro> erros = cliente.validar();

            // Em caso de erro no processamento, processar resposta de erro
            if (!erros.isEmpty()) {
                request.setAttribute("cliente", cliente);
                request.setAttribute("erros", erros);
                doGet(request, response);
                return;
            }

            // Em caso de novo registro, validar se CPF já está cadastrado e salvá-lo
            request.setAttribute("action", uri[3]);
            if (uri[3].equals("novo")) {
                Facade.inserir(cliente);
                response.sendRedirect(request.getContextPath() + "/clientes");
                return;
            }

            // Em caso de atualização, apenas salvá-la e redirecionar
            else if (uri[3].equals("alterar")) {
                Facade.alterar(cliente);
                response.sendRedirect(request.getContextPath() + "/clientes");
                return;
            }
        }
        
        // Exibir erro em qualquer outra URI
        response.sendError(404);
    }
}

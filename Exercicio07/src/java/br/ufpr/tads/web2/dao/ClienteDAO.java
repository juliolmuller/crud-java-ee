package br.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.ufpr.tads.web2.beans.Cliente;
import br.ufpr.tads.web2.beans.Cidade;
import br.ufpr.tads.web2.beans.Endereco;
import br.ufpr.tads.web2.beans.Estado;

public abstract class ClienteDAO {

    private static final String TABELA_CLIENTE = "tb_cliente";
    private static final String TABELA_CIDADE = "tb_cidade";
    private static final String TABELA_ESTADO = "tb_estado";

    public static List<Cliente> listar() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            List<Cliente> clientes = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT " +
                "id_cliente, cpf_cliente, nome_cliente, email_cliente, data_cliente, cep_cliente, " +
                "rua_cliente, nr_cliente, id_cidade, nome_cidade, id_estado, nome_estado, sigla_estado " +
                "FROM " + TABELA_CLIENTE + " " +
                "INNER JOIN " + TABELA_CIDADE + " ON cidade_id = id_cidade " +
                "INNER JOIN " + TABELA_ESTADO + " ON estado_id = id_estado;"
            );
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setId(rs.getInt("id_estado"));
                estado.setNome(rs.getString("nome_estado"));
                estado.setSigla(rs.getString("sigla_estado"));
                Cidade cidade = new Cidade();
                cidade.setId(rs.getInt("id_cidade"));
                cidade.setNome(rs.getString("nome_cidade"));
                cidade.setEstado(estado);
                Endereco endereco = new Endereco();
                endereco.setCep(rs.getString("cep_cliente"));
                endereco.setRua(rs.getString("rua_cliente"));
                endereco.setNumero(rs.getInt("nr_cliente"));
                endereco.setCidade(cidade);
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setCpf(rs.getString("cpf_cliente"));
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setEmail(rs.getString("email_cliente"));
                cliente.setDataNasc(rs.getDate("data_cliente"));
                cliente.setEndereco(endereco);
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Cliente com(Object dado, String sql) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            if (dado instanceof Integer) {
                stmt.setInt(1, (Integer) dado);
            } else {
                stmt.setString(1, (String) dado);
            }
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) return null;
            Estado estado = new Estado();
            estado.setId(rs.getInt("id_estado"));
            estado.setNome(rs.getString("nome_estado"));
            estado.setSigla(rs.getString("sigla_estado"));
            Cidade cidade = new Cidade();
            cidade.setId(rs.getInt("id_cidade"));
            cidade.setNome(rs.getString("nome_cidade"));
            cidade.setEstado(estado);
            Endereco endereco = new Endereco();
            endereco.setCep(rs.getString("cep_cliente"));
            endereco.setRua(rs.getString("rua_cliente"));
            endereco.setNumero(rs.getInt("nr_cliente"));
            endereco.setCidade(cidade);
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id_cliente"));
            cliente.setCpf(rs.getString("cpf_cliente"));
            cliente.setNome(rs.getString("nome_cliente"));
            cliente.setEmail(rs.getString("email_cliente"));
            cliente.setDataNasc(rs.getDate("data_cliente"));
            cliente.setEndereco(endereco);
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Cliente comId(int id) {
        String sql = "SELECT " +
            "id_cliente, cpf_cliente, nome_cliente, email_cliente, data_cliente, cep_cliente, " +
            "rua_cliente, nr_cliente, id_cidade, nome_cidade, id_estado, nome_estado, sigla_estado " +
            "FROM " + TABELA_CLIENTE + " " +
            "INNER JOIN " + TABELA_CIDADE + " ON cidade_id = id_cidade " +
            "INNER JOIN " + TABELA_ESTADO + " ON estado_id = id_estado " +
            "WHERE id_cliente = ?;";
        return ClienteDAO.com(id, sql);
    }
    
    public static Cliente comCpf(String cpf) {
        String sql = "SELECT " +
            "id_cliente, cpf_cliente, nome_cliente, email_cliente, data_cliente, cep_cliente, " +
            "rua_cliente, nr_cliente, id_cidade, nome_cidade, id_estado, nome_estado, sigla_estado " +
            "FROM " + TABELA_CLIENTE + " " +
            "INNER JOIN " + TABELA_CIDADE + " ON cidade_id = id_cidade " +
            "INNER JOIN " + TABELA_ESTADO + " ON estado_id = id_estado " +
            "WHERE cpf_cliente = ?;";
        return ClienteDAO.com(cpf, sql);
    }
    
    public static Cliente comEmail(String email) {
        String sql = "SELECT " +
            "id_cliente, cpf_cliente, nome_cliente, email_cliente, data_cliente, cep_cliente, " +
            "rua_cliente, nr_cliente, id_cidade, nome_cidade, id_estado, nome_estado, sigla_estado " +
            "FROM " + TABELA_CLIENTE + " " +
            "INNER JOIN " + TABELA_CIDADE + " ON cidade_id = id_cidade " +
            "INNER JOIN " + TABELA_ESTADO + " ON estado_id = id_estado " +
            "WHERE email_cliente = ?;";
        return ClienteDAO.com(email, sql);
    }

    public static Cliente inserir(Cliente cliente) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO " + TABELA_CLIENTE + "(" +
                "cpf_cliente, nome_cliente, email_cliente, data_cliente, " +
                "cep_cliente, rua_cliente, nr_cliente, cidade_id " +
                ") VALUES(?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_cliente;"
            );
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail().toLowerCase());
            stmt.setDate(4, new Date(cliente.getDataNasc().getTime()));
            stmt.setString(5, cliente.getEndereco().getCep());
            stmt.setString(6, cliente.getEndereco().getRua());
            stmt.setInt(7, cliente.getEndereco().getNumero());
            stmt.setInt(8, cliente.getEndereco().getCidade().getId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int id = rs.getInt("id_cliente");
            cliente.setId(id);
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean atualizar(Cliente cliente) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE " + TABELA_CLIENTE + " SET " +
                "cpf_cliente = ?, nome_cliente = ?, email_cliente = ?, data_cliente = ?, " +
                "cep_cliente = ?, rua_cliente = ?, nr_cliente = ?, cidade_id = ? " +
                "WHERE id_cliente = ?;"
            );
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail().toLowerCase());
            stmt.setDate(4, new Date(cliente.getDataNasc().getTime()));
            stmt.setString(5, cliente.getEndereco().getCep());
            stmt.setString(6, cliente.getEndereco().getRua());
            stmt.setInt(7, cliente.getEndereco().getNumero());
            stmt.setInt(8, cliente.getEndereco().getCidade().getId());
            stmt.setInt(10, cliente.getId());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean excluir(Cliente cliente) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM " + TABELA_CLIENTE + " WHERE id_cliente = ?;"
            );
            stmt.setInt(1, cliente.getId());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

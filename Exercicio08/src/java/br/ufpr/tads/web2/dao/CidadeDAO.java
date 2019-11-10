package br.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import br.ufpr.tads.web2.beans.Cidade;
import br.ufpr.tads.web2.beans.Estado;

public abstract class CidadeDAO {

    private static final String TABELA_ESTADO = "tb_estado";
    private static final String TABELA_CIDADE = "tb_cidade";

    public static List<Cidade> listar() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            List<Cidade> cidades = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT id_cidade, nome_cidade, id_estado, nome_estado, sigla_estado " +
                "FROM " + TABELA_CIDADE + " " +
                "INNER JOIN " + TABELA_ESTADO + " ON estado_id = id_estado;"
            );
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setId(rs.getLong("id_estado"));
                estado.setNome(rs.getString("nome_estado"));
                estado.setSigla(rs.getString("sigla_estado"));
                Cidade cidade = new Cidade();
                cidade.setId(rs.getLong("id_cidade"));
                cidade.setNome(rs.getString("nome_cidade"));
                cidade.setEstado(estado);
                cidades.add(cidade);
            }
            return cidades;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Cidade> listarPor(Long idEstado) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            List<Cidade> cidades = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT id_cidade, nome_cidade, id_estado, nome_estado, sigla_estado " +
                "FROM " + TABELA_CIDADE + " " +
                "INNER JOIN " + TABELA_ESTADO + " ON estado_id = id_estado " +
                "WHERE estado_id = ?;"
            );
            stmt.setLong(1, idEstado);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setId(rs.getLong("id_estado"));
                estado.setNome(rs.getString("nome_estado"));
                estado.setSigla(rs.getString("sigla_estado"));
                Cidade cidade = new Cidade();
                cidade.setId(rs.getLong("id_cidade"));
                cidade.setNome(rs.getString("nome_cidade"));
                cidade.setEstado(estado);
                cidades.add(cidade);
            }
            return cidades;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package br.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import br.ufpr.tads.web2.beans.Estado;

public abstract class EstadoDAO {

    private static final String TABELA = "tb_estado";

    public static List<Estado> listar() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            List<Estado> estados = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT id_estado, nome_estado, sigla_estado FROM " + TABELA + ";"
            );
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setId(rs.getLong("id_estado"));
                estado.setNome(rs.getString("nome_estado"));
                estado.setSigla(rs.getString("sigla_estado"));
                estados.add(estado);
            }
            return estados;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

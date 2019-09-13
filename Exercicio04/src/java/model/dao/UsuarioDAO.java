package model.dao;

import java.sql.Connection;
import database.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public abstract class UsuarioDAO {

    private static final String TABELA = "tb_usuario";
    
    public static List<Usuario> listar() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            List<Usuario> usuarios = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT id_usuario, nome_usuario, login_usuario, senha_usuario FROM " + TABELA + ";");
            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nome_usuario"),
                    rs.getString("login_usuario"),
                    rs.getString("senha_usuario")
                ));
            }
            return usuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static int inserir(Usuario usuario) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO " + TABELA + "(nome, login, senha) VALUES(?, ?, ?) RETURNING id_usuario;"
            );
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("id_usuario");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static boolean atualizar(Usuario usuario) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE " + TABELA + " SET nome_usuario = ?, login_usuario = ?, senha_usuario = ? WHERE id_usuario = ?;"
            );
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getId());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static boolean excluir(Usuario usuario) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM " + TABELA + " WHERE id_usuario = ?;"
            );
            stmt.setInt(1, usuario.getId());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

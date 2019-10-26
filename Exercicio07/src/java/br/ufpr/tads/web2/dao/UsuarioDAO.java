package br.ufpr.tads.web2.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import br.ufpr.tads.web2.beans.Usuario;

public abstract class UsuarioDAO {

    private static final String TABELA = "tb_usuario";

    public static List<Usuario> listar() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            List<Usuario> usuarios = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT id_usuario, nome_usuario, login_usuario, senha_usuario FROM " + TABELA + ";"
            );
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome_usuario"));
                usuario.setLogin(rs.getString("login_usuario"));
                usuario.setSenha(rs.getString("senha_usuario"));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean existe(String login) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT id_usuario FROM " + TABELA + " WHERE login_usuario = ?;"
            );
            stmt.setString(1, login.toUpperCase());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Usuario validar(String login, String senha) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT id_usuario, nome_usuario, login_usuario, senha_usuario FROM " + TABELA
                + " WHERE login_usuario = ? AND senha_usuario = ?;"
            );
            stmt.setString(1, login.toUpperCase());
            stmt.setString(2, encriptarSenha(senha));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome_usuario"));
                usuario.setLogin(rs.getString("login_usuario"));
                usuario.setSenha(rs.getString("senha_usuario"));
                return usuario;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int inserir(Usuario usuario) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO " + TABELA + "(nome_usuario, login_usuario, senha_usuario) "
                + "VALUES(?, ?, ?) RETURNING id_usuario;"
            );
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin().toUpperCase());
            usuario.setSenha(encriptarSenha(usuario.getSenha()));
            stmt.setString(3, usuario.getSenha());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int id = rs.getInt("id_usuario");
            usuario.setId(id);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean atualizar(Usuario usuario) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE " + TABELA + " SET nome_usuario = ?, login_usuario = ?, senha_usuario = ? WHERE id_usuario = ?;"
            );
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin().toUpperCase());
            stmt.setString(3, encriptarSenha(usuario.getSenha()));
            stmt.setInt(4, usuario.getId());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

    private static String encriptarSenha(String senha) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] senhaDigest = md5.digest(senha.getBytes("UTF-8"));
            StringBuilder senhaHash = new StringBuilder();
            for (byte b : senhaDigest)
                senhaHash.append(String.format("%02X", 0xFF & b));
            return senhaHash.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}

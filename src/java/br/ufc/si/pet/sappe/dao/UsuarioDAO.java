package br.ufc.si.pet.sappe.dao;

import java.sql.SQLException;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Usuario;
import java.util.ArrayList;

public class UsuarioDAO {

    public Usuario getByLogin(String login) throws SQLException {
        Usuario usuario = null;
        usuario = (Usuario) PostGresMapConfig.getSqlMapClient().queryForObject("getUsuarioByLogin", login);

        return usuario;

    }

    public void insert(Usuario usuario) throws SQLException {
        usuario.setId(proxId());
        //System.out.println(usuario.getId());

        PostGresMapConfig.getSqlMapClient().insert("addUsuario", usuario);
    }

    public void delete(Usuario usuario) throws SQLException {
        PostGresMapConfig.getSqlMapClient().delete("deleteUsuario", usuario);
    }

    public void update(Usuario user) throws SQLException {
        PostGresMapConfig.getSqlMapClient().delete("updateUsuario", user);
    }

    public Usuario getById(Long id) throws SQLException {
        return (Usuario) PostGresMapConfig.getSqlMapClient().queryForObject("getUsuarioById", id);
    }

    public ArrayList<Usuario> getByNome(String nome) throws SQLException {
        return (ArrayList<Usuario>) PostGresMapConfig.getSqlMapClient().queryForList("getUsuarioByNome", nome);
    }

    public Usuario getByEmail(String email) throws SQLException {
        return (Usuario) PostGresMapConfig.getSqlMapClient().queryForObject("getUsuarioByEmail", email);
    }

    public ArrayList<Usuario> getAll() throws SQLException{
        return (ArrayList<Usuario>) PostGresMapConfig.getSqlMapClient().queryForList("getTodosUsuarios");
    }

    private Long proxId() throws SQLException {
        Long id = (Long) PostGresMapConfig.getSqlMapClient().queryForObject("getMaxIdUsuario");
        if (id == null) {
            id = 0L;
        }
        return id + 1L;
    }

    public Usuario getByCpf(String cpf) throws SQLException {
        return (Usuario) PostGresMapConfig.getSqlMapClient().queryForObject("getUsuarioByCpf", cpf);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.UsuarioSimulado;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gleyson
 */
public class UsuarioSimuladoDAO {

    public void inserir(UsuarioSimulado us) throws SQLException {
        us.setId(proxId());
        PostGresMapConfig.getSqlMapClient().insert("addUsuarioSimulado", us);
    }

    public void update(UsuarioSimulado u) throws SQLException {
        PostGresMapConfig.getSqlMapClient().delete("updateUsuarioSimulado", u);
    }

    public UsuarioSimulado getById(Long id) throws SQLException {
        return (UsuarioSimulado) PostGresMapConfig.getSqlMapClient().queryForObject("getUsuarioSimuladoBySimuladoId", id);
    }

    public UsuarioSimulado getUsuarioSimuladoByUsuarioId(UsuarioSimulado us) throws SQLException {
        return (UsuarioSimulado) PostGresMapConfig.getSqlMapClient().queryForObject("getUsuarioSimuladoByUsuarioId", us);
    }

    public ArrayList<UsuarioSimulado> getAll() throws SQLException {
        return (ArrayList<UsuarioSimulado>) PostGresMapConfig.getSqlMapClient().queryForList("getTodosUsuariosSimulados");
    }

    public ArrayList<UsuarioSimulado> getUsuariosSimuladosBySimuladoId(Long id) throws SQLException {
        return (ArrayList<UsuarioSimulado>) PostGresMapConfig.getSqlMapClient().queryForList("getUsuariosSimuladosBySimuladoId", id);
    }

    private Long proxId() throws SQLException {
        Long id = (Long) PostGresMapConfig.getSqlMapClient().queryForObject("getMaxIdUsuarioSimulado");
        if (id == null) {
            id = 0L;
        }
        return id + 1L;
    }
}

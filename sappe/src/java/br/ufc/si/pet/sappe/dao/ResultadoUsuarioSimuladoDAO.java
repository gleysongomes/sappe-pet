/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.ResultadoUsuarioSimulado;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gleyson
 */
public class ResultadoUsuarioSimuladoDAO {

    public void inserir(ResultadoUsuarioSimulado us) throws SQLException {
        us.setId(proxId());
        PostGresMapConfig.getSqlMapClient().insert("addResultadoUsuarioSimulado", us);
    }

    public void update(ResultadoUsuarioSimulado u) throws SQLException {
        PostGresMapConfig.getSqlMapClient().delete("updateResultadoUsuarioSimulado", u);
    }

    public ResultadoUsuarioSimulado getById(Long id) throws SQLException {
        return (ResultadoUsuarioSimulado) PostGresMapConfig.getSqlMapClient().queryForObject("getResultadoUsuarioSimuladoBySimuladoId", id);
    }

    public ResultadoUsuarioSimulado getResultadoUsuarioSimuladoByUsuarioId(ResultadoUsuarioSimulado us) throws SQLException {
        return (ResultadoUsuarioSimulado) PostGresMapConfig.getSqlMapClient().queryForObject("getResultadoUsuarioSimuladoByUsuarioId", us);
    }

    public ArrayList<ResultadoUsuarioSimulado> getAll() throws SQLException {
        return (ArrayList<ResultadoUsuarioSimulado>) PostGresMapConfig.getSqlMapClient().queryForList("getTodosResultadosUsuariosSimulados");
    }

    public ArrayList<ResultadoUsuarioSimulado> getResultadosUsuariosSimuladosBySimuladoId(Long id) throws SQLException {
        return (ArrayList<ResultadoUsuarioSimulado>) PostGresMapConfig.getSqlMapClient().queryForList("getResultadosUsuariosSimuladosBySimuladoId", id);
    }

    private Long proxId() throws SQLException {
        Long id = (Long) PostGresMapConfig.getSqlMapClient().queryForObject("getMaxIdResultadoUsuarioSimulado");
        if (id == null) {
            id = 0L;
        }
        return id + 1L;
    }
}

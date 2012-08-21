/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Simulado;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gleyson
 */
public class SimuladoDAO {

    public void inserir(Simulado s) throws SQLException {
        s.setId(proxId());
        PostGresMapConfig.getSqlMapClient().insert("addSimulado", s);
    }

    public void excluir(Long id) throws SQLException {
        PostGresMapConfig.getSqlMapClient().delete("deleteSimulado", id);
    }

    public Simulado getById(Long id) throws SQLException {
        return (Simulado) PostGresMapConfig.getSqlMapClient().queryForObject("getSimuladoById", id);
    }

    public ArrayList<Simulado> getAll() throws SQLException {
        return (ArrayList<Simulado>) PostGresMapConfig.getSqlMapClient().queryForList("getTodosSimulados");
    }

    public ArrayList<Simulado> getListSimuladosByUsuario(Long id) throws SQLException {
        return (ArrayList<Simulado>) PostGresMapConfig.getSqlMapClient().queryForList("getListSimuladosByUsuario", id);
    }

    public Long proxId() throws SQLException {
        Long id = (Long) PostGresMapConfig.getSqlMapClient().queryForObject("getMaxIdSimulado");
        return id;
    }
}

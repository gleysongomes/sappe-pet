/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Supervisor;
import java.sql.SQLException;

/**
 *
 * @author gleyson
 */
public class SupervisorDAO {

    public void insert(Supervisor s) throws SQLException {
        PostGresMapConfig.getSqlMapClient().insert("addSupervisor", s);
    }


    public Supervisor getByUsuarioId(Long id) throws SQLException {
        return (Supervisor) PostGresMapConfig.getSqlMapClient().queryForObject("getSupervisorByUsuarioId", id);
    }
}

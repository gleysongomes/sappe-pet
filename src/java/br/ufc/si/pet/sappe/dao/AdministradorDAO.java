/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Administrador;
import java.sql.SQLException;

/**
 *
 * @author gleyson
 */
public class AdministradorDAO {

    public Administrador getByUsuarioId(Long id) throws SQLException {
        return (Administrador) PostGresMapConfig.getSqlMapClient().queryForObject("getAdministradorByUsuarioId", id);
    }
}

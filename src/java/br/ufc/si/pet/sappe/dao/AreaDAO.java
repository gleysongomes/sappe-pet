/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Area;
import java.sql.SQLException;

/**
 *
 * @author gleyson
 */
public class AreaDAO {

    public Area getAreaById(Long id) throws SQLException {
        return (Area) PostGresMapConfig.getSqlMapClient().queryForObject("getAreaById", id);
    }

}

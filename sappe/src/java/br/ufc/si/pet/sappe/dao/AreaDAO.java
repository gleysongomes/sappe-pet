/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Area;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gleyson
 */
public class AreaDAO {

    public void inserir(Area a) throws SQLException {
        PostGresMapConfig.getSqlMapClient().insert("addArea", a);
    }

    public Area getAreaById(Long id) throws SQLException {
        return (Area) PostGresMapConfig.getSqlMapClient().queryForObject("getAreaById", id);
    }

    public ArrayList<Area> getAllAreasByExameId(Long id) throws SQLException {
        return (ArrayList<Area>) PostGresMapConfig.getSqlMapClient().queryForList("getAllAreasByExameId", id);
    }
}

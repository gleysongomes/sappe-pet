/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.AreaDAO;
import br.ufc.si.pet.sappe.entidades.Area;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleyson
 */
public class AreaService {

    private AreaDAO areaDAO;

    public AreaService() {
        areaDAO = new AreaDAO();
    }

    public Area getAreaById(Long id) {
        try {
            Area a = areaDAO.getAreaById(id);
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(AreaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

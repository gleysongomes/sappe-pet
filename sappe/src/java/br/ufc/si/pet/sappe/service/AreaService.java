/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.AreaDAO;
import br.ufc.si.pet.sappe.entidades.Area;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public boolean inserir(Area a) {
        try {
            areaDAO.inserir(a);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AreaService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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

    public ArrayList<Area> getAllAreasByExameId(Long id) {
        try {
            ArrayList<Area> areas = areaDAO.getAllAreasByExameId(id);
            return areas;
        } catch (SQLException ex) {
            Logger.getLogger(QuestaoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.ExameDAO;
import br.ufc.si.pet.sappe.entidades.Exame;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleyson
 */
public class ExameService {

    private ExameDAO exameDAO;

    /**
     *
     */
    public ExameService() {
        exameDAO = new ExameDAO();
    }

    public Exame getExameById(Integer id) {
        try {
            Exame e = exameDAO.getExameById(id);
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(ExameService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

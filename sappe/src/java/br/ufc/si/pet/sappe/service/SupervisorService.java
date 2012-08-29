/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.SupervisorDAO;
import br.ufc.si.pet.sappe.entidades.Supervisor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleyson
 */
public class SupervisorService {

    private SupervisorDAO supervisorDAO;

    public SupervisorService() {
        supervisorDAO = new SupervisorDAO();
    }

    public boolean insertSupervisor(Supervisor supervisor) {
        try {
            supervisorDAO.insert(supervisor);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Supervisor getSupervisorByUsuarioId(Long id) {
        try {
            Supervisor supervisor = supervisorDAO.getByUsuarioId(id);
            if (supervisor == null) {
                return null;
            } else {
                return supervisor;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Supervisor> getAll() {
        try {
            ArrayList<Supervisor> supervisores = supervisorDAO.getListAllSupervisores();
            return supervisores;
        } catch (SQLException ex) {
            Logger.getLogger(SupervisorService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean deleteSupervisor(Long id) {
        try {
            supervisorDAO.delete(id);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

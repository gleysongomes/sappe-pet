/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.SupervisorDAO;
import br.ufc.si.pet.sappe.entidades.Supervisor;
import java.sql.SQLException;

/**
 *
 * @author gleyson
 */
public class SupervisorService {

    private SupervisorDAO supervisorDAO;

    public SupervisorService() {
        supervisorDAO = new SupervisorDAO();
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
}

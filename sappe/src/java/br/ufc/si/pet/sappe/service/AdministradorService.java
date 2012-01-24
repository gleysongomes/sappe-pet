/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.AdministradorDAO;
import br.ufc.si.pet.sappe.entidades.Administrador;
import java.sql.SQLException;

/**
 *
 * @author gleyson
 */
public class AdministradorService {

    private AdministradorDAO administradorDAO;

    public AdministradorService() {
        administradorDAO = new AdministradorDAO();
    }

    public Administrador getAdministradorByUsuarioId(Long id) {
        try {
            Administrador administrador = administradorDAO.getByUsuarioId(id);
            if (administrador == null) {
                return null;
            } else {
                return administrador;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

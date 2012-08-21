/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.UsuarioSimuladoDAO;
import br.ufc.si.pet.sappe.entidades.UsuarioSimulado;
import java.sql.SQLException;

/**
 *
 * @author gleyson
 */
public class UsuarioSimuladoService {

     private final UsuarioSimuladoDAO usuarioSimuladoDAO = new UsuarioSimuladoDAO();

    public boolean insertUsuarioSimulado(UsuarioSimulado us) {
        try {
            usuarioSimuladoDAO.insertUsuarioSimulado(us);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

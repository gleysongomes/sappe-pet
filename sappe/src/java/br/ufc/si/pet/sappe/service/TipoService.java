/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.TipoDAO;
import br.ufc.si.pet.sappe.entidades.Tipo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleyson
 */
public class TipoService {

    private TipoDAO tipoDAO;

    public TipoService() {
        tipoDAO = new TipoDAO();
    }

    public boolean inserir(Tipo t) {
        try {
            tipoDAO.inserir(t);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TipoService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Tipo getTipoById(Long id) {
        try {
            Tipo tipo = tipoDAO.getTipoById(id);
            return tipo;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

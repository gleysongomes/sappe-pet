/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.TipoDAO;
import br.ufc.si.pet.sappe.entidades.Tipo;
import java.sql.SQLException;

/**
 *
 * @author gleyson
 */
public class TipoService {

    private TipoDAO tipoDAO;

    public TipoService() {
        tipoDAO = new TipoDAO();
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

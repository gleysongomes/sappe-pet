/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.SimuladoDAO;
import br.ufc.si.pet.sappe.entidades.Simulado;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleyson
 */
public class SimuladoService {

    private SimuladoDAO simuladoDAO;

    public SimuladoService() {
        simuladoDAO = new SimuladoDAO();
    }

    public boolean inserir(Simulado qs) {
        try {
            simuladoDAO.inserir(qs);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SimuladoService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Simulado getSimuladoById(Long id) {
        try {
            Simulado s = simuladoDAO.getById(id);
            return s;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Simulado> getAllSimulados() {
        try {
            ArrayList<Simulado> simulados = simuladoDAO.getAll();
            return simulados;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Long proxId() {
        try {
            return simuladoDAO.proxId();
        } catch (SQLException ex) {
            Logger.getLogger(SimuladoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0L;
    }
}

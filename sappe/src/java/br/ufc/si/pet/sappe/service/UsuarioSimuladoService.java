/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.UsuarioSimuladoDAO;
import br.ufc.si.pet.sappe.entidades.UsuarioSimulado;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleyson
 */
public class UsuarioSimuladoService {

    private UsuarioSimuladoDAO usuarioSimuladoDAO;

    public UsuarioSimuladoService() {
        usuarioSimuladoDAO = new UsuarioSimuladoDAO();
    }

    public boolean inserir(UsuarioSimulado us) {
        try {
            usuarioSimuladoDAO.inserir(us);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioSimuladoService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean updateUsuarioSimulado(UsuarioSimulado usuarioSimulado) {
        try {
            usuarioSimuladoDAO.update(usuarioSimulado);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public UsuarioSimulado getUsuarioSimuladoBySimuladoId(Long id) {
        try {
            UsuarioSimulado s = usuarioSimuladoDAO.getById(id);
            return s;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public UsuarioSimulado getUsuarioSimuladoByUsuarioId(UsuarioSimulado us) {
        try {
            UsuarioSimulado s = usuarioSimuladoDAO.getUsuarioSimuladoByUsuarioId(us);
            return s;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<UsuarioSimulado> getAllUsuariosSimulados() {
        try {
            ArrayList<UsuarioSimulado> usuariosSimulados = usuarioSimuladoDAO.getAll();
            return usuariosSimulados;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public ArrayList<UsuarioSimulado> getUsuariosSimuladosBySimuladoId(Long id) {
        try {
            ArrayList<UsuarioSimulado> usuariosSimulados = usuarioSimuladoDAO.getUsuariosSimuladosBySimuladoId(id);
            return usuariosSimulados;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

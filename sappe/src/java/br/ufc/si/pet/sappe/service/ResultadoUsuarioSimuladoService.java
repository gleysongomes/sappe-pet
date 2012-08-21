/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.ResultadoUsuarioSimuladoDAO;
import br.ufc.si.pet.sappe.entidades.ResultadoUsuarioSimulado;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleyson
 */
public class ResultadoUsuarioSimuladoService {

    private ResultadoUsuarioSimuladoDAO resultadoUsuarioSimuladoDAO;

    public ResultadoUsuarioSimuladoService() {
        resultadoUsuarioSimuladoDAO = new ResultadoUsuarioSimuladoDAO();
    }

    public boolean inserir(ResultadoUsuarioSimulado us) {
        try {
            resultadoUsuarioSimuladoDAO.inserir(us);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ResultadoUsuarioSimuladoService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateResultadoUsuarioSimulado(ResultadoUsuarioSimulado usuarioSimulado) {
        try {
            resultadoUsuarioSimuladoDAO.update(usuarioSimulado);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ResultadoUsuarioSimulado getResultadoUsuarioSimuladoBySimuladoId(Long id) {
        try {
            ResultadoUsuarioSimulado s = resultadoUsuarioSimuladoDAO.getById(id);
            return s;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ResultadoUsuarioSimulado getResultadoUsuarioSimuladoByUsuarioId(ResultadoUsuarioSimulado us) {
        try {
            ResultadoUsuarioSimulado s = resultadoUsuarioSimuladoDAO.getResultadoUsuarioSimuladoByUsuarioId(us);
            return s;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<ResultadoUsuarioSimulado> getAllResultadosUsuariosSimulados() {
        try {
            ArrayList<ResultadoUsuarioSimulado> resultadosUsuariosSimulados = resultadoUsuarioSimuladoDAO.getAll();
            return resultadosUsuariosSimulados;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<ResultadoUsuarioSimulado> getResultadosUsuariosSimuladosBySimuladoId(Long id) {
        try {
            ArrayList<ResultadoUsuarioSimulado> resultadosUsuariosSimulados = resultadoUsuarioSimuladoDAO.getResultadosUsuariosSimuladosBySimuladoId(id);
            return resultadosUsuariosSimulados;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

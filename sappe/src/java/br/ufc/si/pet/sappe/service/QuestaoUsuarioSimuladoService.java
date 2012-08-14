/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.QuestaoUsuarioSimuladoDAO;
import br.ufc.si.pet.sappe.entidades.QuestaoUsuarioSimulado;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleyson
 */
public class QuestaoUsuarioSimuladoService {

    private QuestaoUsuarioSimuladoDAO questaoUsuarioSimuladoDAO;

    public QuestaoUsuarioSimuladoService() {
        questaoUsuarioSimuladoDAO = new QuestaoUsuarioSimuladoDAO();
    }

    public boolean inserir(QuestaoUsuarioSimulado qus) {
        try {
            questaoUsuarioSimuladoDAO.inserir(qus);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioSimuladoService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean update(QuestaoUsuarioSimulado qus) {
        try {
            questaoUsuarioSimuladoDAO.update(qus);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<QuestaoUsuarioSimulado> getQuestoesUsuarioSimuladoByIdUsuarioESimulado(QuestaoUsuarioSimulado qus) {
        try {
            ArrayList<QuestaoUsuarioSimulado> questaoUsuarioSimulados = questaoUsuarioSimuladoDAO.getQuestoesUsuarioSimuladoByIdUsuarioESimulado(qus);
            return questaoUsuarioSimulados;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

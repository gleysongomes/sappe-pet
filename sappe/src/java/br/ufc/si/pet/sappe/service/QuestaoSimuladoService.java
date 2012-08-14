/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.QuestaoSimuladoDAO;
import br.ufc.si.pet.sappe.entidades.QuestaoSimulado;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleyson
 */
public class QuestaoSimuladoService {

    private QuestaoSimuladoDAO questaoSimuladoDAO;

    public QuestaoSimuladoService() {
        questaoSimuladoDAO = new QuestaoSimuladoDAO();
    }

    public boolean inserir(QuestaoSimulado qs) {
        try {
            questaoSimuladoDAO.inserir(qs);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuestaoSimuladoService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

     public ArrayList<QuestaoSimulado> getListQuestaoSimuladoByIdSimulado(Long id) {
        try {
            ArrayList<QuestaoSimulado> questaos = questaoSimuladoDAO.getListQuestaoSimuladoByIdSimulado(id);
            return questaos;
        } catch (SQLException ex) {
            Logger.getLogger(QuestaoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

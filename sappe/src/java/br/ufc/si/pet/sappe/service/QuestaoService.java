/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.QuestaoDAO;
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.Utility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gleyson
 */
public class QuestaoService {

    private QuestaoDAO questaoDAO;

    /**
     *
     */
    public QuestaoService() {
        questaoDAO = new QuestaoDAO();
    }

    public ArrayList<Questao> getListQuestoes(Utility utility) {
        try {
            ArrayList<Questao> questaos = questaoDAO.getListQuestoes(utility);
            return questaos;
        } catch (SQLException ex) {
            Logger.getLogger(QuestaoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Questao getQuestaoById(Long id) {
        try {
            Questao q = questaoDAO.getById(id);
            return q;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<Questao> getListQuestoesByArea(Utility utility) {
        try {
            ArrayList<Questao> questaos = questaoDAO.getListQuestoesByArea(utility);
            System.out.println("-------1" + utility.getQtdq());
            return questaos;
        } catch (SQLException ex) {
            Logger.getLogger(QuestaoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Questao> getListQuestoesByExame(Utility utility) {
        try {
            ArrayList<Questao> questaos = questaoDAO.getListQuestoesByExame(utility);
            System.out.println("-------2>" + utility.getQtdq());
            return questaos;
        } catch (SQLException ex) {
            Logger.getLogger(QuestaoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Questao> getListQuestoesByAreaSimulado(Utility utility) {
        try {
            ArrayList<Questao> questaos = questaoDAO.getListQuestoesByAreaSimulado(utility);
            return questaos;
        } catch (SQLException ex) {
            Logger.getLogger(QuestaoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

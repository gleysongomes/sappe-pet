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

    public boolean inserir(Questao q) {
        try {
            questaoDAO.inserir(q);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuestaoService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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

    public ArrayList<Questao> visualizarQuestoesAnoExame(String ano) {
        try {
            ArrayList<Questao> questaos = questaoDAO.getListQuestoesByAnoExame(ano);
            return questaos;
        } catch (SQLException ex) {
            Logger.getLogger(QuestaoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }//fim do método

    public ArrayList<Questao> visualizarTodasQuestoes() {
        try {
            ArrayList<Questao> questaos = questaoDAO.getAllListQuestoes();
            return questaos;
        } catch (SQLException ex) {
            Logger.getLogger(QuestaoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }//fim do método

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

    public ArrayList<Questao> getAllAnosQuestoesByExame(Long id) {
        try {
            ArrayList<Questao> questaos = questaoDAO.getAllAnosQuestoesByExame(id);
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
    }//fim do método

    public boolean deleteQuestaoById(long id) {
        return questaoDAO.deleteQuestao(id);
    }//fim do método

    public void updateQuestao(Questao questao) {
        questaoDAO.update(questao);
    }//fim do método
}//fim da classe


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.QuestaoProvaDAO;
import br.ufc.si.pet.sappe.entidades.QuestaoProva;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleyson
 */
public class QuestaoProvaService {

    private QuestaoProvaDAO questaoProvaDAO;

    public QuestaoProvaService() {
        questaoProvaDAO = new QuestaoProvaDAO();
    }

    public boolean inserir(QuestaoProva qP) {
        try {
            questaoProvaDAO.inserir(qP);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuestaoProvaService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateQuestaoProva(QuestaoProva qP) {
        try {
            questaoProvaDAO.update(qP);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateQuestaoProvaByIdProva(QuestaoProva qP) {
        try {
            questaoProvaDAO.updateQuestaoProvaByIdProva(qP);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public QuestaoProva getQuestaoProvaById(Long id) {
        try {
            QuestaoProva questaoProva = questaoProvaDAO.getById(id);
            return questaoProva;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<QuestaoProva> getListQuestaoProvaById(Long id) {
        try {
            ArrayList<QuestaoProva> qP = questaoProvaDAO.getListQuestaoProvaById(id);
            return qP;
        } catch (SQLException ex) {
            Logger.getLogger(QuestaoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

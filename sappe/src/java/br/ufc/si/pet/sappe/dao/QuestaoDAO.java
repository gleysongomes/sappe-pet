/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Questao;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gleyson
 */
public class QuestaoDAO {

    public ArrayList<Questao> getListQuestoes(Long id) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getListQuestoes", id);
    }

    public Questao getById(Long id) throws SQLException {
        return (Questao) PostGresMapConfig.getSqlMapClient().queryForObject("getQuestaoById", id);
    }

    public ArrayList<Questao> getListQuestoesByArea(int idArea, int idUser, int n) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getListQuestoesByArea", idArea, idUser, n);
    }
}

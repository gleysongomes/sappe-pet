/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.QuestaoProva;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gleyson
 */
public class QuestaoProvaDAO {

    public void inserir(QuestaoProva qP) throws SQLException {
        qP.setId(proxId());
        PostGresMapConfig.getSqlMapClient().insert("addQuestaoProva", qP);
    }

    public void update(QuestaoProva qP) throws SQLException {
        PostGresMapConfig.getSqlMapClient().update("updateQuestaoProva", qP);
    }

    public void updateQuestaoProvaByIdProva(QuestaoProva qP) throws SQLException {
        PostGresMapConfig.getSqlMapClient().update("updateQuestaoProvaByIdProva", qP);
    }

    public QuestaoProva getById(Long id) throws SQLException {
        return (QuestaoProva) PostGresMapConfig.getSqlMapClient().queryForObject("getQuestaoProvaById", id);
    }

    public ArrayList<QuestaoProva> getListQuestaoProvaById(Long id) throws SQLException {
        return (ArrayList<QuestaoProva>) PostGresMapConfig.getSqlMapClient().queryForList("getListQuestaoProvaById", id);
    }

    private Long proxId() throws SQLException {
        Long id = (Long) PostGresMapConfig.getSqlMapClient().queryForObject("getMaxIdQuestaoProva");
        if (id == null) {
            id = 0L;
        }
        return id + 1L;
    }
}

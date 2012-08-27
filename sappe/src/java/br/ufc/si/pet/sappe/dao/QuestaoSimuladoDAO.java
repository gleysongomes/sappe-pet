/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.QuestaoSimulado;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gleyson
 */
public class QuestaoSimuladoDAO {

    public void inserir(QuestaoSimulado qs) throws SQLException {
        qs.setId(proxId());
        PostGresMapConfig.getSqlMapClient().insert("addQuestaoSimulado", qs);
    }

     public ArrayList<QuestaoSimulado> getListQuestaoSimuladoByIdSimulado(Long id) throws SQLException {
        return (ArrayList<QuestaoSimulado>) PostGresMapConfig.getSqlMapClient().queryForList("getListQuestaoSimuladoByIdSimulado", id);
    }

     private Long proxId() throws SQLException {
        Long id = (Long) PostGresMapConfig.getSqlMapClient().queryForObject("getMaxIdQuestaoSimulado");
        if (id == null) {
            id = 0L;
        }
        return id + 1L;
    }
}

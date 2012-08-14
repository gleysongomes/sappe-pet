/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.QuestaoUsuarioSimulado;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gleyson
 */
public class QuestaoUsuarioSimuladoDAO {

    public void inserir(QuestaoUsuarioSimulado qsQus) throws SQLException {
        //qsQus.setId(proxId());
        PostGresMapConfig.getSqlMapClient().insert("addQuestaoUsuarioSimulado", qsQus);
    }

     public void update(QuestaoUsuarioSimulado p) throws SQLException {
        PostGresMapConfig.getSqlMapClient().update("updateQuestaoUsuarioSimulado", p);
    }

    public ArrayList<QuestaoUsuarioSimulado> getQuestoesUsuarioSimuladoByIdUsuarioESimulado(QuestaoUsuarioSimulado qus) throws SQLException {
        return (ArrayList<QuestaoUsuarioSimulado>) PostGresMapConfig.getSqlMapClient().queryForList("getQuestoesUsuarioSimuladoByIdUsuarioESimulado", qus);
    }

    /*private Long proxId() throws SQLException {
        Long id = (Long) PostGresMapConfig.getSqlMapClient().queryForObject("getMaxIdQuestaoUsuarioSimulado");
        return id + 1L;
    }*/
}

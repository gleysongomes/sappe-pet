/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.Utility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gleyson
 */
public class QuestaoDAO {

    public void inserir(Questao q) throws SQLException {
        PostGresMapConfig.getSqlMapClient().insert("addQuestao", q);
    }

    public ArrayList<Questao> getListQuestoes(Utility utility) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getListQuestoes", utility);
    }

    public ArrayList<Questao> getAllAnosQuestoesByExame(Long id) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getAllAnosQuestoesByExame", id);
    }

    public Questao getById(Long id) throws SQLException {
        return (Questao) PostGresMapConfig.getSqlMapClient().queryForObject("getQuestaoById", id);
    }

    public ArrayList<Questao> getListQuestoesByArea(Utility utility) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getListQuestoesByArea", utility);
    }

    public ArrayList<Questao> getListQuestoesByAreaSimulado(Utility utility) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getListQuestoesByAreaSimulado", utility);
    }

    public ArrayList<Questao> getListQuestoesByExame(Utility utility) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getListQuestoesByExame", utility);
    }

    public ArrayList<Questao> getListQuestoesByAnoExame(String ano) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getQuestoesByAnoExame", ano);
    }//fim do método

     public ArrayList<Questao> listaQuestoesPeloAnoEPeloExame(Utility utility) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("listaQuestoesPeloAnoEPeloExame", utility);
    }//fim do método

    public List<String> getAllListAnoQuestoes() throws SQLException {
        return (List<String>) PostGresMapConfig.getSqlMapClient().queryForList("getAllAnoQuestoes");
    }//fim do método

    public boolean deleteQuestao(long id) {
        try {
            PostGresMapConfig.getSqlMapClient().delete("deleteQuestao", id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }//fim do método

    public boolean update(Questao questao) {
        try {
            PostGresMapConfig.getSqlMapClient().update("updateQuestao", questao);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }//fim do método
}//fim da classe


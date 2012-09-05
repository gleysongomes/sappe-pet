/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Prova;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gleyson
 */
public class ProvaDAO {

    public void inserir(Prova p) throws SQLException {
        p.setId(proxId());
        PostGresMapConfig.getSqlMapClient().insert("addProva", p);
    }

    public void update(Prova p) throws SQLException {
        PostGresMapConfig.getSqlMapClient().update("updateProva", p);
    }

    public void updateProvaById(Prova p) throws SQLException {
        PostGresMapConfig.getSqlMapClient().update("updateProvaById", p);
    }

    public Prova getProvaById(Long id) throws SQLException {
        return (Prova) PostGresMapConfig.getSqlMapClient().queryForObject("getProvaById", id);
    }

    public Prova getProvaByIdUsuario(Long id) throws SQLException {
        return (Prova) PostGresMapConfig.getSqlMapClient().queryForObject("getProvaByIdUsuario", id);
    }

    public ArrayList<Prova> getListProvas() throws SQLException {
        return (ArrayList<Prova>) PostGresMapConfig.getSqlMapClient().queryForList("getListProvas");
    }

    public ArrayList<Prova> getProvasByTipoId(Long id) throws SQLException {
        return (ArrayList<Prova>) PostGresMapConfig.getSqlMapClient().queryForList("getProvasByTipoId", id);
    }

    public ArrayList<Prova> getListAllProvasByIdUsuario(Long id) throws SQLException {
        return (ArrayList<Prova>) PostGresMapConfig.getSqlMapClient().queryForList("getListAllProvasByIdUsuario", id);
    }

    private Long proxId() throws SQLException {
        Long id = (Long) PostGresMapConfig.getSqlMapClient().queryForObject("getMaxIdProva");
        if (id == null) {
            id = 0L;
        }
        return id + 1L;
    }
}

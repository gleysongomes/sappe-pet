/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Tipo;
import java.sql.SQLException;

/**
 *
 * @author gleyson
 */
public class TipoDAO {

    public void inserir(Tipo t) throws SQLException {
        PostGresMapConfig.getSqlMapClient().insert("addTipo", t);
    }

    public Tipo getTipoById(Long id) throws SQLException {
        return (Tipo) PostGresMapConfig.getSqlMapClient().queryForObject("getTipoById", id);
    }
}

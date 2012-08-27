/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.UsuarioSimulado;
import java.sql.SQLException;

/**
 *
 * @author gleyson
 */
public class UsuarioSimuladoDAO {

    public void insertUsuarioSimulado(UsuarioSimulado us) throws SQLException {
        PostGresMapConfig.getSqlMapClient().insert("addUsuarioSimulado", us);
    }
}

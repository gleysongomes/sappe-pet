/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Exame;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gleyson
 */
public class ExameDAO {
 public Exame getExameById(Long id) throws SQLException {
        return (Exame) PostGresMapConfig.getSqlMapClient().queryForObject("getExameById", id);
 }

 public List<Exame> listarTodosExames() throws SQLException{
     return (List<Exame>) PostGresMapConfig.getSqlMapClient().queryForList("listarTodosExames");
 }

}

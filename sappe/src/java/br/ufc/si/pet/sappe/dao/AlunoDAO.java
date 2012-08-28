/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Aluno;
import java.sql.SQLException;

/**
 *
 * @author gleyson
 */
public class AlunoDAO {
    public Aluno getByUsuarioId(Long id) throws SQLException {
        return (Aluno) PostGresMapConfig.getSqlMapClient().queryForObject("getAlunoByUsuarioId", id);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.AlunoDAO;
import br.ufc.si.pet.sappe.entidades.Aluno;
import java.sql.SQLException;

/**
 *
 * @author gleyson
 */
public class AlunoService {

    private AlunoDAO alunoDAO;

    public AlunoService() {
        alunoDAO = new AlunoDAO();
    }

    public Aluno getAlunoByUsuarioId(Long id) {
        try {
            Aluno aluno = alunoDAO.getByUsuarioId(id);
            if (aluno == null) {
                return null;
            } else {
                return aluno;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

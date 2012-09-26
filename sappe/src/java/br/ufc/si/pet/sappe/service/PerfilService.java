/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.PerfilDAO;
import br.ufc.si.pet.sappe.entidades.Perfil;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ismaily
 */
public class PerfilService {

    private final PerfilDAO dao = new PerfilDAO();

    public boolean insertPerfil(Perfil perfil) {
        try {
            dao.insert(perfil);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deletePerfil(Long id) {
        try {
            dao.delete(id);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Perfil getPerfilById(Long id) {
        try {
            Perfil perfil = dao.getById(id);
            return perfil;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Perfil getPerfilByUsuarioId(Long id) {
        try {
            Perfil perfil = dao.getByUsuarioId(id);
            return perfil;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean ativarConta(Perfil perfil) {
        try {
            dao.ativarConta(perfil);
            if (perfil.getAtivo() == true) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Perfil> getListAllContasInativas() {
        try {
            ArrayList<Perfil> perfils = dao.getListAllContasInativas();
            return perfils;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Long ultimoId() throws SQLException{
     return  dao.proxId();
    }
    /*
    public Long contaPerfisByUsuarioId(Long id){
    Long numero = 0L;
    try {
    numero = dao.contaPerfisByUsuarioId(id);
    } catch (SQLException ex) {
    ex.printStackTrace();
    }
    return numero;
    }
     */
}

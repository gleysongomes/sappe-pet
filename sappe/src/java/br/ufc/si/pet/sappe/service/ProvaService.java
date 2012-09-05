/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.ProvaDAO;
import br.ufc.si.pet.sappe.entidades.Prova;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleyson
 */
public class ProvaService {

    private ProvaDAO provaDAO;

    public ProvaService() {
        provaDAO = new ProvaDAO();
    }

    public boolean inserir(Prova p) {
        try {
            provaDAO.inserir(p);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProvaService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateProva(Prova p) {
        try {

            provaDAO.update(p);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateProvaById(Prova p) {
        try {

            provaDAO.updateProvaById(p);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Prova getProvaById(Long id) {
        try {
            Prova p = provaDAO.getProvaById(id);
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(ProvaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Prova getProvaByIdUsuario(Long id) {
        try {
            Prova p = provaDAO.getProvaByIdUsuario(id);
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(ProvaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Prova();
    }

    public ArrayList<Prova> getProvasByTipoId(Long id) {
        try {
            ArrayList<Prova> provas = provaDAO.getProvasByTipoId(id);
            return provas;
        } catch (SQLException ex) {
            Logger.getLogger(ProvaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Prova> getListAllProvasByIdUsuario(Long id) {
        try {
            ArrayList<Prova> provas = provaDAO.getListAllProvasByIdUsuario(id);
            return provas;
        } catch (SQLException ex) {
            Logger.getLogger(ProvaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Prova> getListProvas() {
        try {
            ArrayList<Prova> provas = provaDAO.getListProvas();
            return provas;
        } catch (SQLException ex) {
            Logger.getLogger(ProvaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

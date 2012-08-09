package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.PapelDAO;
import br.ufc.si.pet.sappe.entidades.Papel;

public class PapelService {

    private final PapelDAO papelDAO = new PapelDAO();

    public Papel getPapelById(Long id) {
        return papelDAO.getById(id);
    }
}

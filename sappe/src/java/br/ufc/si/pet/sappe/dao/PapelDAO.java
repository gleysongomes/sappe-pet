package br.ufc.si.pet.sappe.dao;

import java.sql.SQLException;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Papel;

public class PapelDAO {

    public Papel getById(Long id) {
        Papel papel = null;
        try {
            papel = (Papel) PostGresMapConfig.getSqlMapClient().queryForObject("getPapelById", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return papel;
    }
}

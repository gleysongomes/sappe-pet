/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Perfil;
import java.sql.SQLException;

/**
 *
 * @author ismaily
 */
public class PerfilDAO {

    public void insert(Perfil perfil) throws SQLException {
        Long id = getProxId();
        perfil.setId(id);
        //System.out.println(perfil.getId());
        PostGresMapConfig.getSqlMapClient().insert("addPerfil", perfil);
    }

    public Perfil getById(Long id) throws SQLException {
        return (Perfil) PostGresMapConfig.getSqlMapClient().queryForObject("getPerfilById", id);
    }

    public void delete(Long id) throws SQLException {
        PostGresMapConfig.getSqlMapClient().delete("deletePerfil", id);
    }

    public Perfil getByUsuarioId(Long id) throws SQLException {
        return (Perfil) PostGresMapConfig.getSqlMapClient().queryForObject("getPerfilByUsuarioId", id);
    }

    public void ativarConta(Perfil perfil) throws SQLException {
        PostGresMapConfig.getSqlMapClient().update("ativarPerfil", perfil);
    }

    private Long getProxId() throws SQLException {
        Long id = (Long) PostGresMapConfig.getSqlMapClient().queryForObject("getMaxIdPerfil");
        if (id == null) {
            id = 0L;
        }
        return id + 1L;
    }
    /*
    public Long contaPerfisByUsuarioId(Long id) throws SQLException {
    Long i = (Long) PostGresMapConfig.getSqlMapClient().queryForObject("getQtdPerfisByUsuarioId");
    if (i == null) {
    i = 0L;
    }
    return i;
    }
     */
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.entidades;

import java.util.Date;

/**
 *
 * @author fernando
 */
public class Perfil extends Bean {

    private Usuario usuario;
    private Papel papel;
    private Date dataCriacao;

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

  
    private boolean ativo;
    

    public Perfil() {
        this.usuario = new Usuario();
        this.papel= new Papel();

    }

    public Papel getPapel() {
        return papel;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }
    

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public boolean getStatus() {
        return ativo;
    }

}

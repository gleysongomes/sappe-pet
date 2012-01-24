/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.entidades;

/**
 *
 * @author gleyson
 */
public class Area extends Bean {

    private Integer exame_id;

    public Integer getExame_id() {
        return exame_id;
    }

    public void setExame_id(Integer exame_id) {
        this.exame_id = exame_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String nome;
    


}

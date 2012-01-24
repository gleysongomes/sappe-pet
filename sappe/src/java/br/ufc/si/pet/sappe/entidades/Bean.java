/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.entidades;

import java.io.Serializable;

/**
 *
 * @author Gleyson
 */
public class Bean implements Serializable {

    /**
     *
     */
    protected Long id;

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
}

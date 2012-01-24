/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Escritorio projetos
 */
public interface Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response);

}

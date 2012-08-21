/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.entidades;

/**
 *
 * @author gleyson
 */
public class ResultadoUsuarioSimulado extends Bean{

    private Long simulado_id;
    private Long usuario_id;
    private int respondidas;
    private int brancas;
    private String tempo_prova;
    private int certas;
    private int erradas;

    public int getBrancas() {
        return brancas;
    }

    public void setBrancas(int brancas) {
        this.brancas = brancas;
    }

    public int getCertas() {
        return certas;
    }

    public void setCertas(int certas) {
        this.certas = certas;
    }

    public int getErradas() {
        return erradas;
    }

    public void setErradas(int erradas) {
        this.erradas = erradas;
    }

    public int getRespondidas() {
        return respondidas;
    }

    public void setRespondidas(int respondidas) {
        this.respondidas = respondidas;
    }

    public Long getSimulado_id() {
        return simulado_id;
    }

    public void setSimulado_id(Long simulado_id) {
        this.simulado_id = simulado_id;
    }

    public String getTempo_prova() {
        return tempo_prova;
    }

    public void setTempo_prova(String tempo_prova) {
        this.tempo_prova = tempo_prova;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }
    

    

}

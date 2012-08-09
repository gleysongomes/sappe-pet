/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.entidades;

/**
 *
 * @author gleyson
 */
public class Prova extends Bean {

    private int brancas;
    private Integer certas;
    private String data;
    private Integer erradas;
    private String nome_arquivo;
    private Integer numero_questoes;
    private int respondidas;
    private String tempo_prova;
    private Long tipo_id;
    private Long usuario_id;

    public int getBrancas() {
        return brancas;
    }

    public void setBrancas(int brancas) {
        this.brancas = brancas;
    }

    public Integer getCertas() {
        return certas;
    }

    public void setCertas(Integer certas) {
        this.certas = certas;
    }

    public int getRespondidas() {
        return respondidas;
    }

    public void setRespondidas(int respondidas) {
        this.respondidas = respondidas;
    }

    public String getNome_arquivo() {
        return nome_arquivo;
    }

    public void setNome_arquivo(String nome_arquivo) {
        this.nome_arquivo = nome_arquivo;
    }

    public Long getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(Long tipo_id) {
        this.tipo_id = tipo_id;
    }

    public Integer getErradas() {
        return erradas;
    }

    public void setErradas(Integer erradas) {
        this.erradas = erradas;
    }

    public Integer getNumero_questoes() {
        return numero_questoes;
    }

    public void setNumero_questoes(Integer numero_questoes) {
        this.numero_questoes = numero_questoes;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

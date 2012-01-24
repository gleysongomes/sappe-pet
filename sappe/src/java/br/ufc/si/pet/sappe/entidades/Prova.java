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

    private Long tipo_id;
    private String nome_arquivo;

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

    private Integer numero_questoes;
    private Integer aceitas;

    public Integer getAceitas() {
        return aceitas;
    }

    public void setAceitas(Integer aceitas) {
        this.aceitas = aceitas;
    }

    public Integer getErradas() {
        return erradas;
    }

    public void setErradas(Integer erradas) {
        this.erradas = erradas;
    }
    private Integer erradas;

    public Integer getNumero_questoes() {
        return numero_questoes;
    }

    public void setNumero_questoes(Integer numero_questoes) {
        this.numero_questoes = numero_questoes;
    }

    public int getBranca() {
        return branca;
    }

    public void setBranca(int branca) {
        this.branca = branca;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getRespondida() {
        return respondida;
    }

    public void setRespondida(int respondida) {
        this.respondida = respondida;
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
    private Long usuario_id;
    private int respondida;
    private int branca;
    private String tempo_prova;
    private String data;
}

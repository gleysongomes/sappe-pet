/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.entidades;

/**
 *
 * @author gleyson
 */
public class QuestaoProva extends Bean {

    private Long prova_id;
    private Long questao_id;
    private String nome_arquivo;

    public String getNome_arquivo() {
        return nome_arquivo;
    }

    public void setNome_arquivo(String nome_arquivo) {
        this.nome_arquivo = nome_arquivo;
    }
    public Long getQuestao_id() {
        return questao_id;
    }

    public void setQuestao_id(Long questao_id) {
        this.questao_id = questao_id;
    }

    public String getItem_marcado() {
        return item_marcado;
    }

    public void setItem_marcado(String item_marcado) {
        this.item_marcado = item_marcado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Long getProva_id() {
        return prova_id;
    }

    public void setProva_id(Long prova_id) {
        this.prova_id = prova_id;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    private String nome;
    private String item_marcado;
    private String resposta;
    private Integer status;
    private String dica;

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }
    private int nota;
}

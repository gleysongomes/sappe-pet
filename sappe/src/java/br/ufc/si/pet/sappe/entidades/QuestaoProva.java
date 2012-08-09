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
    private String item_marcado;
    private Integer status;

    public Long getProva_id() {
        return prova_id;
    }

    public void setProva_id(Long prova_id) {
        this.prova_id = prova_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}

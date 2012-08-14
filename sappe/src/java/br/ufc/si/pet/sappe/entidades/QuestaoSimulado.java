/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.entidades;

/**
 *
 * @author gleyson
 */
public class QuestaoSimulado extends Bean{

    private Long simulado_id;
    private Long questao_id;

    public Long getQuestao_id() {
        return questao_id;
    }

    public void setQuestao_id(Long questao_id) {
        this.questao_id = questao_id;
    }

    public Long getSimulado_id() {
        return simulado_id;
    }

    public void setSimulado_id(Long simulado_id) {
        this.simulado_id = simulado_id;
    }

}

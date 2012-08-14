/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.si.pet.sappe.entidades;

/**
 *
 * @author gleyson
 */
public class QuestaoUsuarioSimulado extends Bean{

    private Long simulado_id;
    private Long usuario_id;
    private Long questao_id;
    private int status;

    public Long getQuestao_id() {
        return questao_id;
    }

    public void setQuestao_id(Long questao_id) {
        this.questao_id = questao_id;
    }
    private String item_marcado;

    public String getItem_marcado() {
        return item_marcado;
    }

    public void setItem_marcado(String item_marcado) {
        this.item_marcado = item_marcado;
    }

    public Long getSimulado_id() {
        return simulado_id;
    }

    public void setSimulado_id(Long simulado_id) {
        this.simulado_id = simulado_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }



}

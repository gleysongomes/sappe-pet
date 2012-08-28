/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.entidades;

/**
 *
 * @author gleyson
 */
public class Simulado extends Bean {

    private String nome;
    private String horaini;
    private String horafim;
    private String data;
    private Long exame_id;
    private Integer num_questao;
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getNum_questao() {
        return num_questao;
    }

    public void setNum_questao(Integer num_questao) {
        this.num_questao = num_questao;
    }

    public Long getExame_id() {
        return exame_id;
    }

    public void setExame_id(Long exame_id) {
        this.exame_id = exame_id;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorafim() {
        return horafim;
    }

    public void setHorafim(String horafim) {
        this.horafim = horafim;
    }

    public String getHoraini() {
        return horaini;
    }

    public void setHoraini(String horaini) {
        this.horaini = horaini;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

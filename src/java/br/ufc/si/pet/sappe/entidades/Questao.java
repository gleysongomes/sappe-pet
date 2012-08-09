/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.entidades;

/**
 *
 * @author Gleyson
 */
public class Questao extends Bean {

    private Integer area_id;

    /**
     *
     * @return
     */
    public String getAno() {
        return ano;
    }

    /**
     *
     * @param ano
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     *
     * @return
     */
    public Integer getArea_id() {
        return area_id;
    }

    /**
     *
     * @param area_id
     */
    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public Integer getexame_id() {
        return exame_id;
    }

    /**
     *
     * @param exame_id
     */
    public void setexame_id(Integer exame_id) {
        this.exame_id = exame_id;
    }
    private Integer exame_id;
    private String nome;
    private String ano;
}

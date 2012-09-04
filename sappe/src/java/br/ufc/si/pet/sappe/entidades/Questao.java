/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.entidades;

import java.io.InputStream;

/**
 *
 * @author Gleyson
 */
public class Questao extends Bean {

    private Integer area_id;
    private String item;
    private Integer exame_id;
    private String nome;
    private String ano;
    private InputStream  arquivo;

    public InputStream getArquivo() {
        return arquivo;
    }

    public void setArquivo(InputStream arquivo) {
        this.arquivo = arquivo;
    }
    


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Integer getArea_id() {
        return area_id;
    }

    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getexame_id() {
        return exame_id;
    }

    public void setexame_id(Integer exame_id) {
        this.exame_id = exame_id;
    }

    public Integer getExame_id() {
        return exame_id;
    }

    public void setExame_id(Integer exame_id) {
        this.exame_id = exame_id;
    }
}

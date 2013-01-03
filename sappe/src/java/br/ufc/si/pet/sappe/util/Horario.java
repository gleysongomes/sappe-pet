/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author gleyson
 */
public class Horario {

    private boolean vdataini(int ano, int mes, int dia, int data_ano, int data_mes, int data_dia) {

        return ((ano > data_ano)
                || (ano == data_ano && mes >= data_mes && dia >= data_dia)
                || (ano == data_ano && mes > data_mes && dia < data_dia));
    }

    private boolean vdatater(int ano, int mes, int dia, int data_ano, int data_mes, int data_dia) {

        return ((ano < data_ano)
                || (ano == data_ano && mes <= data_mes && dia <= data_dia)
                || (ano == data_ano && mes < data_mes && dia > data_dia));
    }

    private boolean vhoraini(int hora, int minuto, int segundo, int horario_hora, int horario_minuto, int horario_segundo) {

        return ((hora > horario_hora)
                || (hora == horario_hora && minuto >= horario_minuto && segundo >= horario_segundo)
                || (hora == horario_hora && minuto > horario_minuto && segundo < horario_segundo));
    }

    private boolean vhorater(int hora, int minuto, int segundo, int horario_hora, int horario_minuto, int horario_segundo) {

        return ((hora < horario_hora)
                || (hora == horario_hora && minuto <= horario_minuto && segundo <= horario_segundo)
                || (hora == horario_hora && minuto < horario_minuto && segundo > horario_segundo));
    }

    /*
     * verificar(data de início, horário de início, data de término, horário de término, data e horário atual)
     * formato da data: dd/mm/yyyy
     * formato da horário: hh:mm:ss
     *
     */
    public boolean verificar(String di, String hi, String dt, String ht, Date dha) {

        int data_inicio_dia = Integer.parseInt(di.substring(0, 2));
        int data_inicio_mes = Integer.parseInt(di.substring(3, 5));
        int data_inicio_ano = Integer.parseInt(di.substring(6, 10));

        int data_termino_dia = Integer.parseInt(dt.substring(0, 2));
        int data_termino_mes = Integer.parseInt(dt.substring(3, 5));
        int data_termino_ano = Integer.parseInt(dt.substring(6, 10));

        int horario_inicio_hora = Integer.parseInt(hi.substring(0, 2));
        int horario_inicio_minuto = Integer.parseInt(hi.substring(3, 5));
        int horario_inicio_segundo = Integer.parseInt(hi.substring(6, 8));

        int horario_termino_hora = Integer.parseInt(ht.substring(0, 2));
        int horario_termino_minuto = Integer.parseInt(ht.substring(3, 5));
        int horario_termino_segundo = Integer.parseInt(ht.substring(6, 8));

        Calendar cal = Calendar.getInstance();
        cal.setTime(dha);
        int ano = cal.get(Calendar.YEAR);
        int mes = 1 + cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        int hora = cal.get(Calendar.HOUR_OF_DAY);
        int minuto = cal.get(Calendar.MINUTE);
        int segundo = cal.get(Calendar.SECOND);

        boolean vdataini = vdataini(ano, mes, dia, data_inicio_ano, data_inicio_mes, data_inicio_dia);
        boolean vdatater = vdatater(ano, mes, dia, data_termino_ano, data_termino_mes, data_termino_dia);
        boolean vhoraini = vhoraini(hora, minuto, segundo, horario_inicio_hora, horario_inicio_minuto, horario_inicio_segundo);
        boolean vhorater = vhorater(hora, minuto, segundo, horario_termino_hora, horario_termino_minuto, horario_termino_segundo);

        return ((vdataini) && (vdatater) && (vhoraini) && (vhorater));

    }
}

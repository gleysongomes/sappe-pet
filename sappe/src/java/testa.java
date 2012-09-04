
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.service.QuestaoService;
import java.util.List;
import sun.applet.Main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mardson
 */
public class testa {

    public static void main(String[] args) {

     QuestaoService qs = new QuestaoService();
        List<Questao> questoes = qs.visualizarQuestoesAnoExame("2002");
        for(Questao elem:questoes){
            System.out.println(elem.getNome());
        }	}// fim do método main


}

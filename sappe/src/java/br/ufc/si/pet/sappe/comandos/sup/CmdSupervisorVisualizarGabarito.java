/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.sup;

import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.QuestaoSimulado;
import br.ufc.si.pet.sappe.entidades.Simulado;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.service.QuestaoSimuladoService;
import br.ufc.si.pet.sappe.service.SimuladoService;
import br.ufc.si.pet.sappe.util.Util;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

/**
 *
 * @author gleyson
 */
public class CmdSupervisorVisualizarGabarito implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            response.setContentType("application/pdf");
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();
            Font fonteCabecalho = new Font(Font.NORMAL, 12, Font.BOLD); /* Será usada no cabeçalho. */
            Font fonteDesc = new Font(Font.NORMAL, 11, Font.BOLD); /* Será usada na descrição. */
            Font fonteConteudo = new Font(Font.NORMAL, 12, Font.NORMAL); /* Será usada no corpo de relatório. */

            /* Tabela para o cabeçalho. */
            PdfPTable cabecalho = new PdfPTable(2);
            float[] widths = {0.15f, 0.85f};
            cabecalho.setWidthPercentage(90); /* Seta a largura da tabela com relação a página. */
            cabecalho.setWidths(widths);
            cabecalho.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            Image jpg = Image.getInstance("" + new ImageIcon("" + CmdSupervisorVisualizarGabarito.class.getResource("../../images/UFC2.png")));
            cabecalho.addCell(jpg);
            cabecalho.addCell(new Phrase("Universidade Federal do Ceará\n"
                    + "Campus de Quixadá\n" + "Simulador do Ambiente das Provas do\nPoscomp e Enade - SAPPE", fonteCabecalho));
            document.add(cabecalho);
            PdfPTable es = new PdfPTable(1);
            float[] width = {0.85f};
            es.setWidthPercentage(100);
            es.setWidths(width);
            es.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            SimuladoService simuladoService = new SimuladoService();
            Simulado simulado = simuladoService.getSimuladoById(id);

            es.addCell(new Phrase("Gabarito " + simulado.getNome()
                    + ".\nData: " + Util.treatToString(new Date())
                    + ".\n", fonteDesc));
            document.add(es);
            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100);
            widths = new float[]{0.25f};
            table.setWidths(widths);
            table.getDefaultCell().setGrayFill(10f);
            QuestaoService questaoService = new QuestaoService();
            QuestaoSimuladoService quss = new QuestaoSimuladoService();
            List<QuestaoSimulado> quses = quss.getListQuestaoSimuladoByIdSimulado(id);
            int count = 1;
            for (QuestaoSimulado qus : quses) {
                Questao q = questaoService.getQuestaoById(qus.getQuestao_id());
                table.addCell(new Phrase((count++) + ") " + q.getItem() + "\n", fonteConteudo));
            }
            document.add(table);
            PdfPTable es2 = new PdfPTable(1);
            float[] width2 = {0.85f};
            es2.setWidthPercentage(100);
            es2.setWidths(width2);
            es2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            document.add(es2);
            document.close();
            response.setContentLength(baos.size());
            ServletOutputStream out = null;
            try {
                out = response.getOutputStream();
            } catch (IOException ex) {
                ex.printStackTrace();
                hS.setAttribute("erro", "Erro " + ex.getMessage());
                return "/sup/visualizar_resultado_simulado.jsp";
            }
            try {
                baos.writeTo(out);
                out.flush();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                hS.setAttribute("erro", "Erro " + ex.getMessage());
                return "/sup/visualizar_resultado_simulado.jsp";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            hS.setAttribute("erro", "Erro " + ex.getMessage());
            return "/sup/visualizar_resultado_simulado.jsp";
        }
        return "/sup/visualizar_resultado_simulado.jsp";
    }
}

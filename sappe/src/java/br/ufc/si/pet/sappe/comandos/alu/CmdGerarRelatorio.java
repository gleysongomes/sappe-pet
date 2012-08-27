/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Aluno;
import br.ufc.si.pet.sappe.entidades.Area;
import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.QuestaoProva;
import br.ufc.si.pet.sappe.entidades.Tipo;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.AlunoService;
import br.ufc.si.pet.sappe.service.AreaService;
import br.ufc.si.pet.sappe.service.ProvaService;
import br.ufc.si.pet.sappe.service.QuestaoProvaService;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.service.TipoService;
import br.ufc.si.pet.sappe.service.UsuarioService;
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
public class CmdGerarRelatorio implements Comando {

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
            cabecalho.setWidthPercentage(90);
            cabecalho.setWidths(widths);
            cabecalho.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            Image jpg = Image.getInstance("" + new ImageIcon("" + CmdGerarRelatorio.class.getResource("../../images/UFC2.png")));
            cabecalho.addCell(jpg);
            cabecalho.addCell(new Phrase("Universidade Federal do Ceará\n"
                    + "Campus de Quixadá\n" + "Simulador do Ambiente das Provas do\nPoscomp e Enade - SAPPE", fonteCabecalho));
            document.add(cabecalho);

            PdfPTable es = new PdfPTable(1);
            float[] width = {0.85f};
            es.setWidthPercentage(100);
            es.setWidths(width);
            es.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            ProvaService provaService = new ProvaService();
            Prova prova = provaService.getProvaById(id);
            Long tpid = prova.getTipo_id();
            Perfil p = (Perfil) hS.getAttribute("user");
            AlunoService aS = new AlunoService();
            Aluno alu = aS.getAlunoByUsuarioId(p.getUsuario().getId());
            UsuarioService uS = new UsuarioService();

            Usuario u = uS.getUsuarioById(alu.getUsuario().getId());
            String nome = u.getNome();
            String conteudo[] = nome.split(" ");
            TipoService tipoService = new TipoService();
            Tipo tipo = tipoService.getTipoById(prova.getTipo_id());
            es.addCell(new Phrase("Relatório da " + tipo.getNome()
                    + ".\nNome: " + conteudo[0]
                    + ".\nData: " + Util.treatToString(new Date())
                    + ".\n", fonteDesc));
            document.add(es);
            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100);
            widths = new float[]{0.25f};
            table.setWidths(widths);
            table.getDefaultCell().setGrayFill(10f);
            table.addCell(new Phrase("Obs: As questões nullas são consideradas como certas.", fonteConteudo));
            if (tpid == 9L || tpid == 10L) {
                AreaService areaService = new AreaService();
                List<Area> areas = areaService.getAllAreasByExameId(tpid);
                QuestaoProvaService qpS = new QuestaoProvaService();
                QuestaoService questaoService = new QuestaoService();
                for (Area a : areas) {
                    List<QuestaoProva> qps = qpS.getListQuestaoProvaById(prova.getId());
                    int nq = 0, nqc = 0, aid;
                    String item, itemCerto;
                    for (QuestaoProva qp : qps) {
                        Questao q = questaoService.getQuestaoById(qp.getQuestao_id());
                        aid = q.getArea_id();
                        item = q.getItem();
                        itemCerto = qp.getItem_marcado();
                        if (aid == a.getId()) {
                            if (Igual(itemCerto, item)) {
                                nqc++;
                            }
                            nq++;
                        }
                    }
                    table.addCell(new Phrase("Questões de " + a.getNome()
                            + ":\nQuestões Certas: " + nqc
                            + ".\nQuestões Erradas: " + (nq - nqc)
                            + ".\nPercentual de Acerto: " + 100 * nqc / util(nq) + "%"
                            + ".\n", fonteConteudo));
                }
            } else {
                table.addCell(new Phrase("Número de Questões: " + prova.getNumero_questoes()
                        + ".\nQuestões Respondidas: " + prova.getRespondidas()
                        + ".\nQuestões Certas: " + prova.getCertas()
                        + ".\nQuestões Brancas: " + prova.getBrancas()
                        + ".\nQuestões Erradas: " + prova.getErradas(), fonteConteudo));
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
                return "/alu/visualizar_resultado.jsp";
            }
            try {
                baos.writeTo(out);
                out.flush();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                hS.setAttribute("erro", "Erro " + ex.getMessage());
                return "/alu/visualizar_resultado.jsp";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            hS.setAttribute("erro", "Erro " + ex.getMessage());
            return "/alu/visualizar_resultado.jsp";
        }
        return "/alu/visualizar_resultado.jsp";
    }

    private boolean Igual(String a, String b) {
        return ((a == null ? b == null : a.equals(b))) || (b.equals("N")) ? true : false;
    }

    private int util(int n) {
        return n == 0 ? 1 : n;
    }
}

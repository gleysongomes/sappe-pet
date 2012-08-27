/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.sup;

import br.ufc.si.pet.sappe.entidades.Aluno;
import br.ufc.si.pet.sappe.entidades.Area;
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.QuestaoUsuarioSimulado;
import br.ufc.si.pet.sappe.entidades.ResultadoUsuarioSimulado;
import br.ufc.si.pet.sappe.entidades.Simulado;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.AlunoService;
import br.ufc.si.pet.sappe.service.AreaService;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.service.QuestaoUsuarioSimuladoService;
import br.ufc.si.pet.sappe.service.ResultadoUsuarioSimuladoService;
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
public class CmdSupervisorVisualizarResultadoSimuladoAluno implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
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
            Image jpg = Image.getInstance("" + new ImageIcon("" + CmdSupervisorVisualizarResultadoSimuladoAluno.class.getResource("../../images/UFC2.png")));
            cabecalho.addCell(jpg);
            cabecalho.addCell(new Phrase("Universidade Federal do Ceará\n"
                    + "Campus de Quixadá\n" + "Simulador do Ambiente das Provas do\nPoscomp e Enade - SAPPE", fonteCabecalho));
            document.add(cabecalho);
            PdfPTable es = new PdfPTable(1);
            float[] width = {0.85f};
            es.setWidthPercentage(100);
            es.setWidths(width);
            es.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            Simulado simulado = (Simulado) session.getAttribute("sup_simulado2");
            AlunoService aS = new AlunoService();
            Aluno alu = aS.getAlunoByUsuarioId(id);
            UsuarioService uS = new UsuarioService();

            Usuario u = uS.getUsuarioById(alu.getUsuario().getId());
            String nome = u.getNome();
            String conteudo[] = nome.split(" ");
            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100);
            widths = new float[]{0.25f};
            table.setWidths(widths);
            table.getDefaultCell().setGrayFill(10f);
            ResultadoUsuarioSimuladoService rss = new ResultadoUsuarioSimuladoService();
            ResultadoUsuarioSimulado resultadoUsuarioSimulado = new ResultadoUsuarioSimulado();
            resultadoUsuarioSimulado.setSimulado_id(simulado.getId());
            resultadoUsuarioSimulado.setUsuario_id(id);
            ResultadoUsuarioSimulado rus = rss.getResultadoUsuarioSimuladoByUsuarioId(resultadoUsuarioSimulado);
            es.addCell(new Phrase("Relatório " + simulado.getNome()
                    + ".\nNome: " + conteudo[0]
                    + ".\nData: " + Util.treatToString(new Date())
                    + ".\nTempo de Prova: " + rus.getTempo_prova()
                    + ".\n", fonteDesc));
            document.add(es);
            table.addCell(new Phrase("Obs: As questões nullas são consideradas como certas.", fonteConteudo));
            QuestaoUsuarioSimulado questaoUsuarioSimulado = new QuestaoUsuarioSimulado();
            questaoUsuarioSimulado.setSimulado_id(simulado.getId());
            questaoUsuarioSimulado.setUsuario_id(id);
            QuestaoUsuarioSimuladoService quss = new QuestaoUsuarioSimuladoService();
            AreaService areaService = new AreaService();
            List<Area> areas = areaService.getAllAreasByExameId(simulado.getExame_id());
            QuestaoService questaoService = new QuestaoService();
            int soma = 0, soma2 = 0;
            for (Area a : areas) {
                List<QuestaoUsuarioSimulado> quses = quss.getQuestoesUsuarioSimuladoByIdUsuarioESimulado(questaoUsuarioSimulado);
                int nq = 0, nqc = 0, aid;
                String item, itemCerto;
                for (QuestaoUsuarioSimulado qus : quses) {
                    Questao q = questaoService.getQuestaoById(qus.getQuestao_id());
                    aid = q.getArea_id();
                    item = q.getItem();
                    itemCerto = qus.getItem_marcado();
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
                soma += nqc;
                soma2 += nq;
            }

            table.addCell(new Phrase("Percentual de Acerto Geral: " + 100 * soma / util(soma2) + "%.", fonteConteudo));
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
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/sup/visualizar_resultado_simulado.jsp";
            }
            try {
                baos.writeTo(out);
                out.flush();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                session.setAttribute("erro", "Erro " + ex.getMessage());
                return "/sup/visualizar_resultado_simulado.jsp";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            session.setAttribute("erro", "Erro " + ex.getMessage());
            return "/sup/visualizar_resultado_simulado.jsp";
        }
        return "/sup/visualizar_resultado_simulado.jsp";
    }

    private boolean Igual(String a, String b) {
        return ((a == null ? b == null : a.equals(b))) || (b.equals("N")) ? true : false;
    }

    private int util(int n) {
        return n == 0 ? 1 : n;
    }
}

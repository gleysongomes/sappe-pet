/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.sup;

import br.ufc.si.pet.sappe.entidades.Aluno;
import br.ufc.si.pet.sappe.entidades.QuestaoUsuarioSimulado;
import br.ufc.si.pet.sappe.entidades.Simulado;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.AlunoService;
import br.ufc.si.pet.sappe.service.QuestaoUsuarioSimuladoService;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            //String men = "" + request.getSession().getServletContext().getRealPath("");
            //String conteudo3[] = men.split("/build/web");
            //Image jpg = Image.getInstance("" + new ImageIcon(
                    //"" + conteudo3[0] + "/web/images/UFC2.png"));
            Image jpg = Image.getInstance("" + new ImageIcon(""+CmdSupervisorVisualizarResultadoSimuladoAluno.class.getResource("../../images/UFC2.png")));
            cabecalho.addCell(jpg);
            cabecalho.addCell(new Phrase("Universidade Federal do Ceará\n"
                    + "Campus de Quixadá\n" + "Simulador do Ambiente das Provas do\nPoscomp e Enade - SAPPE", fonteCabecalho));
            document.add(cabecalho);

            //Font fonteDesc = new Font(Font.HELVETICA, 10, Font.BOLD);
            //float[] widths = {0.15f, 0.85f};
            PdfPTable es = new PdfPTable(1);
            float[] width = {0.85f};
            es.setWidthPercentage(100);
            es.setWidths(width);
            es.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            //ProvaService provaService = new ProvaService();
            //Prova prova = provaService.getProvaById(id);
            //Long tpid = prova.getTipo_id();
            //Perfil p = (Perfil) hS.getAttribute("user");

            //SimuladoService simuladoService = new SimuladoService();
            //Simulado simulado = simuladoService.getSimuladoById(idS);
            Simulado simulado = (Simulado) hS.getAttribute("sup_simulado2");
            AlunoService aS = new AlunoService();
            Aluno alu = aS.getAlunoByUsuarioId(id);
            UsuarioService uS = new UsuarioService();

            Usuario u = uS.getUsuarioById(alu.getUsuario().getId());
            String nome = u.getNome();
            String conteudo[] = nome.split(" ");
            es.addCell(new Phrase("Relatório " + simulado.getNome()
                    + ".\nNome: " + conteudo[0]
                    + ".\nData: " + Util.treatToString(new Date())
                    + ".\n", fonteDesc));
            document.add(es);
            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100);
            widths = new float[]{0.25f};
            table.setWidths(widths);
            table.getDefaultCell().setGrayFill(10f);
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "postgres");
            QuestaoUsuarioSimulado questaoUsuarioSimulado = new QuestaoUsuarioSimulado();
            questaoUsuarioSimulado.setSimulado_id(simulado.getId());
            questaoUsuarioSimulado.setUsuario_id(id);
            QuestaoUsuarioSimuladoService quss = new QuestaoUsuarioSimuladoService();
            List<QuestaoUsuarioSimulado> quses = quss.getQuestoesUsuarioSimuladoByIdUsuarioESimulado(questaoUsuarioSimulado);
            int count = 1, pmat = 0, pfuncomp = 0, ptecomp = 0, esi = 0, ens = 0, ecg = 0;
            int pmat2 = 0, pfuncomp2 = 0, ptecomp2 = 0, esi2 = 0, ens2 = 0, ecg2 = 0;
            for (QuestaoUsuarioSimulado qus : quses) {
                PreparedStatement pS = conn.prepareStatement("SELECT area_id, item FROM sappe.questao WHERE id=?");
                pS.setLong(1, qus.getQuestao_id());
                ResultSet rs = pS.executeQuery();
                rs.next();
                int aid = rs.getInt(1);
                String item = rs.getString(2);
                String itemCerto = qus.getItem_marcado();
                switch (aid) {
                    case 1:
                        if (igual(itemCerto, item)) {
                            pmat++;
                        }
                        pmat2++;
                        break;
                    case 2:
                        if (igual(itemCerto, item)) {
                            pfuncomp++;
                        }
                        pfuncomp2++;
                        break;
                    case 3:
                        if (igual(itemCerto, item)) {
                            ptecomp++;
                        }
                        ptecomp2++;
                        break;
                    case 4:
                        if (igual(itemCerto, item)) {
                            esi++;
                        }
                        esi2++;
                        break;
                    case 5:
                        if (igual(itemCerto, item)) {
                            ens++;
                        }
                        ens2++;
                        break;
                    case 6:
                        if (igual(itemCerto, item)) {
                            ecg++;
                        }
                        ecg2++;
                }
                count++;
            }

            if (simulado.getExame_id() == 1) {
                table.addCell(new Phrase("Questões de Matemática"
                        + ":\nQuestões Certas: " + pmat
                        + ".\nQuestões Erradas: " + (pmat2 - pmat)
                        + ".\nPercentual de Acerto: " + 100 * pmat / util(pmat2) + "%"
                        + ".\n\nQuestões de Fundamentos da Computação"
                        + ":\nQuestões Certas: " + pfuncomp
                        + ".\nQuestões Erradas: " + (pfuncomp2 - pfuncomp)
                        + ".\nPercentual de Acerto: " + 100 * pfuncomp / util(pfuncomp2) + "%"
                        + ".\n\nQuestões de Tecnolgia da Computação"
                        + ":\nQuestões Certas: " + ptecomp
                        + ".\nQuestões Erradas: " + (ptecomp2 - ptecomp)
                        + ".\nPercentual de Acerto: " + 100 * ptecomp / util(ptecomp2) + "%"
                        + ".\n\nPercentual de Acerto Geral: " + 100 * (pmat + pfuncomp + ptecomp) / util((pmat2 + pfuncomp2 + ptecomp2)) + "%"
                        + ".\n", fonteConteudo));
            } else {
                table.addCell(new Phrase("Questões de Sistemas de Informação"
                        + ":\nQuestões Certas: " + esi
                        + ".\nQuestões Erradas: " + (esi2 - esi)
                        + ".\nPercentual de Acerto: " + 100 * esi / util(esi2) + "%"
                        + ".\n\nQuestões de Engenharia de Software"
                        + ":\nQuestões Certas: " + ens
                        + ".\nQuestões Erradas: " + (ens2 - ens)
                        + ".\nPercentual de Acerto: " + 100 * ens / util(ens2) + "%"
                        + ".\n\nQuestões de Conhecimentos Gerais"
                        + ":\nQuestões Certas: " + ecg
                        + ".\nQuestões Erradas: " + (ecg2 - ecg)
                        + ".\nPercentual de Acerto: " + 100 * ecg / util(ecg2) + "%"
                        + ".\n\nPercentual de Acerto Geral: " + 100 * (esi + ens + ecg) / util((esi2 + ens2 + ecg2)) + "%"
                        + ".\n", fonteConteudo));
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
                conn.close();
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

    private boolean igual(String a, String b) {
        return (a == null ? b == null : a.equals(b)) ? true : false;
    }

    private int util(int n) {
        return n == 0 ? 1 : n;
    }
}

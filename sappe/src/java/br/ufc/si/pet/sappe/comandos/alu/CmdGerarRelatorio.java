/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Aluno;
import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.entidades.QuestaoProva;
import br.ufc.si.pet.sappe.entidades.Tipo;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.AlunoService;
import br.ufc.si.pet.sappe.service.ProvaService;
import br.ufc.si.pet.sappe.service.QuestaoProvaService;
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
            cabecalho.setWidthPercentage(90); /* Seta a largura da tabela com relação a página. */
            cabecalho.setWidths(widths);
            cabecalho.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            //String men = "" + request.getSession().getServletContext().getRealPath("");
            //String conteudo3[] = men.split("/build/web");
            //Image jpg = Image.getInstance("" + new ImageIcon(
            //"" + conteudo3[0] + "/web/images/UFC2.png"));
            Image jpg = Image.getInstance("" + new ImageIcon("" + CmdGerarRelatorio.class.getResource("../../images/UFC2.png")));
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
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "postgres");
            QuestaoProvaService qpS = new QuestaoProvaService();
            List<QuestaoProva> qPs = qpS.getListQuestaoProvaById(prova.getId());
            int count = 1, pmat = 0, pfuncomp = 0, ptecomp = 0, esi = 0, ens = 0, ecg = 0;
            for (QuestaoProva qP : qPs) {
                PreparedStatement pS = conn.prepareStatement("SELECT area_id, item FROM sappe.questao WHERE id=?");
                pS.setLong(1, qP.getQuestao_id());
                ResultSet rs = pS.executeQuery();
                rs.next();
                int aid = rs.getInt(1);
                String item = rs.getString(2);
                String itemCerto = qP.getItem_marcado();
                switch (aid) {
                    case 1:
                        if (igual(itemCerto, item)) {
                            pmat++;
                        }
                        break;
                    case 2:
                        if (igual(itemCerto, item)) {
                            pfuncomp++;
                        }
                        break;
                    case 3:
                        if (igual(itemCerto, item)) {
                            ptecomp++;
                        }
                        break;
                    case 4:
                        if (igual(itemCerto, item)) {
                            esi++;
                        }
                        break;
                    case 5:
                        if (igual(itemCerto, item)) {
                            ens++;
                        }
                        break;
                    case 6:
                        if (igual(itemCerto, item)) {
                            ecg++;
                        }
                }
                count++;
            }
            table.addCell(new Phrase("Obs*: As questõs nullas são consideradas certas.\n", fonteConteudo));
            if (tpid.intValue() <= 6) {
                table.addCell(new Phrase("Número de Questões: " + prova.getNumero_questoes()
                        + ".\nQuestões Respondidas: " + prova.getRespondidas()
                        + ".\nQuestões Certas: " + prova.getCertas()
                        + ".\nQuestões Brancas: " + prova.getBrancas()
                        + ".\nQuestões Erradas: " + prova.getErradas(), fonteConteudo));
            } else if (tpid.intValue() == 7) {
                table.addCell(new Phrase("Questões de Matemática"
                        + ":\nQuestões Certas: " + pmat
                        + ".\nQuestões Erradas: " + (20 - pmat)
                        + ".\nPercentual de Acerto: " + 100 * pmat / 20 + "%"
                        + ".\n\nQuestões de Fundamentos da Computação"
                        + ":\nQuestões Certas: " + pfuncomp
                        + ".\nQuestões Erradas: " + (30 - pfuncomp)
                        + ".\nPercentual de Acerto: " + 100 * pfuncomp / 30 + "%"
                        + ".\n\nQuestões de Tecnolgia da Computação"
                        + ":\nQuestões Certas: " + ptecomp
                        + ".\nQuestões Erradas: " + (20 - ptecomp)
                        + ".\nPercentual de Acerto: " + 100 * ptecomp / 20 + "%"
                        + ".\n\nPercentual de Acerto Geral: " + 100 * (pmat + pfuncomp + ptecomp) / 70 + "%"
                        + ".\n", fonteConteudo));
            } else if (tpid.intValue() == 8) {
                table.addCell(new Phrase("Questões de Sistemas de Informação"
                        + ":\nQuestões Certas: " + esi
                        + ".\nQuestões Erradas: " + (15 - esi)
                        + ".\nPercentual de Acerto: " + 100 * esi / 15 + "%"
                        + ".\n\nQuestões de Engenharia de Software"
                        + ":\nQuestões Certas: " + ens
                        + ".\nQuestões Erradas: " + (15 - ens)
                        + ".\nPercentual de Acerto: " + 100 * ens / 15 + "%"
                        + ".\n\nQuestões de Conhecimentos Gerais"
                        + ":\nQuestões Certas: " + ecg
                        + ".\nQuestões Erradas: " + (10 - ecg)
                        + ".\nPercentual de Acerto: " + 100 * ecg / 10 + "%"
                        + ".\n\nPercentual de Acerto Geral: " + 100 * (esi + ens + ecg) / 40 + "%"
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
                return "/alu/visualizar_resultado.jsp";
            }
            try {
                baos.writeTo(out);
                out.flush();
                out.close();
                conn.close();
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

    private boolean igual(String a, String b) {
        return (a == null ? b == null : a.equals(b)) || b.equals("N") ? true : false;
    }
}

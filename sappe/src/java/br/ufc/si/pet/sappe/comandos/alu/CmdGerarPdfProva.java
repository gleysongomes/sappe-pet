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
import java.io.InputStream;
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
 * @author welligton
 */
public class CmdGerarPdfProva implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        Prova prova = (Prova) hS.getAttribute("prova2");
        try {
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
            Image jpg = Image.getInstance("" + new ImageIcon(""+CmdGerarPdfProva.class.getResource("../../images/UFC2.png")));
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
            Perfil p = (Perfil) hS.getAttribute("user");
            AlunoService aS = new AlunoService();
            Aluno alu = aS.getAlunoByUsuarioId(p.getUsuario().getId());
            UsuarioService uS = new UsuarioService();
            Usuario u = uS.getUsuarioById(alu.getUsuario().getId());
            String nome = u.getNome();
            String conteudo[] = nome.split(" ");
            TipoService tipoService = new TipoService();
            Tipo tipo = tipoService.getTipoById(prova.getTipo_id());
            es.addCell(new Phrase(tipo.getNome() + "           "
                    + "                    " + "Nome: " + conteudo[0]
                    + "       "
                    + "Data: " + Util.treatToString(new Date()), fonteDesc));
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
            int count = 1;
            for (QuestaoProva qP : qPs) {
                table.addCell(new Phrase("\nQuestão " + count + ":\n", fonteDesc));
                PreparedStatement pS = conn.prepareStatement("SELECT arquivo FROM sappe.questao WHERE id=?");
                pS.setLong(1, qP.getQuestao_id());
                ResultSet rs = pS.executeQuery();
                rs.next();
                InputStream in = rs.getBinaryStream(1);
                byte[] arqBytes = new byte[in.available()];
                in.read(arqBytes, 0, in.available());
                Image jpg2 = Image.getInstance(arqBytes);
                table.addCell(jpg2);
                table.addCell(new Phrase("\na: ( ) b: ( ) c: ( ) d: ( ) e: ( )\n", fonteConteudo));
                count++;
            }
            document.add(table);
            PdfPTable es2 = new PdfPTable(1);
            float[] width2 = {0.85f};
            es2.setWidthPercentage(100);
            es2.setWidths(width2);
            es2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            es2.addCell(new Phrase("\n\n"
                    + "                                                  "
                    + "                                "
                    + "Boa Sorte!!", fonteDesc));
            document.add(es2);
            document.close();
            response.setContentLength(baos.size());
            ServletOutputStream out = null;
            try {
                out = response.getOutputStream();
            } catch (IOException ex) {
                ex.printStackTrace();
                hS.setAttribute("men", "Erro " + ex.getMessage());
                return "/alu/refazer_prova.jsp";
            }
            try {
                baos.writeTo(out);
                out.flush();
                out.close();
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                hS.setAttribute("men", "Erro " + ex.getMessage());
                return "/alu/refazer_prova.jsp";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            hS.setAttribute("men", "Erro " + ex.getMessage());
            return "/alu/refazer_prova.jsp";
        }
        return "/alu/refazer_prova.jsp";
    }
}

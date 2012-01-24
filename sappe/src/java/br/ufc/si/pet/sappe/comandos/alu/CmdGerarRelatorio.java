/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;

import br.ufc.si.pet.sappe.entidades.Aluno;
import br.ufc.si.pet.sappe.service.AlunoService;
import br.ufc.si.pet.sappe.util.Util;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import javax.servlet.ServletOutputStream;
import br.ufc.si.pet.sappe.entidades.Prova;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.ProvaService;
import br.ufc.si.pet.sappe.service.UsuarioService;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import java.awt.BasicStroke;
import java.awt.Color;
import java.net.MalformedURLException;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author gleyson
 */
public class CmdGerarRelatorio implements Comando {

    private static int primeiraColocacao = 0;

    static class CustomBarRenderer3D extends BarRenderer3D {

        /**
         * Creates a new renderer.
         */
        public CustomBarRenderer3D() {
        }

        /**
         * Retorna a coluna vermelha que estao com os valores
         * abaixo da linha da meta.
         *
         * @param row  the series.
         * @param column  the category.
         *
         * @return The item color.
         */
        @Override
        public Paint getItemPaint(int row, int column) {
            CategoryDataset dataset = getPlot().getDataset();
            int value = dataset.getValue(row, column).intValue();
            Color c;
            String stringColor = "";
            if (value == primeiraColocacao || value >= 80) {
                c = Color.GREEN;
                stringColor = String.format("#%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue());
                System.out.println("\n" + stringColor + "<- Verde");
                return Color.decode(stringColor); //cor da coluna acima da meta
            } else {
                if (value >= 20 && value < 50) {
                    c = Color.YELLOW;
                    stringColor = String.format("#%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue());
                    System.out.println("\n" + stringColor + "<-Amarelo");
                    return Color.decode(stringColor);
                } else if (value >= 50 && value < 80) {
                    c = Color.BLUE;
                    stringColor = String.format("#%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue());
                    System.out.println("\n" + stringColor + "<-Amarelo");
                    return Color.decode(stringColor);
                } else {
                    c = Color.RED;
                    stringColor = String.format("#%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue());
                    System.out.println("\n" + stringColor + "<-Vermelho");
                    return Color.decode(stringColor);
                }

            }
        }
    }

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        ProvaService pS = new ProvaService();
        List<Prova> provas = pS.getListProva();
        List<Prova> provas2 = new ArrayList<Prova>();
        provas2.addAll(provas);
        UsuarioService uS = new UsuarioService();
        int aceitas, condicao[] = new int[provas.size()], v = 0;
        //int sort[] = new int[provas.size()];
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Long> idS = new ArrayList<Long>();
        for (Prova p : provas) {
            Usuario u = uS.getUsuarioById(p.getUsuario_id());
            idS.add(u.getId());
            for (Long i : idS) {
                if (i.equals(u.getId())) {
                    condicao[v]++;
                }
            }
            aceitas = 0;
            if (condicao[v] == 1) {
                for (Prova prova : provas2) {
                    if (prova.getUsuario_id().equals(u.getId())) {
                        aceitas += isTrue(prova.getAceitas());
                    }
                }
                if (aceitas > primeiraColocacao) {
                    primeiraColocacao = aceitas;
                }
                dataset.addValue(aceitas,  u.getNome(),  u.getNome());
            }
            v++;
        }

        JFreeChart chart = ChartFactory.createBarChart3D(
                "Estudantes por questões aceitas.", // Título da Janela
                "Estudantes", // Informação do eixo "X"
                "Quantidade de Questões Aceitas", // Informação do eixo "Y"
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
                );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CustomBarRenderer3D renderer = new CustomBarRenderer3D();
        plot.setRenderer(renderer);


        /**
         * Linha da meta
         *
         * 1- Indica o valor da meta
         * 2- Indica a cor da linha
         */
        ValueMarker marker = new ValueMarker(80, new Color(200, 200, 255),
                new BasicStroke(1.0f), new Color(200, 200, 255),
                new BasicStroke(1.0f), 1.0f);
        marker.setLabel("Meta");
        //marker.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        marker.setLabelPaint(Color.BLACK);
        marker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        marker.setLabelTextAnchor(TextAnchor.TOP_LEFT);

        plot.addRangeMarker(marker, Layer.BACKGROUND);

        renderer.setItemLabelsVisible(true);
        renderer.setMaximumBarWidth(0.05); //largura da coluna

        //Configuração da descrição da meta. "Frase", "Fonte", etc...
        CategoryTextAnnotation a = new CategoryTextAnnotation("Meta", "", 80); //Frase, coluna que inicia a frase, altura da frase
        a.setCategoryAnchor(CategoryAnchor.START);
        //a.setFont(new Font("SansSerif", Font.PLAIN, 12));
        a.setTextAnchor(TextAnchor.BOTTOM_LEFT);
        plot.addAnnotation(a);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(NumberFormat.getIntegerInstance());

        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            String msg = "" + request.getSession().getServletContext().getRealPath("");
            String conteudo[] = msg.split("/build/web");
            final File file1 = new File("" + conteudo[0] + "/web/images/relatorio.png");
            ChartUtilities.saveChartAsPNG(file1, chart, 800, 500, info);
        } catch (IOException ex) {
            Logger.getLogger(CmdGerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            response.setContentType("application/pdf");
            Document document = new Document(PageSize.A4.rotate());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();
            document.setPageSize(PageSize.LETTER.rotate());
            Font fonteDesc = new Font(Font.HELVETICA, 12, Font.BOLD);
            //float[] widths = {0.15f, 0.85f};
            PdfPTable es = new PdfPTable(1);
            float[] width = {0.85f};
            es.setWidthPercentage(100);
            es.setWidths(width);
            es.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            Aluno aluno = (Aluno) hS.getAttribute("user");
            AlunoService aS = new AlunoService();
            Aluno alu = aS.getAlunoByUsuarioId(aluno.getId());
            UsuarioService usuarioService = new UsuarioService();
            Usuario usuario = usuarioService.getUsuarioById(alu.getUsuario().getId());
            String nome2 = usuario.getNome();
            String conteudo[] = nome2.split(" ");
            es.addCell(new Phrase("Nome: " + conteudo[0]
                    + "       "
                    + "Data: " + Util.treatToString(new Date()), fonteDesc));
            document.add(es);
            String men = "" + request.getSession().getServletContext().getRealPath("");
            String conteudo3[] = men.split("/build/web");
            try {
                Image png = Image.getInstance("" + new ImageIcon("" + conteudo3[0] + "/web/images/relatorio.png"));
                png.setAlignment(Element.ALIGN_CENTER);
                document.add(png);
                document.close();
                response.setContentLength(baos.size());
                ServletOutputStream out = null;
                out = response.getOutputStream();
                baos.writeTo(out);
                out.flush();
                out.close();
            } catch (BadElementException ex) {
                Logger.getLogger(CmdGerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(CmdGerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CmdGerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (DocumentException ex) {
            Logger.getLogger(CmdGerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private Integer isTrue(Integer aceitas) {
        if (aceitas == null) {
            return 0;
        } else {
            return aceitas;
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.util;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.DateTime;
import org.joda.time.Period;
import sun.misc.BASE64Encoder;

/**
 *
 * @author gleyson
 */
public class Util {

    public static String calcularTempo(String hI, String hT) {
        String horas, minutos, segundos;
        DateTime inicio = new DateTime(hI);
        DateTime datafinal = new DateTime(hT);
        Period periodo = new Period(inicio, datafinal);
        horas = String.valueOf(periodo.getHours());
        minutos = String.valueOf(periodo.getMinutes());
        segundos = String.valueOf(periodo.getSeconds());
        return String.format("%02d:%02d:%02d", Integer.parseInt(horas), Integer.parseInt(minutos), Integer.parseInt(segundos));
        //return horas + ":" + minutos + ":" + segundos;
    }

    public static String criptografar(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(senha.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(digest.digest());
        } catch (NoSuchAlgorithmException ns) {
            ns.printStackTrace();
            return senha;
        }
    }//fim do método

    

    public static String treatToString(Date param) {
        if (param != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String result = sdf.format(param);
            return result;
        }
        return "";
    }

    public static String marcarRadio(String a, String b) {
        if (a == null ? b == null : a.equals(b)) {
            return "checked";
        }
        return "disabled";
    }

    public static String marcarRadio2(String a, String b) {
        if (a == null ? b == null : a.equals(b)) {
            return "checked";
        }
        return "";
    }

    public static String marcarRadio3(Integer a, Integer b) {
        if (a == null ? b == null : a.equals(b)) {
            return "checked";
        }
        return "";
    }

    public static String status(Integer n) {
        if (n == 0) {
            System.out.println("passei - 1");
            return "A ser corrigida...";
        } else if (n == 1) {
            System.out.println("passei - 2");
            return "Aceita.";
        } else {
            System.out.println("passei - 3");
            return "Errada.";
        }
    }

    public static String calcularHorario(String h, String h2) {
        return obterTempo(obterSegundos(h) + obterSegundos(h2));
    }

    public static void calcular() {
        System.out.println(obterTempo(obterSegundos("00:01:00") + obterSegundos("00:01:00")));
    }

    public static long obterSegundos(String hora1) {
        String[] time = hora1.split(":");
        try {
            return Integer.parseInt(time[2]) + (Integer.parseInt(time[1]) * 60) + (Integer.parseInt(time[0]) * 3600);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String obterTempo(long Segundos) {
        return String.format("%02d:%02d:%02d", (Segundos / 3600), (Segundos / 60 % 60), Segundos % 60);
    }

    public static String createRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(Integer.toHexString(random.nextInt()));
        }
        return sb.toString();
    }

    public static Date treatToDate(String param) {
        try {
            int a = Integer.parseInt(param.substring(0, 2));
            if (a < 1 || a > 31) {
                return null;
            }
            int b = Integer.parseInt(param.substring(3, 5));
            if (b < 1 || b > 12) {
                return null;
            }
            if (b == 2 && a > 28) {
                return null;
            }
            if ((b == 4 || b == 6 || b == 9 || b == 11) && a > 30) {
                return null;
            }
            int c = Integer.parseInt(param.substring(6));
            Calendar n = GregorianCalendar.getInstance();
            if (c < 1970 || c > n.get(Calendar.YEAR)) {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
        if (param != null && param.trim().length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date result = sdf.parse(param);
                return result;
            } catch (Exception ex) {
                return null;
            }
        }
        return null;
    }

    public static void criarPastaTemporaria() {
        File dir = new File("/web/temp");
        dir.mkdir();
    }

    public static void excluirPastaTemporaria() {
        File dir = new File("/web/temp");
        dir.mkdir();
        dir.delete();
    }

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        Date data = new Date();
        calendar.setTime(data);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);
        return (h > 9 ? "" + h : 0 + "" + h) + ":" + (m > 9 ? "" + m : 0 + "" + m) + ":" + (s > 9 ? "" + s : 0 + "" + s);
    }

    public static String verificar(String str) {
        return (str == null || str.isEmpty()) ? ("0") : (str);
    }

    public static void main(String args[]) throws IOException, ParseException {
        //File dir = new File("/web/temp");
        //dir.mkdir();

        //System.out.println("====" + "08:00:00".substring(0, 2));
        //System.out.println("====" + "08:00:00".substring(3, 5));
        //System.out.println("====" + "08:00:00".substring(6, 8));
        //SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
        //Date ten = parser.parse("10:00");

        //Date h = parser.parse("02:18:00");
        //Date d = parser.parse("02:16:00");
        //if (d.before(h)) {
        //System.out.println("====" + "08:00:00".substring(0, 2));
        //}
        //SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        ///Date dataAtual = sdf2.parse("" + sdf2.format(new Date()));
        //System.out.println(getTime());
        //Date data = sdf2.parse(new Date().toString());
        //String str=sdf2.format(data);

      //  Format format = new SimpleDateFormat("dd/MM/yyyy");
        //String dataAtual = format.format(new Date());
      //  System.out.println(criptografar("a"));
       // System.out.println(criptografar("DMF1ucDxtqgxw5niaXcmYQ=="));

         System.out.println(comparar("21/02/2013", "22/02/2013", "08:00:00", "23:59:00"));

    }

    public static String getUrl(HttpServletRequest req) {
        String scheme = req.getScheme();             // http
        String serverName = req.getServerName();     // hostname
        int serverPort = req.getServerPort();        // 80
        String url = scheme + "://" + serverName + ":" + serverPort;
        return url;
    }//fim do método

    public static Connection getConexao() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/sappe", "postgres", "postgres");
            return conn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }//fim do método


    public static String getDiretorio(){
        String diretorio = "C:/Users/mardson/Documents/NetBeansProjects/sappe/web/provas";
        return  diretorio;
    }



    public static boolean comparar(String data_inicial,String data_final, String hora_inicial,String hora_final){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");

        
        try{

         long temp = System.currentTimeMillis();
         Date data_atual = new Date(temp);
         SimpleDateFormat f2 = new SimpleDateFormat("dd/MM/yyyy");
         
         Date d3 = f2.parse(data_inicial);
         Date d4 = f2.parse(data_final);

        System.out.println(data_atual);
        System.out.println(d3);
        System.out.println(d4);


         //comparando o dia,mês e ano
         if((data_atual.getTime() >= d3.getTime()) &&  (data_atual.getTime() < d4.getTime())){ //se está no intervalo
            Date d1 = formatter.parse(hora_inicial);
            Date d2 = formatter.parse(hora_final);

            String hha = "" + data_atual.getHours();
            String mma = "" + data_atual.getMinutes();
            String ssa = "" + data_atual.getSeconds();

            
            data_atual.setHours(Integer.parseInt(hha));
            data_atual.setMinutes(Integer.parseInt(mma));
            data_atual.setSeconds(Integer.parseInt(ssa));

            //verifica as horas, minutos e segundos
            if(data_atual.getHours() >= d1.getHours() && data_atual.getHours() <= d2.getHours()){
                if(data_atual.getMinutes() >= d1.getMinutes() && data_atual.getSeconds() >= d1.getSeconds())
                    if(data_atual.getMinutes() <= d2.getMinutes())
                    return true;
                return false;
            }else if(data_atual.getHours() == d2.getHours()){
                if(data_atual.getMinutes() <= d2.getMinutes() && data_atual.getSeconds() <= d2.getSeconds())
                   return true;
            }else{
                return false;
            }
         }else if(data_atual.compareTo(d4) == 1){
            Date d1 = formatter.parse(hora_inicial);
            Date d2 = formatter.parse(hora_final);

            String hha = "" + data_atual.getHours();
            String mma = "" + data_atual.getMinutes();
            String ssa = "" + data_atual.getSeconds();


            data_atual.setHours(Integer.parseInt(hha));
            data_atual.setMinutes(Integer.parseInt(mma));
            data_atual.setSeconds(Integer.parseInt(ssa));

            //verifica as horas, minutos e segundos
            if(data_atual.getHours() >= d1.getHours() && data_atual.getHours() <= d2.getHours()){
                if(data_atual.getMinutes() >= d1.getMinutes() && data_atual.getSeconds() >= d1.getSeconds())
                    if(data_atual.getMinutes() <= d2.getMinutes())
                       return true;
                return false;
            }else if(data_atual.getHours() == d2.getHours()){
                if(data_atual.getMinutes() <= d2.getMinutes() && data_atual.getSeconds() <= d2.getSeconds())
                   return true;
            }else{
                return false;
            }
         }else{
             System.out.println(1);
            return false;
         }      
          
      }
      catch(Exception e){
         System.out.println(e);
      }

        return false;

    }//fim do método
}//fim da classe



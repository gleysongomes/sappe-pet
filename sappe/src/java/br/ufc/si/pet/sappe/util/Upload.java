/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author mardson
 */
public class Upload {

    public boolean Enviar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (ServletFileUpload.isMultipartContent(request)) {

            int cont = 0;

            ServletFileUpload servletFileUpload = new ServletFileUpload(
                    new DiskFileItemFactory());

            List fileItemsList = null;

            try {
                fileItemsList = servletFileUpload.parseRequest(request);
            } catch (FileUploadException e1) {

                System.out.println("erro aqui");
                e1.printStackTrace();
            }

            String optionalFileName = "";
            FileItem fileItem = null;

            Iterator it = fileItemsList.iterator();

            do {

                cont++;

                FileItem fileItemTemp = (FileItem) it.next();

                if (fileItemTemp.isFormField()) {
                    if (fileItemTemp.getFieldName().equals("")) {
                        optionalFileName = fileItemTemp.getString();
                    }
                } else {
                    fileItem = fileItemTemp;
                }

                if (cont != (fileItemsList.size())) {
                    if (fileItem != null) {

                        String fileName = fileItem.getName();

                        if (fileItem.getSize() > 0) {

                            if (optionalFileName.trim().equals("")) {
                                fileName = FilenameUtils.getName(fileName);
                            } else {
                                fileName = optionalFileName;
                            }

                            String dirName = "C:/Users/mardson/Documents/NetBeansProjects/sappe/web/provas/poscomp"; //caminho para o projeto

                            File saveTo = new File(dirName + "/" + fileName);

                            try {
                                fileItem.write(saveTo);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            } while (it.hasNext());
            return true;
        } else {
            return false;
        }
    }//fim do método

    public void salvar_imagen_pasta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(50 * 1024 * 1024);


        try {
            List items = upload.parseRequest(request);
// Processa os itens do upload
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.getFieldName().equals("arq")) {

                    String nome = item.getName();
                    StringBuffer bn = new StringBuffer();
                    bn.append("C:/Users/mardson/Documents/NetBeansProjects/sappe/web/provas/poscomp/");
                    bn.append(nome);
                    File uploadedFile = new File(bn.toString());
                    item.write(uploadedFile);
                }
            }

        } catch (FileUploadException fuex) {
            fuex.getMessage().toString();
        } catch (Exception ex) {
            ex.getMessage().toString();
        }
    }
}

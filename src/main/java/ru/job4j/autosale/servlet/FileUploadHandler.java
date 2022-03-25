package ru.job4j.autosale.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.autosale.model.Ad;
import ru.job4j.autosale.store.HbmStoreAd;
import ru.job4j.autosale.store.SourcePath;
import ru.job4j.autosale.store.StoreAd;

public class FileUploadHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Ad ad = HbmStoreAd.instOf().findAdById(id);
        String name = null;
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                       name = new File(item.getName()).getName();
                        item.write(new File(SourcePath.give() + name));
                    }
                }
                request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
            }

        } else {
            request.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }
        ad.setPhoto(SourcePath.giveSub() + name);
       HbmStoreAd.instOf().updateAd(ad);
        response.sendRedirect(request.getContextPath() + "/index.do");
    }

}
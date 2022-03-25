package ru.job4j.autosale.servlet;

import ru.job4j.autosale.store.SourcePath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.Properties;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String requestedImage = req.getPathInfo();
        String name = req.getParameter("name");
        File image = new File(SourcePath.give(), URLDecoder.decode(requestedImage, "UTF-8"));
        String contentType = getServletContext().getMimeType(image.getName());
        resp.reset();
        resp.setContentType(contentType);
        resp.setHeader("Content-Length", String.valueOf(image.length()));
        Files.copy(image.toPath(), resp.getOutputStream());
    }
}


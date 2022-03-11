package ru.job4j.autosale.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.autosale.model.DriveType;
import ru.job4j.autosale.model.Model;
import ru.job4j.autosale.store.HbmStoreCar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DriveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<DriveType> modelList = HbmStoreCar.instOf().findDrive();
        PrintWriter out = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(modelList);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonString);
        out.flush();
    }
}

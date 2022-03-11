package ru.job4j.autosale.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.autosale.model.Ad;
import ru.job4j.autosale.model.Model;
import ru.job4j.autosale.store.HbmStoreAd;
import ru.job4j.autosale.store.HbmStoreCar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

public class ModelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Model> modelList = HbmStoreCar.instOf().findModel();
        PrintWriter out = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(modelList);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonString);
        out.flush();
    }
}

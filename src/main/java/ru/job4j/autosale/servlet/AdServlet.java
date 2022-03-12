package ru.job4j.autosale.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.autosale.model.Ad;
import ru.job4j.autosale.model.Car;
import ru.job4j.autosale.model.User;
import ru.job4j.autosale.store.HbmStoreAd;
import ru.job4j.autosale.store.HbmStoreCar;
import ru.job4j.autosale.store.StoreAd;
import ru.job4j.autosale.store.StoreCar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class AdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Collection<Ad> list = HbmStoreAd.instOf().findAll();
        System.out.println(list);
        PrintWriter out = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(list);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonString);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String descr = req.getParameter("descr");
        String power = req.getParameter("power");
        String model = req.getParameter("model");
        String body = req.getParameter("body");
        String engine = req.getParameter("engine");
        String drive = req.getParameter("drive");
        User user = (User) req.getSession().getAttribute("user");
        StoreCar storeCar = HbmStoreCar.instOf();
        Car car = storeCar.createCar(power, model, body, engine, drive);
        StoreAd storeAd = HbmStoreAd.instOf();
        Ad ad = Ad.of(descr, car, user, false);
        int id = storeAd.addAd(ad);
        req.setAttribute("id", id);
        req.getRequestDispatcher("addphoto.jsp").forward(req, resp);
    }
}

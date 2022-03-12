package ru.job4j.autosale.servlet;

import ru.job4j.autosale.model.Ad;
import ru.job4j.autosale.store.HbmStoreAd;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Ad ad = HbmStoreAd.instOf().findAdById(id);
        ad.setDone(true);
        HbmStoreAd.instOf().updateAd(ad);
       }
    }

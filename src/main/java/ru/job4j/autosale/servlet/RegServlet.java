package ru.job4j.autosale.servlet;

import ru.job4j.autosale.model.User;
import ru.job4j.autosale.store.HbmStoreUser;
import ru.job4j.autosale.store.StoreUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        if (nonNull(HbmStoreUser.instOf().findUser(name))) {
            req.setAttribute("error", "Пользователь с данным именем уже зарегистрирован");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
            User user = User.of(name, password);
            StoreUser store = HbmStoreUser.instOf();
            store.addUser(user);
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
}
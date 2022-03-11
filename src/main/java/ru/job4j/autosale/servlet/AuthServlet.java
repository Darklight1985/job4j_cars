package ru.job4j.autosale.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.autosale.model.User;
import ru.job4j.autosale.store.HbmStoreUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static java.util.Objects.nonNull;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
         User user = (User) req.getSession().getAttribute("user");
        PrintWriter out = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(user);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonString);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = HbmStoreUser.instOf().findUser(name);
            if (nonNull(user) && user.getPassword().equals(password)) {
                HttpSession sc = req.getSession();
                sc.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/index.html");
            } else {
                req.setAttribute("error", "Не верное имя или пароль");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
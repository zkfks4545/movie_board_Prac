package com.js.board.account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-new")
public class AccountNewC extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request.setAttribute("loginPage", "jsp/account/login.jsp");
        request.setAttribute("content", "jsp/account/newUser.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String id = request.getParameter("id");

        boolean exist = AccountDAO.idCheck(id); // 👉 중요: request 말고 id 넘기기

        if (exist) {
            request.setAttribute("msg", "이미 존재하는 ID");
            request.setAttribute("loginPage", "jsp/account/login.jsp");
            request.setAttribute("content", "jsp/account/newUser.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        AccountDAO.newUser(request);

        response.sendRedirect("user-login");
    }
}
package com.js.board.account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/id-check")
public class NewAccountChkC extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String id = request.getParameter("id");

        boolean exist = AccountDAO.idCheck(id);

        response.setContentType("text/plain; charset=UTF-8");

        if (exist) {
            response.getWriter().write("이미 존재하는 ID");
        } else {
            response.getWriter().write("사용 가능한 ID");
        }
    }
}
package com.js.board.account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-edit")
public class AccountEditC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 수정하는곳으로 보내기


        if (AccountDAO.ADAO.loginCheck(request)){
            request.setAttribute("content", "jsp/account/edit.jsp");
        }else {
            request.setAttribute("content", "jsp/account/login.jsp");
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 수정 하는 일

        if (AccountDAO.ADAO.loginCheck(request)){
            AccountDAO.ADAO.editUser(request);

        }
            // 어디로?

        response.sendRedirect("user-info");


//        request.setAttribute("content", "jsp/account/mypage.jsp");
//        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
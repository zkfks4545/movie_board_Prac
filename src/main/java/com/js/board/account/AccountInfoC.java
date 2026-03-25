package com.js.board.account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountInfoC", value = "/user-info")
public class AccountInfoC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // id 조회하는일 불필요 - 이미 세션에 존재

        //어디로?
        if(AccountDAO.loginCheck(request)){
            request.setAttribute("content", "jsp/account/mypage.jsp");
        }else{
            request.setAttribute("content", "home.jsp");
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 로그인 하는 일


        // 어디로?


    }

    public void destroy() {
    }
}
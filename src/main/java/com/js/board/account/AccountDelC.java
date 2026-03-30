package com.js.board.account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountDelC", value = "/user-del")
public class AccountDelC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 유저 탈퇴
        AccountDAO.ADAO.delUser(request);

        //어디로?
        response.sendRedirect("main");

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 탈퇴 하는 일
        boolean pwMatch = AccountDAO.ADAO.delUser(request);

//        (pwMatch == true)
        if(pwMatch){
            // 어디로?

            //성공했을시
            response.sendRedirect("main");
        }else{
            // 실패했을시
            response.sendRedirect("user-info");
        }
    }

    public void destroy() {
    }
}
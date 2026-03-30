package com.js.board.review;

import com.js.board.account.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReviewDeleteC", value = "/review-del")
public class ReviewDeleteC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //삭제하는일
        ReviewDAO.RDAO.delReview(request);

        //어디로?
        response.sendRedirect("review");
    }

    public void destroy() {
    }
}
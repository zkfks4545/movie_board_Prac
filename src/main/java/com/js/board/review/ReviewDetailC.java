package com.js.board.review;

import com.js.board.account.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReviewDetailC", value = "/review-detail")
public class ReviewDetailC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ReviewDAO.RDAO.getReview(request);

        AccountDAO.ADAO.loginCheck(request);
        request.setAttribute("content", "jsp/review/review_detail.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    public void destroy() {
    }
}
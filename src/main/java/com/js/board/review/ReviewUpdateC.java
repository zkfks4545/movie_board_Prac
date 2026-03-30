package com.js.board.review;

import com.js.board.account.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReviewUpdateC", value = "/review-update")
public class ReviewUpdateC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //하나 조회
        ReviewDAO.RDAO.getReview(request);

        //어디로?
        AccountDAO.ADAO.loginCheck(request);
        request.setAttribute("content", "jsp/review/review_update.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        //수정하는일
        ReviewDAO.RDAO.updateReview(request);

        //어디로
        response.sendRedirect("review-detail?no="+request.getParameter("no"));
    }

    public void destroy() {
    }
}
package com.js.board.review;

import com.js.board.account.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReviewAddC", value = "/review-add")
public class ReviewAddC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //게시글 추가하러



        AccountDAO.ADAO.loginCheck(request);
        request.setAttribute("content", "jsp/review/review_add.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //등록
        ReviewDAO.RDAO.addReview(request);


        //이후에 어디로?
        response.sendRedirect("review");

//        ReviewDAO.RDAO.showAllReview(request);
//        AccountDAO.ADAO.loginCheck(request);
//        request.setAttribute("content", "jsp/review/review.jsp");
//        request.getRequestDispatcher("index.jsp").forward(request, response);

    }


    public void destroy() {
    }
}
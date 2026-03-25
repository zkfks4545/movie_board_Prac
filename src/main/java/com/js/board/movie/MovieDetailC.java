package com.js.board.movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/detail-movie")
public class MovieDetailC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // 클릭한 개체 하나를 조회
        MovieDAO.getMovie(request);

        //어디로 이동?
        request.setAttribute("content", "jsp/movie/movie_detail.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);


    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //등록하는일
        MovieDAO.addMovie(request);

        //어디로?
        response.sendRedirect("movie");

    }

    public void destroy() {
    }
}
package com.js.board.movie;

import com.js.board.account.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MovieC", value = "/movie")
public class MovieC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(request.getParameter("type") != null && request.getParameter("type").equals("d")){
            //삭제하는일
            System.out.println("type");
            System.out.println("no");
            AccountDAO.loginCheck(request);

            MovieDAO.delMovie(request);
            System.out.println("deleted");
            response.sendRedirect("movie");
        }else {

            String p = request.getParameter("p");

            int page = 1;
            if (p != null) {
                page = Integer.parseInt(p);
            }

//            MovieDAO.selectAllMovies(request);
//            MovieDAO.paging(page, request); // ✅ page 변수 사용
            AccountDAO.loginCheck(request);
            MovieDAO.MDAO.selectAllMovies(request);
            MovieDAO.MDAO.paging(page, request);

            request.setAttribute("currentPage", page); // ✅ JSP에 전달

            request.setAttribute("content", "jsp/movie/movie.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
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
package com.js.board.review;

import com.js.board.main.DBManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReviewDAO {

    public static final ReviewDAO RDAO = new ReviewDAO();

    private ReviewDAO() {
    }

    public static void showAllReview(HttpServletRequest request) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from review_test order by r_date";

        try {
            conn = DBManager.connect();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ArrayList<Object> reviews = new ArrayList<>();
            while (rs.next()) {
                int no = rs.getInt("r_no");
                String title = rs.getString("r_title");
                String txt = rs.getString("r_txt");
                Date date = rs.getDate("r_date");

                ReviewVo reviewVo = new ReviewVo();
                reviewVo.setNo(no);
                reviewVo.setTitle(title);
                reviewVo.setTxt(txt);
                reviewVo.setDate(date);
                reviews.add(reviewVo);
            }
            System.out.println(reviews);
            request.setAttribute("reviews", reviews);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBManager.close(conn, pstmt, rs);
        }

    }

    public void addReview(HttpServletRequest request) {
        // 1. 값 or db
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "insert into review_test values"
        +"(review_test_seq.nextval,?,?,sysdate)";

        try {
            conn = DBManager.connect();
            pstmt = conn.prepareStatement(sql);

            request.setCharacterEncoding("UTF-8");

            pstmt.setString(1, request.getParameter("title"));
            pstmt.setString(2, request.getParameter("txt"));

            if (pstmt.executeUpdate() == 1) {
                System.out.println("add review success");
            }

        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            DBManager.close(conn, pstmt, null);
        }


    }
}

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

    private static ArrayList<Object> reviews;

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
            reviews = new ArrayList<>();
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
}

package com.js.board.review;

import com.js.board.main.DBManager;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;

public class ReviewDAO {

    public static final ReviewDAO RDAO = new ReviewDAO();

    public Connection conn = null;

    private ReviewDAO() {
        try {
            conn = DBManager.connect();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ReviewVo> showAllReview(HttpServletRequest request) {
//        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from review_test order by r_date";

        try {
//            conn = DBManager.connect();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ArrayList<ReviewVo> reviews = new ArrayList<>();
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
            return reviews;
//            request.setAttribute("reviews", reviews);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBManager.close(conn, pstmt, rs);
        }

        return null;
    }

    public void addReview(HttpServletRequest request) {
        // 1. 값 or db
//        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "insert into review_test values"
        +"(review_test_seq.nextval,?,?,sysdate)";

        try {
//            conn = DBManager.connect();
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

    public void getReview(HttpServletRequest request) {
//        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from review_test where r_no=?";

        try {
//            conn = DBManager.connect();
            pstmt = conn.prepareStatement(sql);

            String no = request.getParameter("no");

            pstmt.setString(1, no);

            rs = pstmt.executeQuery();

            ReviewVo reviewVo = null;

            if (rs.next()) {
                reviewVo = new ReviewVo();
                reviewVo.setNo(rs.getInt("r_no"));
                reviewVo.setTitle(rs.getString("r_title"));
                reviewVo.setTxt(rs.getString("r_txt"));
                reviewVo.setDate(rs.getDate("r_date"));
            }

            request.setAttribute("review", reviewVo);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
    }

    public void updateReview(HttpServletRequest request) {
//        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "update review_test set r_title=?, r_txt=?, r_date=sysdate where r_no=?";

        try {
//            conn = DBManager.connect();
            pstmt = conn.prepareStatement(sql);

            request.setCharacterEncoding("UTF-8");

            pstmt.setString(1, request.getParameter("title"));
            pstmt.setString(2, request.getParameter("txt"));
            pstmt.setString(3, request.getParameter("no"));

            if (pstmt.executeUpdate() == 1) {
                System.out.println("update review success");
            }

        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            DBManager.close(conn, pstmt, null);
        }
    }

    public void delReview(HttpServletRequest request) {
        // 1. 값 or db
//        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "delete review_test where r_no=?";

        try {
//            conn = DBManager.connect();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,
                    request.getParameter("no"));

            if (pstmt.executeUpdate() == 1) {
                System.out.println("delete review success");
            }

        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            DBManager.close(conn, pstmt, null);
        }
    }

    public void paging(int pageNum, HttpServletRequest req) {
        req.setAttribute("currentPage", pageNum);
        ArrayList<ReviewVo> reviews = showAllReview(req);
        int total = reviews.size();
        int cnt = 5;

        // 페이지수
        int totalPage = (int) Math.ceil((double) total / cnt);
        req.setAttribute("totalPage", totalPage);

        int start = total - (cnt * (pageNum - 1));
        int end = (pageNum == totalPage) ? -1 : start - (cnt + 1);

        ArrayList<ReviewVo> items = new ArrayList<>();
        for (int i = start - 1; i > end; i--) {
            items.add(reviews.get(i));
        }

        req.setAttribute("reviews", items);


    }
}

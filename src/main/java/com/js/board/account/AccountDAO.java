package com.js.board.account;

import com.js.board.main.DBManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {

    public static final AccountDAO ADAO =  new AccountDAO();

    public static boolean loginCheck(HttpServletRequest request) {
        AccountVO user = (AccountVO) request.getSession().
                getAttribute("user");
        if (user == null) {
            request.setAttribute("loginPage",
                    "jsp/account/login.jsp");
            return false;
        } else {
            request.setAttribute("loginPage",
                    "jsp/account/loginOK.jsp");
            return true;
        }
    }

    public static void login(HttpServletRequest request) {
        // 1. 값 or db
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        // db여기에 있는 계정이랑 비교
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from login_test where l_id=?";
        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            String msg;
            if (rs.next()) {
                if (rs.getString("l_pw").equals(pw)) {
                    // 로그인 성공
                    System.out.println("로그인성공!");
                    msg = "로그인 성공";

                    AccountVO accountVO = new AccountVO();
                    accountVO.setId(rs.getString("l_id"));
                    accountVO.setPw(rs.getString("l_pw"));
                    accountVO.setName(rs.getString("l_name"));
//                    request.setAttribute("user",  accountVO);
                    HttpSession hs = request.getSession();
                    hs.setAttribute("user", accountVO);
                    hs.setMaxInactiveInterval(300);
                } else {
                    // 로그인 실패
                    System.out.println("비번에러");
                    msg = "비번 에러";
                }

            } else {
                //유저 없음
                System.out.println("유저 없음");
                msg = "유저없음";
            }
            request.setAttribute("msg", msg);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
        }
    }

    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
//        session.invalidate();     // 모든 세션 초기화
        session.removeAttribute("user");  // user 세션 삭제
//        session.setAttribute("user", null); // user에다가 null을 집어넣음

    }

    public static boolean delUser(HttpServletRequest request) {
        AccountVO user = (AccountVO) request.getSession().getAttribute("user");

        Connection con = null;
        PreparedStatement pstmt = null;

        String sql = "delete from login_test where l_id=?";

        int success = 0;
        try {
            if (user.getPw().equals(request.getParameter("pw"))) {
                con = DBManager.connect();
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, user.getId());
                success = pstmt.executeUpdate();
                if (success == 1) {
                    System.out.println("delete on secure");
                    System.out.println("bye...");
                    logout(request);
                }
                return true;
            } else {
                System.out.println("password not match");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, null);
        }
        return false;
    }

    public static void editUser(HttpServletRequest request) {
        AccountVO user = (AccountVO) request.getSession().getAttribute
                ("user");
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "update login_test set l_pw=?, l_name=? where l_id=?";
        int success = 0;
        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            String pw = request.getParameter("pw");
            if (pw == null) {
                // 기존 비밀번호 가져오기
                pw = user.getPw();
            }
            pstmt.setString(1, pw);

            String name = request.getParameter("name");
            if (name == null) {
                name = user.getName();
            }
            pstmt.setString(2, name);

            pstmt.setString(3, user.getId());

            if (pstmt.executeUpdate() == 1) {
                user.setPw(pw);
                user.setName(name);
                request.getSession().setAttribute("user", user);

                System.out.println("change on secure ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, null);
        }
    }

    public static void newUser(HttpServletRequest request) {
        // 1. 값 or db
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");

        // db여기에 있는 계정이랑 비교
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        String sql = "insert into login_test values (?, ?, ?)";
        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);

            rs = pstmt.executeQuery();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
        }
    }


    public static boolean idCheck(String id) {

        boolean exist = false;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from login_test where l_id=?";

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();

            exist = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
        }

        return exist;
    }
}

package com.js.board.movie;

import com.js.board.main.DBManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MovieDAO {

    public static final MovieDAO MDAO = new MovieDAO();

    private MovieDAO() {
    }

    private static ArrayList<MovieDTO> movies;

    // 전체조회
    public static void selectAllMovies(HttpServletRequest request) {
        // 1. 값 or DB
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from movie_test";

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            MovieDTO dto = null;
            movies = new ArrayList<>();

            while (rs.next()) {
                dto = new MovieDTO();
                dto.setNo(rs.getInt("m_no"));
                dto.setTitle(rs.getString("m_title"));
                dto.setActor(rs.getString("m_actor"));
                dto.setImg(rs.getString("m_img"));
                dto.setStory(rs.getString("m_story"));
                movies.add(dto);
            }
            System.out.println(movies);
            request.setAttribute("movies", movies);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(con,pstmt,rs);
        }

    }

    public static void addMovie(HttpServletRequest request) {
        // 1.값 or db
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "insert into movie_test values" +
                "(movie_test_seq.nextval,?,?,?,?)";
//        String path = request.getServletContext().getRealPath("movieFile");
        String path = "C:\\kim\\dbws_intellij\\upload\\movieFile";
        System.out.println(path);
        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);

//            MultipartRequest mr = new MultipartRequest(request, path,
//                    1024*1024*20, "UTF-8", new DefaultFileRenamePolicy());

            final String[] realName = {null};

            MultipartRequest mr = new MultipartRequest(request, path,
                    1024*1024*20, "UTF-8", new DefaultFileRenamePolicy() {
                @Override
                public File rename(File f) {
                    File result = super.rename(f);
                    realName[0] = result.getName();
                    return result;
                }
            });

            String fileName = realName[0]; // ✅ 실제 저장된 파일명



            String title = mr.getParameter("title");
            String actor = mr.getParameter("actor");
            String story = mr.getParameter("story");
//            String fileName = mr.getFilesystemName("file");

            System.out.println(title);
            System.out.println(actor);
            System.out.println(story);
            System.out.println(fileName);

            story = story.replace("\r\n", "<br>");

            pstmt.setString(1, title);
            pstmt.setString(2, actor);
            pstmt.setString(3, fileName);
            pstmt.setString(4, story);

            if (pstmt.executeUpdate() == 1) {
                System.out.println("added success");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(con,pstmt,null);
        }

    }

    public static void delMovie(HttpServletRequest request) {
        // 1. 값 or DB
        Connection con = null;
        PreparedStatement pstmt = null;

        String sql = "delete from movie_test where m_no = ?";

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(request.getParameter("no")));
            if (pstmt.executeUpdate() == 1) {
                System.out.println("deleted success");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(con,pstmt,null);
        }
    }

    public static void getMovie(HttpServletRequest request) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from movie_test where m_no = ?";

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, request.getParameter("no"));

            rs = pstmt.executeQuery();
            MovieDTO dto = null;

            if (rs.next()) {
                dto = new MovieDTO();
                dto.setNo(rs.getInt("m_no"));
                dto.setTitle(rs.getString("m_title"));
                dto.setActor(rs.getString("m_actor"));
                dto.setImg(rs.getString("m_img"));
                dto.setStory(rs.getString("m_story"));
            }
            System.out.println(dto);
            request.setAttribute("movie", dto);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(con,pstmt,rs);
        }

    }

    public static void upMovie(HttpServletRequest request) {
        //값 or DB
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "update movie_test set m_title=?, m_actor=?, m_story=? where m_no=?";

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);

            request.setCharacterEncoding("UTF-8");

            String title =request.getParameter("title");
            String actor =request.getParameter("actor");
            String story =request.getParameter("story");
            String no = request.getParameter("no");

            story = story.replace("\r\n", "<br>");

            System.out.println(title);
            System.out.println(actor);
            System.out.println(story);
            System.out.println(no);

            pstmt.setString(1, title);
            pstmt.setString(2, actor);
            pstmt.setString(3, story);
            pstmt.setInt(4, Integer.parseInt(no));

            if (pstmt.executeUpdate() == 1) {
                System.out.println("updated success");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(con,pstmt,null);
        }
    }

    public static void upMovie2(HttpServletRequest request) {
        //값 or DB

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "update movie_test set m_title=?, m_actor=?, m_story=?, m_img=? where m_no=?";
//        String path = request.getServletContext().getRealPath("movieFile");
        String path = "C:\\kim\\dbws_intellij\\upload\\movieFile";

        System.out.println(path);
        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);

            request.setCharacterEncoding("UTF-8");

//            MultipartRequest mr = new MultipartRequest(request, path,
//                    1024*1024*20, "UTF-8", new DefaultFileRenamePolicy());

            final String[] realName = {null};

            MultipartRequest mr = new MultipartRequest(request, path,
                    1024*1024*20, "UTF-8", new DefaultFileRenamePolicy() {
                @Override
                public File rename(File f) {
                    File result = super.rename(f); // 실제 rename 수행
                    realName[0] = result.getName(); // a1.jpg 캡처
                    return result;
                }
            });

            String newImg = realName[0]; // ✅ "a1.jpg"
// 파일 없으면 null



            String title = mr.getParameter("title");
            String actor = mr.getParameter("actor");
            String story = mr.getParameter("story");
//            String newImg = mr.getFilesystemName("newImg");
            String oldImg = mr.getParameter("oldImg");
            String img = newImg;
            if (newImg == null){
                img= oldImg;
            }
            String no = mr.getParameter("no");

            System.out.println(title);
            System.out.println(actor);
            System.out.println(story);
            System.out.println(img);
            System.out.println(no);

            story = story.replace("\r\n", "<br>");

            pstmt.setString(1, title);
            pstmt.setString(2, actor);
            pstmt.setString(3, story);
            pstmt.setString(4, img);
            pstmt.setInt(5, Integer.parseInt(no));

            request.setAttribute("noo", no);


            if (pstmt.executeUpdate() == 1) {
                System.out.println("edit success");
                if (newImg != null){    //사진을 교체했다
                    File f = new File(path + "/" + oldImg);
                    f.delete();
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(con,pstmt,null);
        }
    }

    public static void paging(int pageNum, HttpServletRequest request) {

        request.setAttribute("currentPage", pageNum);

//        int total = 11;
        int total = movies.size();
        int cnt = 3;

        //페이지수
        int totalPage = (int)Math.ceil((double)total/cnt);
        request.setAttribute("totalPage", totalPage);

//        int start = total - (cnt * (pageNum - 1));
//        int end = (pageNum == totalPage) ? -1 : start - (cnt + 1);

        int start = total - 1 - (cnt * (pageNum - 1));
        int end = (pageNum == totalPage) ? -1 : start - cnt;

        ArrayList<MovieDTO> items = new ArrayList<>();
        for (int i = start; i > end; i--) {
            items.add(movies.get(i));
        }

        request.setAttribute("movies", items);
    }

}

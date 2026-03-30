package com.js.board.main;

import java.sql.*;

public class DBManager {

    public static Connection connect() throws SQLException {

        //        String url2 = "jdbc:oracle:thin:@eg6skguj9kvbijiu_medium?TNS_ADMIN=C:/mzz/Wallet_EG6SKGUJ9KVBIJIU";
        String url = "jdbc:oracle:thin:@10.1.82.127:1521:XE";

        return DriverManager.getConnection(url, "c##js1004", "js1004");
    }

    public static void close( Connection con, PreparedStatement ps, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(con != null){
//                con.close();
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}

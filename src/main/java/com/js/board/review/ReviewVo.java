package com.js.board.review;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewVo {

    private int no;
    private String title, txt;
    private Date date;


}

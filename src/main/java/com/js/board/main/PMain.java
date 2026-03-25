package com.js.board.main;

public class PMain {
    public static void main(String[] args) {
        System.out.println("11");
        //페이징

        //규칙
        int 총데이터수 = 11;
        int 한페이지당보여줄개수 = 3;


        //페이지수
        int 총페이지수 = (int)Math.ceil((double)총데이터수/한페이지당보여줄개수);
        System.out.println(총페이지수);

        int 페이지번호 = 2;

        int 시작데이터번호 = (페이지번호 - 1) * 한페이지당보여줄개수 + 1;
        int 끝데이터번호 = (페이지번호 == 총페이지수) ? 총데이터수 : 시작데이터번호 + 한페이지당보여줄개수 - 1;

        int 시작데이터번호2 = 총데이터수 - (한페이지당보여줄개수 * (페이지번호 - 1));
        int 끝데이터번호2 = (페이지번호 == 총페이지수) ? -1 : 시작데이터번호2 - (한페이지당보여줄개수 + 1);

    }


}

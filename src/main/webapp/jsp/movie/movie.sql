create table movie_test(
  m_no number(3) primary key ,
  m_title varchar2(20 char) not null ,
  m_actor varchar2(30 char) not null ,
  m_img varchar2(200 char) not null ,
  m_story varchar2(500 char) not null
);

create sequence movie_test_seq;

insert into movie_test values (movie_test_seq.nextval, '매트릭스', '키아누리브스', 'a.jpg', 'SF, DRAMA, 철학');


INSERT INTO movie_test VALUES (movie_test_seq.nextval, '쇼생크 탈출', '팀 로빈스', 'a1.jpg', '감동, 드라마, 인생');
INSERT INTO movie_test VALUES (movie_test_seq.nextval, '포레스트 검프', '톰 행크스', 'a2.jpg', '감동, 드라마, 인생');
INSERT INTO movie_test VALUES (movie_test_seq.nextval, '글래디에이터', '러셀 크로우', 'a3.jpg', '액션, 역사, 드라마');
INSERT INTO movie_test VALUES (movie_test_seq.nextval, '조커', '호아킨 피닉스', 'a4.jpg', '드라마, 범죄, 심리');
INSERT INTO movie_test VALUES (movie_test_seq.nextval, '탑건 매버릭', '톰 크루즈', 'a5.jpg', '액션, 비행, 감동');
INSERT INTO movie_test VALUES (movie_test_seq.nextval, '어바웃 타임', '도널 글리슨', 'a6.jpg', '로맨스, 시간, 감동');
INSERT INTO movie_test VALUES (movie_test_seq.nextval, '위플래쉬', '마일스 텔러', 'a7.jpg', '음악, 드라마, 성장');
INSERT INTO movie_test VALUES (movie_test_seq.nextval, '인사이드 아웃', '에이미 포엘러', 'a8.jpg', '애니, 감정, 가족');
INSERT INTO movie_test VALUES (movie_test_seq.nextval, '알라딘', '메나 마수드', 'a9.jpg', '판타지, 모험, 음악');
INSERT INTO movie_test VALUES (movie_test_seq.nextval, '스파이더맨', '톰 홀랜드', 'a10.jpg', '히어로, 액션, 성장');

select * from movie_test;

delete movie_test where m_no = 25;

truncate table movie_test

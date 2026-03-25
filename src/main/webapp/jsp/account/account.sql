create table login_test(
    l_id varchar2(30 char) primary key,
    l_pw varchar2(30 char) not null,
    l_name varchar2(30 char) not null
);

insert into login_test values ('js', 'js1004', 'jsjs');

insert into login_test values ('id', 'pw', 'name');

select * from login_test;


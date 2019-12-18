바. 상품등록, 수정, 삭제 실습예제

1) 관리자 테이블

drop table admin cascade constraints;
create table admin (
	userid varchar2(50) not null,
	passwd varchar2(50) not null,
	name varchar2(50) not null,
	email varchar2(100),
	join_date date default sysdate,
	primary key(userid)
);

insert into admin (userid, passwd, name) values (userid, passwd, name);
select * from admin;
commit;



drop table memo;
create table memo(
	idx number not null primary key,
	writer varchar2(50) not null,
	memo varchar2(200) not null,
	post_date date default sysdate
);

insert into memo (idx, writer, memo) values (1, 'kim', 'first memo');
insert into memo (idx, writer, memo) values (2, 'park', 'second memo');


drop table product cascade constraints;

clob: 최대 4G 입력가능, 한 테이블에서 여러컬럼에 사용가능, 검색가능
long: 최대 2G 입력가능, 한 테이블에서 1개컬럼만 사용가능, 검색안됨

create table product (
	product_id number,
	product_name varchar2(50),
	price number default 0,
	description clob,
	picture_url varchar2(500),
	primary key(product_id)
);

select * from product where description like '%샘플%';

insert into product values(1, 'lemon', 1500, 'lemon is good', 'lemon.jpg');

select * from product;

commit;

상품코드를 위한 시퀀스 생성
drop sequence seq_product;
create sequence seq_product
start with 10
increment by 1;

insert into product values(seq_product.nextval, 'apple', 'apple is delicious', 'apple.jpg');
commit;

insert into product values (1,'레몬',1500,'레몬에 포함된 구연산은 피로회복에 좋습니다. 비타민 C도 풍부합니다.','lemon.jpg'); 
insert into product values (2,'오렌지',2000,'비타민 C가 풍부합니다. 생과일 주스로 마시면 좋습니다.','orange.jpg');
insert into product values (3,'키위',3000,'비타민 C가 매우 풍부합니다. 다이어트나 미용에 좋습니다.','kiwi.jpg');
insert into product values (4,'포도',5000,'폴리페놀을 다량 함유하고 있어 항산화 작용을 합니다.','grape.jpg');
insert into product values (5,'딸기',8000,'비타민 C나 플라보노이드를 다량 함유하고 있습니다.','strawberry.jpg');
insert into product values (6,'귤',7000,'시네피린을 함유하고 있어 감기 예방에 좋다고 합니다.','tangerine.jpg');



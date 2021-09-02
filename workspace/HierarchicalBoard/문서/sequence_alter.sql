-- https://seras.tistory.com/15

select member_seq.CURRVAL from DUAL; -- 꼭 nextval 해주고 와야 검색이 가능하다..
select * from USER_SEQUENCES;

-- sequence 값 바꾸기
alter sequence member_seq increment by -1; -- 바꾸고 싶은값 넣고
select member_seq.nextval from dual; -- 변동시키고
alter sequence member_seq increment by 1; -- 다시 변동값을 원래대로 바꾸기

select * from member;



-- https://seras.tistory.com/15

select member_seq.CURRVAL from DUAL; -- �� nextval ���ְ� �;� �˻��� �����ϴ�..
select * from USER_SEQUENCES;

-- sequence �� �ٲٱ�
alter sequence member_seq increment by -1; -- �ٲٰ� ������ �ְ�
select member_seq.nextval from dual; -- ������Ű��
alter sequence member_seq increment by 1; -- �ٽ� �������� ������� �ٲٱ�

select * from member;



select * from member;
desc member;
insert into member(memnum,id,pw,memname,memBirth,memTel,memEmail,memPost,memAddress,memAddress_detail,memJdate)
values(MEMBER_SEQ.nextval,'user5','1234','Mcdodo',TO_DATE('03-23-1984','MM-DD-YYYY'),'010-5532-7789','user4@naver.com','21566','����','�ƹ��� 123ȣ',SYSDATE);

insert into member(memnum,id,pw,memname,memBirth,memTel,memEmail,memPost,memAddress,memAddress_detail,memJdate)
values(MEMBER_SEQ.nextval,'user6','1234','seq',TO_DATE('03-23-1984','MM-DD-YYYY'),'010-5532-7789','user4@naver.com','21566','����','�ƹ��� 123ȣ',SYSDATE);

insert into member(memnum,id,pw,memname,memBirth,memTel,memEmail,memPost,memAddress,memAddress_detail,memJdate)
values(MEMBER_SEQ.nextval,'user7','1234','���ڸ�',TO_DATE('03-23-1984','MM-DD-YYYY'),'010-5532-7789','user4@naver.com','21566','����','�ƹ��� 123ȣ',SYSDATE);

insert into member(memnum,id,pw,memname,memBirth,memTel,memEmail,memPost,memAddress,memAddress_detail,memJdate)
values(MEMBER_SEQ.nextval,'user8','1234','����',TO_DATE('03-23-1984','MM-DD-YYYY'),'010-5532-7789','user4@naver.com','21566','����','�ƹ��� 123ȣ',SYSDATE);

insert into member(memnum,id,pw,memname,memBirth,memTel,memEmail,memPost,memAddress,memAddress_detail,memJdate)
values(MEMBER_SEQ.nextval,'user9','1234','��簨ü',TO_DATE('03-23-1984','MM-DD-YYYY'),'010-5532-7789','user4@naver.com','21566','����','�ƹ��� 123ȣ',SYSDATE);

insert into member(memnum,id,pw,memname,memBirth,memTel,memEmail,memPost,memAddress,memAddress_detail,memJdate)
values(MEMBER_SEQ.nextval,'user10','1234','�����',TO_DATE('03-23-1984','MM-DD-YYYY'),'010-5532-7789','user4@naver.com','21566','����','�ƹ��� 123ȣ',SYSDATE);

commit;

select * from member;
select * from board;
select * from reply;
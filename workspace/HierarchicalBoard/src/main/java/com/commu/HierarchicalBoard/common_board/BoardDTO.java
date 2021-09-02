package com.commu.HierarchicalBoard.common_board;

import java.util.Date;

import com.commu.HierarchicalBoard.common_member.MemberDTO;

public class BoardDTO {
	private int bNum;	//일련번호
	private MemberDTO bWriter;	//글쓴이
	
	private String bTitle;	//제목
	private String bContent;	//내용
	private int bView;	//조회수
	
	private int bRef;	//글그룹
	private int bStep;	//원글 0 ,댓글 1~
	private int bRefOrder;	//글그룹 정렬
	private int bAnswerCnt;	//댓글 수
	private int bParentNum;	//원글 일련번호
	private Date bRegdate;	//작성일
	private String bDeleteflag;	//정상, 삭제 , default = 정상
	
	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	public MemberDTO getbWriter() {
		return bWriter;
	}
	public void setbWriter(MemberDTO bWriter) {
		this.bWriter = bWriter;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public int getbView() {
		return bView;
	}
	public void setbView(int bView) {
		this.bView = bView;
	}
	public int getbRef() {
		return bRef;
	}
	public void setbRef(int bRef) {
		this.bRef = bRef;
	}
	public int getbStep() {
		return bStep;
	}
	public void setbStep(int bStep) {
		this.bStep = bStep;
	}
	public int getbRefOrder() {
		return bRefOrder;
	}
	public void setbRefOrder(int bRefOrder) {
		this.bRefOrder = bRefOrder;
	}
	public int getbAnswerCnt() {
		return bAnswerCnt;
	}
	public void setbAnswerCnt(int bAnswerCnt) {
		this.bAnswerCnt = bAnswerCnt;
	}
	public int getbParentNum() {
		return bParentNum;
	}
	public void setbParentNum(int bParentNum) {
		this.bParentNum = bParentNum;
	}
	public Date getbRegdate() {
		return bRegdate;
	}
	public void setbRegdate(Date bRegdate) {
		this.bRegdate = bRegdate;
	}
	public String getbDeleteflag() {
		return bDeleteflag;
	}
	public void setbDeleteflag(String bDeleteflag) {
		this.bDeleteflag = bDeleteflag;
	}
}

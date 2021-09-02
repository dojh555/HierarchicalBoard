package com.commu.HierarchicalBoard.common_reply;

import java.util.Date;

import com.commu.HierarchicalBoard.common_board.BoardDTO;
import com.commu.HierarchicalBoard.common_member.MemberDTO;

public class ReplyDTO {
	private int rpNum;
	private MemberDTO rpWriter;
	private BoardDTO rpBoard;
	private String rpContent;
	private Date rpRegdate;
	
	public int getRpNum() {
		return rpNum;
	}
	public void setRpNum(int rpNum) {
		this.rpNum = rpNum;
	}
	public MemberDTO getRpWriter() {
		return rpWriter;
	}
	public void setRpWriter(MemberDTO rpWriter) {
		this.rpWriter = rpWriter;
	}
	public BoardDTO getRpBoard() {
		return rpBoard;
	}
	public void setRpBoard(BoardDTO rpBoard) {
		this.rpBoard = rpBoard;
	}
	public String getRpContent() {
		return rpContent;
	}
	public void setRpContent(String rpContent) {
		this.rpContent = rpContent;
	}
	public Date getRpRegdate() {
		return rpRegdate;
	}
	public void setRpRegdate(Date rpRegdate) {
		this.rpRegdate = rpRegdate;
	}
}

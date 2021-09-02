package com.commu.HierarchicalBoard.util;

public class Paging {
	private int contentStartNum = 0;//첫번째 내용물의 번호(현재 페이지의)
	private int contentLastNum = 0;//마지막 내용물의 번호(현재 페이지의) 오라클db경우만 사용
    private int blockStartNum = 0;//현재 블럭(blockNum)의 시작 페이지 번호
    private int blockLastNum = 0;//현재 블럭(blockNum)의 끝 페이지 번호
    private int lastPageNum = 0;//마지막 블럭의 끝 페이지번호
    
	public int getContentStartNum() {
		return contentStartNum;
	}
	public void setContentStartNum(int contentStartNum) {
		this.contentStartNum = contentStartNum;
	}
	public int getContentLastNum() {
		return contentLastNum;
	}
	public void setContentLastNum(int contentLastNum) {
		this.contentLastNum = contentLastNum;
	}
	public int getBlockStartNum() {
		return blockStartNum;
	}
	public void setBlockStartNum(int blockStartNum) {
		this.blockStartNum = blockStartNum;
	}
	public int getBlockLastNum() {
		return blockLastNum;
	}
	public void setBlockLastNum(int blockLastNum) {
		this.blockLastNum = blockLastNum;
	}
	public int getLastPageNum() {
		return lastPageNum;
	}
	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}
	
}

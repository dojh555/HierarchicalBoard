package com.commu.HierarchicalBoard.util;

public class PagingFactory {

	public void makeBlock(
			Paging paging/*결과값들을 가지고 나갈 객체*/
			,int curPage/*현재페이지*/
			,int contentCount/*페이지당 내용물 수*/
			,int pageCount/*블럭당 페이지 수*/
			,int total/*내용물 총 수*/
			) {
		
		int blockNum = 0;//몇 번째 블럭인지 나타낼 인수(현재 페이지가)
		int contentStartNum = 0;//첫번째 내용물의 번호(현재 페이지의)
		int lastPageNum = 0;//마지막 페이지 번호(총 게시물 수로 알아내기)
		int contentLastNum = 0;//마지막 내용물의 번호(현재 페이지의) 오라클db경우만 사용,mysql은 contentCount 로 바로 사용하면 됨

		
		//현재페이지까지 축적된 게시물수에 +1 로 현재페이지 시작을 구함
		contentStartNum = (curPage-1) * contentCount + 1;//mysql의 경우 인덱스이므로 -1 해야한다.
		contentLastNum = contentStartNum + (contentCount - 1);
		paging.setContentStartNum(contentStartNum);//첫번째 내용물의 번호 넣기
		paging.setContentLastNum(contentLastNum);//마지막 내용물의 번호 넣기

		
		// 현재 페이지에 (블럭의 마지막페이지는 혼자 +1 된 결과가 되니 -1)
		blockNum = (int) Math.floor((curPage - 1) / pageCount);// '한 블럭에 들어갈 페이지 수'로 나눠 몇 번째 블럭인지 값을 구함
		
		//현재 블럭(blockNum)의 시작 페이지 번호와 끝 페이지 번호 구하기
		int blockStartNum = (pageCount * blockNum) + 1;//한 블럭에 몇개의 페이지가 출력될지는 사용자 지정(pageCount)
		
		int blockLastNum = blockStartNum + (pageCount - 1);
		
		//마지막 블럭의 끝 페이지 번호는, 내용물 총 갯수로 알아본 마지막 페이지 번호와 같아야 한다.
		lastPageNum = (total-1)/contentCount + 1;
		
		paging.setBlockStartNum(blockStartNum);
		paging.setBlockLastNum(blockLastNum);
		paging.setLastPageNum(lastPageNum);
	}
}

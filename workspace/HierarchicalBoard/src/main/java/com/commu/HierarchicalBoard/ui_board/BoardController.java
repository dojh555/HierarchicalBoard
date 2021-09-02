package com.commu.HierarchicalBoard.ui_board;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.commu.HierarchicalBoard.common_board.BoardDTO;
import com.commu.HierarchicalBoard.common_member.MemberDTO;
import com.commu.HierarchicalBoard.common_reply.ReplyDTO;
import com.commu.HierarchicalBoard.util.Paging;
import com.commu.HierarchicalBoard.util.PagingFactory;

@Controller
public class BoardController {
	
	private SqlSession sqlSession;
	
	@Autowired
	public BoardController(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//게시판 리스트가기
	@RequestMapping(value="/board/list")
	public String boardList(String pageNum,Model model) {
		if(pageNum==null) {
			pageNum="1";
		}
		
		Paging paging = new Paging();//결과값들을 가지고 나갈 객체
		int curPage = Integer.parseInt(pageNum);//현재페이지
		int contentCount = 10;//페이지당 내용물 수
		int pageCount = 10;//블럭당 페이지 수
		int total = (Integer)sqlSession.selectOne("board.countBoard");//내용물 총 수
		
		//페이징팩토리 - 페이지 첫 게시물번호, 블럭당 첫 페이지 번호, 블럭당 마지막 페이지 번호
		PagingFactory pagingFactory = new PagingFactory();
		pagingFactory.makeBlock(paging, curPage, contentCount, pageCount, total);
		
		//List<BoardDTO> 가져오기
		int contentStartNum = paging.getContentStartNum();
		int contentLastNum = paging.getContentLastNum();
		
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("contentStartNum", contentStartNum);
		map.put("contentLastNum", contentLastNum);
		List<BoardDTO> list = sqlSession.selectList("board.selectListBoard",map);
		
		model.addAttribute("list",list);
		
		
		//페이징을 위해 값을 넘기기
		int blockLastNum = paging.getBlockLastNum();
		int blockStartNum = paging.getBlockStartNum();
		int lastPageNum = paging.getLastPageNum();
		
		model.addAttribute("total",total);
		model.addAttribute("lastPageNum",lastPageNum);
		model.addAttribute("blockStartNum",blockStartNum);
		model.addAttribute("blockLastNum",blockLastNum);
		model.addAttribute("pageNum",pageNum);
		
		return ".main.board.boardList";
	}//boardList()-end
	
	//게시물 작성폼가기
	@RequestMapping(value="/board/write", method=RequestMethod.GET)
	public String boardWriteForm(Model model,String bNum, 
			String bRef,String bStep , String bRefOrder,String pageNum) {
		
		if(pageNum==null) {//list,content 가 아닌 다른 경로를 통해 바로 글쓰기로 온 경우
			pageNum = "1";
		}
		
		if(bNum==null) {//원글 쓰기
			//게시물 번호보다는 원글,답글 구분을 위한 번호, insert 시 sequece 로 들어가고 이 숫자는 의미 없음.
			bNum = "0";
			//글그룹 = 게시물 최대수 + 1 = 게시물 일련번호
			int group = (Integer)sqlSession.selectOne("board.countBoard")+1;
			bRef = String.valueOf(group);
			
			bStep="0";//글 깊이
			bRefOrder="0";//글순서
		}//if-end
		
		model.addAttribute("pageNum",new Integer(pageNum));//글쓰고 들어온 페이지로 돌려보내기
		
		model.addAttribute("bNum",new Integer(bNum));
		model.addAttribute("bRef",new Integer(bRef));
		model.addAttribute("bStep",new Integer(bStep));
		model.addAttribute("bRefOrder",new Integer(bRefOrder));
		
		return ".main.board.writeForm";
	}//boardWriteForm()-end
	
	//게시물 작성 DB 저장
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String boardWrite(@ModelAttribute("boardDTO")BoardDTO boardDTO,String pageNum,HttpSession session) {
		
		if(boardDTO.getbNum()!=0) {//답글이면
			
			//현재 답글들어갈 위치 다음의 답글들 모두 한자리씩 올라가기
			sqlSession.update("board.updateRefCount",boardDTO);
			boardDTO.setbStep(boardDTO.getbStep()+1);//원글의 다음에
			boardDTO.setbRefOrder(boardDTO.getbRefOrder()+1);//원글의 다음에
		}
		
		boardDTO.setbParentNum(boardDTO.getbNum());//원글이 누군지 설정&후의 리스트 정렬위해 원글에도 자기번호 저장
		//원글 & 답글 공통사항
		//member 테이블 참조값 넣기
		int memNum = (Integer)session.getAttribute("memNum");
		MemberDTO bWriter = new MemberDTO();
		bWriter.setMemNum(memNum);//번호넣고
		
		boardDTO.setbWriter(bWriter);//글쓴이 설정
		sqlSession.insert("board.insertBoard",boardDTO);//저장
		
		return "redirect:/board/content?pageNum="+pageNum+"&bNum="+boardDTO.getbNum();
	}//boardWrite()-end
	
	
	//게시물 상세보기
	@RequestMapping(value="/board/content", method=RequestMethod.GET)
	public String boardContent(String pageNum,int bNum,String uv,String liNum,Model model) {
		
		
		if(uv!=null) {//리스트에서 넘어올 때만 조회수 증가
			sqlSession.update("board.updateView",bNum);
		}
		
		//board 가져오기
		BoardDTO boardDTO = sqlSession.selectOne("board.selectOneBoard",bNum);
		model.addAttribute("dto",boardDTO);//글내용보기에 출력할 정보
		
		//댓글 가져오기
		HashMap<String, Integer> forReMap = new HashMap<String, Integer>();
		int startNum= 1;
		int lastNum = 0;
		
		if(liNum==null) {
			liNum = "0";
		}
		lastNum = Integer.parseInt(liNum) + 10;//댓글 마지막 번호는 <li>의 마지막 갯수 + 10
		
		forReMap.put("bNum", bNum);//게시물 번호
		forReMap.put("startNum", startNum);//댓글 시작번호(시작은 항상 1)
		forReMap.put("lastNum", lastNum);
		
		List<ReplyDTO> replyList = sqlSession.selectList("reply.selectReply",forReMap);
		model.addAttribute("replyList",replyList);
		
		//총 댓글수를 가져와서 0개이면 '더보기' 자체가 안뜨도록.
		int totalReply = sqlSession.selectOne("reply.selectCnt",bNum);
		model.addAttribute("totalReply",totalReply);
		//현재페이지 번호보내기
		model.addAttribute("pageNum",pageNum);
		
		return ".main.board.content";
	}
	
	//게시물 삭제-bDeleteflag와 title을 삭제로 update 하고 끝
	@RequestMapping("/board/delete")
	public String boardDelete(int bNum) {
		sqlSession.update("board.deleteBoard",bNum);
		return "redirect:f/board/list";
	}//boardContent()-end
	
	//게시물 수정 폼 가기
	@RequestMapping(value="/board/update",method=RequestMethod.GET)
	public String boardUpdateForm(Model model,String bNum,String pageNum) {
		
		model.addAttribute("pageNum",pageNum);//있었던 페이지 번호 계속 넘기기

		int num = Integer.parseInt(bNum);
		
		//게시물 가져오기
		BoardDTO boardDTO = sqlSession.selectOne("board.selectOneBoard",num);
		model.addAttribute("dto",boardDTO);
		
	return ".main.board.updateForm";
	}
	
	//게시물 수정 DB 저장
	@RequestMapping(value="/board/update",method=RequestMethod.POST)
	public String boardUpdate(BoardDTO boardDTO,String pageNum) {
		
		sqlSession.update("board.updateBoard",boardDTO);
		
		return "redirect:/board/content?pageNum="+pageNum+"&bNum="+boardDTO.getbNum();
	}
}

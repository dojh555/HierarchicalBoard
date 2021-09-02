package com.commu.HierarchicalBoard.ui_reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commu.HierarchicalBoard.common_board.BoardDTO;
import com.commu.HierarchicalBoard.common_member.MemberDTO;
import com.commu.HierarchicalBoard.common_reply.ReplyDTO;
import com.commu.HierarchicalBoard.util.ReplyHtml;

@Controller
public class ReplyController {
	
	private SqlSession sqlSession;
	
	@Autowired
	public ReplyController(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
//	댓글작성 & 출력
	@RequestMapping("/board/reply/write/{bNum}")
	@ResponseBody
	public Map<String, Object> replyWrite(@RequestBody ReplyDTO replyDTO,
			@PathVariable("bNum") int bNum,
			String liNum,//last번호를 위한 컬럼
			String pageNum,
			HttpSession session) {
		
		
		System.out.println("ReplyController.java replyWrite()");
		
		//1. 댓글 저장
		//reply 객체의 참조변수될 board 에 번호 저장
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setbNum(bNum);//bNum이 아니라 num 인 이유: BoardController 에서도 reply.selectReply 사용하는데 bNum(String)->num(Integer)로 바꿈

		//reply 객체의 참조변수될 member 에 번호 저장
		MemberDTO rpWriter = new MemberDTO();
		int memNum = (Integer)session.getAttribute("memNum");
		rpWriter.setMemNum(memNum);

		replyDTO.setRpBoard(boardDTO);//board 넣기
		replyDTO.setRpWriter(rpWriter);//member 넣기
		sqlSession.insert("reply.insertReply",replyDTO);
		
		//2. 게시판 댓글수 증가
		sqlSession.update("board.upAnswerCnt",bNum);
		
		//3. 게시물에 달린 댓글 수 가져와서 view 에 댓글수 업데이트시키기
		int bAnwserCnt = (Integer)sqlSession.selectOne("board.selectAnswerCnt",bNum);
		
		//4. 댓글 리스트 가져오기
		HashMap<String, Integer> forReMap = new HashMap<String, Integer>();
		int startNum= 1;
		int liCnt = Integer.parseInt(liNum);//보던 댓글 수
		int lastNum = 0;//댓글 출력 마지막 수
		System.out.println(liNum);
		lastNum = liCnt;//10개 단위로 보고있었다면 그대로 새글 포함 10개, 더보기만 활성화.
		
		if(liCnt%10!=0||liCnt==0) {//10개 단위가 아니였다면 보던 글들 모두와 현재글까지 가져오기.
			lastNum = liCnt+1;
		}
		System.out.println("liCnt: "+liCnt);
		System.out.println("lastNum: "+lastNum);
		
		forReMap.put("bNum", bNum);//게시물 번호
		forReMap.put("startNum", startNum);//댓글 시작번호(시작은 항상 1)
		forReMap.put("lastNum", lastNum);
		
		List<ReplyDTO> listReply = sqlSession.selectList("reply.selectReply",forReMap);
		
		//5. 댓글 더보기 출력 여부
		boolean isAddBtn = false;
		if(bAnwserCnt > 10 && liCnt%10==0) {//댓글 추가시 수가 10개 초과하고 보던 댓글이 10개 단위였다면 추가시 더보기 활성화
			isAddBtn = true;
		}//if()-end
		
		//6. 댓글 html 출력
		String html = "";
		
		ReplyHtml replyHtml = new ReplyHtml();//view 에서 출력될 html 만들어줌
		for(ReplyDTO reDto:listReply) {
			html += replyHtml.makeReplyHtml(reDto, memNum,pageNum);
		}
		
		
		//7. ajax 로 데이터 전송
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("isAddBtn",isAddBtn);
		map.put("html",html);
		map.put("bAnswerCnt", bAnwserCnt);//댓글수 갱신
		return map;
	}//replyWrite()-end
	
//	댓글 삭제
	@RequestMapping("/board/reply/delete/{rpNum}")
	public String replyRm(@PathVariable("rpNum")int rpNum,int bNum,int pageNum) {
		
		sqlSession.delete("reply.deleteReply",rpNum);//댓글지우기
		sqlSession.update("board.downAnswerCnt",bNum);//게시물의 댓글 수 -1하기
		//원래 리스트로 돌아가기
		return "redirect:/board/content?pageNum="+pageNum+"&bNum="+bNum;
		
	}//replyRm()-end
	
	
//	댓글 더보기 - Ajax
	@RequestMapping("/board/reply/add/{bNum}")
	@ResponseBody
	public Map<String, Object> replyAdd(@PathVariable("bNum") int bNum,
			@RequestBody HashMap<String, Integer> liMap,
			String pageNum,
			HttpSession session) {
		System.out.println(liMap.get("liNum"));
		System.out.println(bNum);
		//1. 댓글 리스트 가져오기
		HashMap<String, Integer> forReMap = new HashMap<String, Integer>();
		int startNum= 1;//댓글 시작번호(시작은 항상 1)
		int lastNum = 0;//댓글 마지막번호
		int liNum = liMap.get("liNum");//<li>의 총 갯수
		
		lastNum = liNum + 10;//댓글 마지막 번호는 <li>의 총 갯수 + 10
		
		forReMap.put("bNum", bNum);//게시물 번호
		forReMap.put("startNum", startNum);
		forReMap.put("lastNum", lastNum);
		
		//2. 댓글 html 출력
		List<ReplyDTO> listReply = sqlSession.selectList("reply.selectReply",forReMap);
		
		int memNum = 0;//로그인 하지 않았다면 없는 회원 번호로 설정(0번)..삭제/수정 버튼 안보이게 할려고
		if(session.getAttribute("memNum")!=null) {//로그인 한 상태이면 번호가져오기
			memNum = (Integer)session.getAttribute("memNum");
		}
		
		String html = "";
		
		ReplyHtml replyHtml = new ReplyHtml();//view 에서 출력될 html 만들어줌
		for(ReplyDTO reDto:listReply) {
			html += replyHtml.makeReplyHtml(reDto, memNum, pageNum);
		}//for()-end
		
		//3. 댓글 더보기 여부가리기
		int totalReply = sqlSession.selectOne("reply.selectCnt",bNum);//댓글 총 수
		
		boolean isTotal = false;
		if(listReply.size()==totalReply) {//마지막게시물이면: 더보기 -> 접기
			isTotal = true;
		}//if()-end
		
		//4. ajax 로 데이터 전송
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("isTotal",isTotal);
		map.put("html",html);
		
		return map;
		
	}//replyAdd()-end
	
//	댓글 접기 - Ajax
	@RequestMapping("/board/reply/fold/{bNum}")
	@ResponseBody
	public Map<String, Object> replyFold(@PathVariable("bNum") int bNum,String pageNum,HttpSession session){
		
		//1. 댓글 리스트 가져오기
		HashMap<String, Integer> forReMap = new HashMap<String, Integer>();
		int startNum= 1;
		int lastNum = 10;
		
		forReMap.put("bNum", bNum);//게시물 번호
		forReMap.put("startNum", startNum);//댓글 시작번호(시작은 항상 1)
		forReMap.put("lastNum", lastNum);
		List<ReplyDTO> listReply = sqlSession.selectList("reply.selectReply",forReMap);
		
		//2. 댓글 html 출력
		String html = "";
		
		int memNum = 0;//로그인 하지 않았다면 없는 회원 번호로 설정(0번)..삭제/수정 버튼 안보이게 할려고
		if(session.getAttribute("memNum")!=null) {//로그인 한 상태이면 번호가져오기
			memNum = (Integer)session.getAttribute("memNum");
		}
		
		ReplyHtml replyHtml = new ReplyHtml();//view 에서 출력될 html 만들어줌
		for(ReplyDTO reDto:listReply) {
			html += replyHtml.makeReplyHtml(reDto, memNum,pageNum);
		}//for()-end
		
		//3. ajax 로 데이터 전송
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("html",html);
		
		return map;
	}//replyAdd()-end
	
//	댓글 수정
	@RequestMapping("/board/reply/update/{bNum}")
	@ResponseBody
	public Map<String, Object> replyUpdate(@PathVariable("bNum")int bNum,
			@RequestBody ReplyDTO replyDTO,String liNum,
			String pageNum, HttpSession session) {
		//1. 댓글 수정
		sqlSession.update("reply.updateReply",replyDTO);
		
		//2. 댓글 리스트 출력
		HashMap<String, Integer> forReMap = new HashMap<String, Integer>();
		int startNum= 1;
		int lastNum = 0;
		
		if(liNum==null) {
			liNum = "0";
		}
		
		lastNum = Integer.parseInt(liNum);//기존 li 갯수 그대로 보여주기
		forReMap.put("bNum", bNum);//게시물 번호
		forReMap.put("startNum", startNum);//댓글 시작번호(시작은 항상 1)
		forReMap.put("lastNum", lastNum);
		
		List<ReplyDTO> listReply = sqlSession.selectList("reply.selectReply",forReMap);
		
		//3. 댓글 html 출력
		String html = "";
		
		int memNum = 0;
		memNum = (Integer)session.getAttribute("memNum");//다시 html 가져올 때 작성자와 현재 회원이 다를시 삭제/수정 버튼 안보이게 할려고
		
		ReplyHtml replyHtml = new ReplyHtml();//view 에서 출력될 html 만들어줌
		for(ReplyDTO reDto:listReply) {
			html += replyHtml.makeReplyHtml(reDto, memNum, pageNum);
		}//for()-end
		
		//4. ajax 로 데이터 전송
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("html",html);
		return map;
	}//replyUpdate()-end
	
}

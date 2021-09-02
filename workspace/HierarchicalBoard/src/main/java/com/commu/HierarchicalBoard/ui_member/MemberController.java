package com.commu.HierarchicalBoard.ui_member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.commu.HierarchicalBoard.common_member.MemberDTO;

import net.sf.json.JSONObject;

@Controller
public class MemberController {
	
	private SqlSession sqlSession;
	
	@Autowired
	public MemberController(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	//회원가입 폼가기
	@RequestMapping("/member/joinForm")
	public String joinForm() {
		
		return ".main.member.joinForm";
	}
	
	//회원가입하기
	@RequestMapping("/member/join")
	public ModelAndView memJoin(HttpSession session,MemberDTO memberDTO,String test) {
		sqlSession.insert("member.insertMember",memberDTO);
		session.setAttribute("id", memberDTO.getId());
		session.setAttribute("memNum", memberDTO.getMemNum());
		session.setAttribute("memName", memberDTO.getMemName());
		ModelAndView mv = new ModelAndView();
		mv.setViewName(".main.member.memAlert");
		
		mv.addObject("msg","회원가입이 완료되었습니다.");
		return mv;
	}
	
	//사용가능한 아이디 여부
	@RequestMapping("/member/checkId")
	@ResponseBody
	public JSONObject checkId(String id) {
		int check = -1;//사용가능 1, 사용불가능 -1
		
		System.out.println(id);
		if((Integer)sqlSession.selectOne("member.checkId", id)!=0) {
			//결과가 0 일 경우 false, 1 일 경우 if문 실행
			check = -1;
		}else {
			check = 1;
		}
		System.out.println(check);
		JSONObject data = new JSONObject(); // date라는 이름의 JSON 객체
		data.put("check",check);
		
		return data;
	}
	
	//로그인폼 가기
	@RequestMapping("/member/loginForm")
	public String loginForm() {
		
		return ".main.member.loginForm";
	}
	
	//로그인 가능 여부
	@RequestMapping("/member/loginCheck")
	@ResponseBody
	public JSONObject loginPro(MemberDTO memberDTO,HttpSession session) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", memberDTO.getId());
		map.put("pw", memberDTO.getPw());
		MemberDTO member = sqlSession.selectOne("member.selectLogin", map);
		
		JSONObject data = new JSONObject(); // date라는 이름의 JSON 객체
		int check = -1;
		
		if(member!=null) {//회원 가입한 회원
			check = 1;
			session.setAttribute("id", memberDTO.getId());
			session.setAttribute("memNum", member.getMemNum());
			session.setAttribute("memName", member.getMemName());
		}else {//회원 가입하지 않은 회원
			check = -1;
		}
		
		data.put("check", check);
		return data;
	}
	
	//로그아웃하기
	@RequestMapping("/member/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String msg = null;
		if(session!=null&&session.getAttribute("memNum")!=null) {
			session.removeAttribute("id");
			session.removeAttribute("memNum");
			session.removeAttribute("memName");
			msg = "로그아웃 되었습니다.";
		}else if(session.getAttribute("memNum")==null){
			msg = "정상적인 처리가 아닙니다.";
		}
		mv.addObject("msg",msg);
		mv.setViewName(".main.member.memAlert");
		return mv;
	}
	
	//회원정보 페이지 가기
	@RequestMapping("/member/info")
	public String meminfoForm(HttpSession session,Model model) {
		int memNum = (Integer)session.getAttribute("memNum");
		MemberDTO memberDTO = sqlSession.selectOne("member.selectAllMember",memNum);
		
		//2000-01-01 00:00:00
		String memBirth = memberDTO.getMemBirth();
		memBirth = memBirth.substring(0,10);
		memberDTO.setMemBirth(memBirth);
		
		model.addAttribute("dto",memberDTO);
		return ".main.member.infoMember";
	}
	
	//수정폼 패스워드 인증
	@RequestMapping("/member/checkPw")
	@ResponseBody
	public JSONObject checkPw(String pw,HttpSession session) {
		int memNum = (Integer)session.getAttribute("memNum");
		String pw1 = sqlSession.selectOne("member.selectPw", memNum);
		
		JSONObject data = new JSONObject(); // date라는 이름의 JSON 객체
		int check = -1;
		
		if(pw.equals(pw1)) {//비밀번호 일치
			check = 1;
		}else {//비밀번호 불일치
			check = -1;
		}
		
		data.put("check", check);
		return data;
	}
	
	//회원정보 수정폼 가기
	@RequestMapping("/member/infoForm")
	public String infoForm(HttpSession session,Model model) {
		int memNum = (Integer)session.getAttribute("memNum");
		MemberDTO memberDTO = sqlSession.selectOne("member.selectAllMember",memNum);
		
		//example@daum.net
		String email = memberDTO.getMemEmail();
		int idx=email.indexOf("@");
		String email1 = email.substring(0,idx);//example
		String email2 = email.substring(idx);//daum.net
		
		model.addAttribute("email1",email1);
		model.addAttribute("email2",email2);
		
		//010-2345-6789 
		if(memberDTO.getMemTel()!=null) {//휴대전화는 선택사항
			String tel = memberDTO.getMemTel();
			String tel1 = tel.substring(0,3);//010
			String tel2 = tel.substring(4,8);//2345
			String tel3 = tel.substring(9);//6789
			
			model.addAttribute("tel1",tel1);
			model.addAttribute("tel2",tel2);
			model.addAttribute("tel3",tel3);
		}
		
		//2000-01-01 00:00:00
		if(memberDTO.getMemBirth()!=null) {//생년월일은 선택사항
			String memBirth = memberDTO.getMemBirth();
			memBirth = memBirth.substring(0,10);
			memberDTO.setMemBirth(memBirth);
		}
		
		model.addAttribute("dto",memberDTO);
		
		return ".main.member.infoForm";
	}
	
	//회원정보 수정하기
	@RequestMapping("/member/update")
	public String updateMem(MemberDTO memberDTO,HttpSession session) {
		int memNum = (Integer)session.getAttribute("memNum");
		memberDTO.setMemNum(memNum);
		sqlSession.update("member.updateMember",memberDTO);
		
		return "redirect:/member/info";
	}
	
	//회원 탈퇴하기
	@RequestMapping("/member/delete")
	public String deleteMem(HttpSession session) {
		
		int memNum = (Integer)session.getAttribute("memNum");
		sqlSession.delete("member.deleteMember",memNum);
		
		session.removeAttribute("memNum");
		session.removeAttribute("memName");
		session.removeAttribute("id");
		return "redirect:/board/list";
	}
	

}

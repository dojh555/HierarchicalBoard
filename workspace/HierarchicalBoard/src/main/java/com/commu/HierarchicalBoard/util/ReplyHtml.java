package com.commu.HierarchicalBoard.util;

import com.commu.HierarchicalBoard.common_reply.ReplyDTO;

public class ReplyHtml {
	
	public String makeReplyHtml(ReplyDTO reDto, int memNum,String pageNum) {
		String html = "";
		
		String memName = reDto.getRpWriter().getMemName();//각 댓글의 글쓴이 이름
		int rpMemNum = reDto.getRpWriter().getMemNum();//각 댓글의 글쓴 회원의 일련번호
		int bNum = reDto.getRpBoard().getbNum();//게시물번호
		
		html += "<li id=\"comment"+reDto.getRpNum()+"\">\r\n" + 
				"<div class=\"author-meta\">\r\n" + 
				"<span class=\"nickname\">"+
				memName+"</span>\r\n" + "<span class=\"date\">" + reDto.getRpRegdate() +
				"</span>\r\n" + 
				"</div>\r\n" + 
				"<div id=\"rp_contentBox"+reDto.getRpNum()+"\"><p id=\"commentContent"+reDto.getRpNum()+"\">"+reDto.getRpContent()+"</p>\r\n</div>\r\n" + 
				"<div class=\"control\">\r\n";
				
				//로그인한 상태이며 댓글작성자가 현재 로그인 회원가 같을 시 삭제 수정 가능
				if(rpMemNum == memNum) {
					
					html +="<a href=\"#\" onclick=\"updateComment("+bNum+","+reDto.getRpNum()+");return false;\">수정</a>\r\n" + 
							"<a href=\"#\" onclick=\"deleteComment("+reDto.getRpNum()+","+bNum+","+pageNum+");return false;\">삭제</a>\r\n";
				}
		
		html +=	"</div>\r\n";
		html += "<button class=\"underTxtBtn"+reDto.getRpNum()+"\" id=\"updateBtn\" onclick=\"updateReply("+bNum+","+reDto.getRpNum()+","+pageNum+")\">수정하기</button>";
		html += "<button class=\"underTxtBtn"+reDto.getRpNum()+"\" id=\"cancelBtn\" onclick=\"cancelUpdate("+reDto.getRpNum()+")\">취소하기</button>";
		html += "</li>";
		
		return html;
	}
}

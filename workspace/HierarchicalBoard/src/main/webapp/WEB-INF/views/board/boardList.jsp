<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function goWriteForm(){
		//var memNum = ${memNum};
		
		location.href='/board/write';
/* 		if(memNum!=null){
			location.href='/board/write';
		}else{
			alert('로그인이 필요한 서비스입니다.');
			location.href='/member/loginForm';
		} */
	}

</script>

	<h2 class="tit-board">글목록(전체글)</h2>
	<div class="btn-box">
		<button onclick="goWriteForm()" class="btn-item">글쓰기</button>
	</div>
	<c:if test="${total==0}">
		<p style="text-align: center">저장된 글이 없습니다.</p>
	</c:if>
	<!-- 글이 존재하면 -->
	<c:if test="${total!=0}">
		<table width="80%" border="1">
		<tr class="con-block">
			<td>글번호</td>
			<td>글제목</td>
			<td>글쓴이</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="dto" items="${list}">
			<tr>
				<!-- 글번호 -->
				<td>${dto.bNum}</td>
				<!-- 글제목 -->
				<td>
					<!-- 답글이면 이미지 넣는방법 wepapp/resources/imgs 폴더를 만든다. -->
					<c:if test="${dto.bStep>0}">
						<!-- 공백그림-댓글..대댓글 순으로 점점 width 가 길어져 들여쓰기가 된다. -->
						<img src="/resources/imgs/level.gif" width="${5*dto.bStep}" height="16">
						<img src="/resources/imgs/re.gif">
					</c:if>
					
					<!-- 원글 -->
					<c:if test="${dto.bStep==0}">
						<img src="/resources/imgs/level.gif" width="${5*dto.bStep}" height="16" >
					</c:if>
					
					<!-- 글제목을 클릭 > 글내용보기로 간다. -->
					<c:if test="${dto.bDeleteflag eq '정상'}">
					<a href="/board/content?bNum=${dto.bNum}&pageNum=${pageNum}&uv=0">${dto.bTitle}<span class="small_anno"> (${dto.bAnswerCnt})</span></a>
					</c:if>
					<c:if test="${dto.bDeleteflag ne '정상'}">
					${dto.bTitle}<span class="small_anno"> (${dto.bAnswerCnt})</span>
					</c:if>
					<!-- 조회수가 20번 이상이면 hot.gif 표시 -->
					 <c:if test="${dto.bView>=20}">
					 	<img src="/resources/imgs/hot.gif">
					 </c:if>
				</td>
				<!--//글제목 -->
				
				<!-- 회원 이름 -->
				<c:if test="${empty dto.bWriter.memName}">
					<td class="small_anno">탈퇴한 회원</td>
				</c:if>
				
				<c:if test="${!empty dto.bWriter.memName}">
				<td>${dto.bWriter.memName}</td>
				</c:if>
				<!-- 회원 이름 -->
				
				<td><fmt:formatDate value="${dto.bRegdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				
				<td>${dto.bView}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<!-- 블럭,페이지 처리 -->
	<table width="80%">
		<tr>
			<td align="center">
			
			<!-- 이전블럭 -->
			<c:if test="${blockStartNum>10}">
				<a href="/board/list?pageNum=${blockStartNum-1}">이전블럭</a>
			</c:if>
			
			<!-- 페이지처리 -->
			<c:forEach var="i" begin="${blockStartNum}" end="${blockLastNum}">
			<c:choose>
			
				<c:when test="${pageNum==i}">
					<b>[${i}]</b>
				</c:when>
				<c:when test="${i<=lastPageNum}">
					<a href="/board/list?pageNum=${i}">[${i}]</a>
				</c:when>
			</c:choose>
			</c:forEach>
			
			<!-- 다음블럭 -->
			<c:if test="${blockLastNum<lastPageNum}">
				<a href="/board/list?pageNum=${blockLastNum+1}">다음블럭</a>
			</c:if>
			
			</td>
		
		</tr>
	</table>

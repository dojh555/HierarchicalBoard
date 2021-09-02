<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<h2>글내용 보기</h2>
	
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>${dto.bNum}</td>
			<td>조회수</td>
			<td>${dto.bView}</td>
		</tr>
		
		<tr>
			<td>이름</td>
			<td>${dto.bWriter.memName}</td>
			<td>작성일</td>
			<td><fmt:formatDate value="${dto.bRegdate}" pattern="yyyy-MM-dd"/></td>
		</tr>
		
		<tr>
			<td>글제목</td>
			<td colspan="3">${dto.bTitle}</td>
		</tr>
		
		<tr>
			<td>글내용</td>
			<td colspan="3" class="textareaBox">
				<textarea readonly="readonly">${dto.bContent}</textarea>
			</td>
		</tr>
		
	</table>
	<div class="btn-box">
		<input type="button" value="글수정" class="btn-item" onclick="location.href='/board/update?bNum=${dto.bNum}&pageNum=${pageNum}'">
		<c:if test="${dto.bWriter.memNum eq memNum}">
		<input type="button" value="글삭제" class="btn-item" onclick="return deleteBoard(${dto.bNum})">
		</c:if>
		<input type="button" value="새글쓰기" class="btn-item" onclick="location.href='/board/write?pageNum=${pageNum}'">
		<input type="button" value="답글쓰기" class="btn-item" onclick="location.href='/board/write?bNum=${dto.bNum}&bRef=${dto.bRef}&bStep=${dto.bStep}&bRefOrder=${dto.bRefOrder}&pageNum=${pageNum}'">
		<input type="button" value="글목록" class="btn-item" onclick="location.href='/board/list?pageNum=${pageNum}'">
	</div>
	<div class="area_reply">
	    <div class="box_reply_info">
	        <a href="#rp" onclick="" class="reply_events">
		        <p class="item_info">댓글 
			        <span class="thema_apply">
			                <span id="commentCount164_0">${dto.bAnswerCnt}</span>
		            </span>
	            </p>
	        </a>
	    </div>
	
	    <div id="entry164Comment">
	        <!-- reply_content -->
	        <div class="reply_content">
	            <button type="button" class="btn_more btn_replymore" style="display: none;">이전 댓글 더보기</button>
	
	            <!-- box_comment_list -->
	            <div class="box_comment_list">
	                
	                    <div class="comment-list">
	                        <ul id="comment-ul">
	                        	
	                        	<!-- reply 출력 -->
                            	<c:forEach var="reply" items="${replyList}">
                                <li id="comment${reply.rpNum}">
                                    <div class="author-meta">
                                        <span class="nickname">${reply.rpWriter.memName}</span>
                                        <span class="date">${reply.rpRegdate}</span>
                                    </div>
                                    <!-- 댓글내용 -->
                                    <div id="rp_contentBox${reply.rpNum}">
                                    	<p id="commentContent${reply.rpNum}">${reply.rpContent}</p>
                                    </div>
                                    <!-- 수정/삭제버튼 -->
                                    <div class="control">
                                    <c:if test="${memNum eq reply.rpWriter.memNum}">
                                        <a href="javascript:void(0);" onclick="updateComment(${dto.bNum},${reply.rpNum});return false;">수정</a>
                                        <a href="javascript:void(0);" onclick="deleteComment(${reply.rpNum},${dto.bNum},${pageNum});return false;">삭제</a>
                                    </c:if>
                                    <%--  <a href="#" onclick="commentComment(${reply.rpNum}); return false">댓글쓰기</a> --%>
                                    </div>
                                    <!-- 수정시 수정/취소버튼 -->
                                    <button class="underTxtBtn${reply.rpNum}" id="updateBtn" onclick="updateReply(${dto.bNum},${reply.rpNum},${pageNum})">수정하기</button>
                                	<button class="underTxtBtn${reply.rpNum}" id="cancelBtn" onclick="cancelUpdate(${reply.rpNum})">취소하기</button>
                                </li>
                            	</c:forEach>
	                        	<!-- reply 출력-end -->
                            	
	                        </ul>
	                    </div>
	            </div>
	            <!-- // box_comment_list -->
	            
	            <!-- 더보기버튼 :10개씩, 10개 이하시 보이지 않음. -->
	            <div class="addBtnOutter">
		            <c:if test="${totalReply>10}">
		            <div class="addBtnBox">
		            	<button id="addBtn" onclick="moreList(${dto.bNum},${pageNum});"><span>더보기(More)</span></button>
	            	</div>
		            </c:if>
	            </div>
	            <!-- 더보기버튼 :10개씩, 10개 이하시 보이지 않음.-end -->
	
	            <!-- <form method="post" action="/comment/add/164" onsubmit="return false" style="margin: 0"> -->
                <!-- reply_write -->
                <div class="reply_write">

                    <div class="form_content thema_apply">
                        <textarea id="rpContent1" name="rpContent" placeholder="댓글을 입력해주세요."></textarea>
                    </div>
                    

                    <div class="form_reg">
                        <button type="button" class="btn_register thema_apply" onclick="addComment(this, ${dto.bNum},${pageNum}); return false;">등록</button>
                    </div>
                </div>
	                <!-- </form> -->
                <!-- // reply_write -->
	            
	        </div>
	        <!-- // reply_content -->
	    </div>
	</div>
<!-- article_content -->

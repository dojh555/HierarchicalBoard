<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<c:if test="${bNum==0}">
	<h2>게시판 글쓰기</h2>
</c:if>
<c:if test="${bNum!=0}">
	<h2>답글쓰기</h2>
</c:if>

<form method="post" action="/board/write" onsubmit="return writeCheck()">
	<input type="hidden" name="pageNum" value="${pageNum}"/>
	<input type="hidden" name="bNum" value="${bNum}"/>
	<input type="hidden" name="bRef" value="${bRef}"/>
	<input type="hidden" name="bStep" value="${bStep}"/>
	<input type="hidden" name="bRefOrder" value="${bRefOrder}"/>
	
	<table border="1">
	
		<tr>
			<td>이름</td>
			<td><input type="text" value="${memName}" size="30" readonly="readonly"></td>
		</tr>
		
		<tr>
			<td>글제목</td>
			<td>
			<!-- 원글 -->
			<c:if test="${bNum==0}">
				<input type="text" name="bTitle" id="bTitle" size="40"/>
			</c:if>
			<!-- 답글 -->
			<c:if  test="${bNum!=0}">
				<input type="text" name="bTitle" id="bTitle" size="40" value="[답변]"/>
			</c:if>
			
			</td>
		</tr>
		
		<tr>
			<td>글내용</td>
			<td>
				<textarea name="bContent" id="bContent" rows="10" cols="60"></textarea>
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
			<!-- 글쓰기 -->
			<c:if test="${bNum==0}">
				<input type="submit" value="글쓰기"/>
			</c:if>
			
			<!-- 답글쓰기 -->
			<c:if test="${bNum!=0}">
				<input type="submit" value="답글쓰기"/>
			</c:if>
			
				<input type="reset" value="다시쓰기"/>
				<input type="button" value="글목록" onclick="location.href='/board/list'">
			</td>
		</tr>
	</table>
</form>
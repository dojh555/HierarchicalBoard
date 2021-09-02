<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<h2>글 수정 폼</h2>
	
	<form method="post" action="/board/update" onsubmit="return writeCheck()">
	<input type="hidden" name="pageNum" value="${pageNum}">
		<table border="1">
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="writer" value="${dto.bWriter.memName}" readonly="readonly">
					<input type="hidden" name="bNum" value="${dto.bNum}">
				</td>
			</tr>
		
			<tr>
				<td>글제목</td>
				<td><input type="text" name="bTitle" id="bTitle" value="${dto.bTitle}" size="40"></td>
			</tr>
			
			<tr>
				<td>글내용</td>
				<td>
					<textarea name="bContent" id="bContent" rows="10" cols="60">${dto.bContent}</textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="글수정">&nbsp;
					<input type="reset" value="다시쓰기">&nbsp;
					<input type="button" value="글목록" onclick="location.href='/board/list?pageNum=${pageNum}'">
				</td>
			</tr>
		</table>
	</form>

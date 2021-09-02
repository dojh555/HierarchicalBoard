<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">


</script>
	<h2>내정보 수정</h2>
	<form onsubmit="return checkpw()">
		<table border="1">
			<tr>
				<td>ID</td>
				<td>${dto.id}</td>
			</tr>
			<tr>
				<td>암호</td>
				<td>
					<input type="password" name="pw" id="pw" size="20">
				</td>
			</tr>
			<tr>
				<td>암호확인</td>
				<td>
					<input type="password" name="pw2" id="pw2" size="20">
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${dto.memName}</td>
			</tr>
			
			<tr>
				<td>생년월일</td>
				<td>${dto.memBirth}
				</td>
			</tr>
			
			<tr>
				<td>전화번호</td>
				<td>${dto.memTel}
				</td>
			</tr>
			
			<tr>
				<td>이메일</td>
				<td>${dto.memEmail}
				</td>
			</tr>
			
			<tr>
				<td>우편번호</td>
				<td>${dto.memPost}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${dto.memAddress}</td>
			</tr>
			<tr>
				<td>상세주소</td>
				<td>${dto.memAddress_detail}</td>
			</tr>
			
		</table>
		<div class="btn-box">
			<input type="submit" class="btn-item" value="수정하기">
			<input type="button" class="btn-item" value="회원탈퇴" onclick="return deleteMem();">
		</div>
	</form>
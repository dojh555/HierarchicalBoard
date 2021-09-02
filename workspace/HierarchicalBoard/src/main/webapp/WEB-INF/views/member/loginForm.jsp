<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <%-- loginForm.jsp --%>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
	<h2>로그인</h2>
	
	<form name="loginForm" method="post" onsubmit="return check()">
		<table border="1">
			<tr>
				<td>ID</td>
				<td><input type="text" name="id" id="id" size="20"></td>
			</tr>
		
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw" id="pw" size="20"></td>
			</tr>
			
		
		</table>
		<div class="btn-box">
			<input type="submit" value="로그인" class="btn-item">&nbsp;
			<input type="button" value="회원가입" class="btn-item" onclick="location.href='/member/joinForm'">
		</div>
	</form>

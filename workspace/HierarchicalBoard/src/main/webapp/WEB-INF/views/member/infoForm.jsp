<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
       function openDaumPostcode(){
              new daum.Postcode({
                     oncomplete:function(data){
                              document.getElementById('memPost').value=data.zonecode;
                              document.getElementById('memAddress').value=data.address;
                      }
               }).open();
       }//openDaumPostcode()---
</script>


<style type="text/css">
	h2{text-align:center;}
	table{margin:auto; padding:3px;}
</style>

	<h2>내정보 수정</h2>
	<form name="upForm" method="post" action="/member/update" onsubmit="return checkSubmit()">
		<input type="hidden" name="memTel" id="memTel">
		<input type="hidden" name="memEmail" id="memEmail">
		<table border="1">
			<tr>
				<td>ID</td>
				<td>
					<input type="text" name="id" value="${dto.id}" size="20" readonly="readonly">
				</td>
			</tr>
			
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="pw" id="pw" size="20">
				</td>
			</tr>
			<tr>
				<td>비밀번호확인</td>
				<td>
					<input type="password" name="pw2" id="pw2" size="20">
				</td>
			</tr>
			
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="memName" id="memName" size="30" value="${dto.memName}">
				</td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><input type="date" name="memBirth" id="memBirth" value="${dto.memBirth}">
				</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>
					<select name="tel1" id="tel1">
						<option value="${tel1}">${tel1}</option>
						<option value="010">010</option>
						<option value="017">017</option>
						<option value="018">018</option>
					</select>
					
					<input type="text" name="tel2" id="tel2" value="${tel2}" size="4">
					<input type="text" name="tel3" id="tel3" value="${tel3}" size="4">
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>
					<input type="text" name="email1" id="email1" value="${email1}">@
					<select name="email2" id="email2">
						<option value="${email2}">${email2}</option>
						<option value="@naver.com">naver.com</option>
						<option value="@nate.com">nate.com</option>
						<option value="@daum.net">daum.net</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td>
					<input type="text" name="memPost" id="memPost" size="5" value="${dto.memPost}">
					<input type="button" value="주소검색" onclick="openDaumPostcode()">
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<input type="text" name="memAddress" id="memAddress" value="${dto.memAddress}" size="60" readonly="readonly">
					<br/>
					상세주소: <input type="text" name="memAddress_detail" id="memAddress_detail" value="${dto.memAddress_detail}" size="60">
				</td>
			</tr>
			
		</table>
			<div class="btn-box">
				<input type="submit" class="btn-item" value="내정보 수정">&nbsp;
				<input type="button" class="btn-item" value="취소" onclick="location.href='/member/info'">
			</div>
	
	</form>
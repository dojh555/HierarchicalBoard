<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<h2 class="tit-board">회원가입</h2>
	<form method="post" action="/member/join" onSubmit="return checkJoin()">
		<input type="hidden" name="memTel" id="memTel">
		<input type="hidden" name="memEmail" id="memEmail">
		<table border="1">
		
			<!-- ID -->
			<tr>
				<td class="con-subtit">ID</td>
				<td>
					<input type="text" name="id" id="id" size="20"/>
					<input type="button" value="ID중복체크" onclick="confirmIDCheck()" class="btn-table-item"/>
				</td>
			</tr>
			
			<!-- 비밀번호 -->
			<tr>
				<td class="con-subtit">비밀번호</td>
				<td>
					<input type="password" name="pw" id="pw" size="10"/>
				</td>
			</tr>
			<tr>
				<td class="con-subtit">비밀번호확인</td>
				<td>
					<input type="password" name="pw2" id="pw2" size="10"/>
				</td>
			</tr>
			
			<!-- 이름 -->
			<tr>
				<td class="con-subtit">이름</td>
				<td>
					<input type="text" name="memName" id="memName" size="30"/>
				</td>
			</tr>
			
			<!-- 생일 -->
			<tr>
				<td class="con-subtit">생년월일</td>
				<td><input type="date" name="memBirth" id="memBirth"/></td>
			</tr>
			
			<!-- 전화번호 -->
			<tr>
				<td class="con-subtit">전화번호</td>
				<td>
					<select name="tel1" id="tel1">
						<option value="010">010</option>
						<option value="017">017</option>
						<option value="018">018</option>
					</select>
					
					<input type="text" name="tel2" id="tel2" size="4">
					<input type="text" name="tel3" id="tel3" size="4">
				</td>
			</tr>
			
			<!-- 이메일 -->
			<tr>
				<td class="con-subtit">이메일</td>
				<td>
					<input type="text" name="email1" id="email1"/>@
					<select name="email2" id="email2">
						<option value="@naver.com">naver.com</option>
						<option value="@nate.com">nate.com</option>
						<option value="@daum.net">daum.net</option>
					</select>
				</td>
			</tr>
			
			
			<!-- 우편번호 -->
			<tr>
				<td class="con-subtit">우편번호</td>
				<td>
					<input type="text" name="memPost" id="memPost" size="5" readonly />
					<input type="button" value="주소검색" onclick="openDaumPostcode()">
				</td>
			</tr>
			
			<!-- 주소 -->
			<tr>
				<td class="con-subtit">주소</td>
				<td>
					<input type="text" name="memAddress" id="memAddress" size="60" readonly />
					<br/>
					상세주소: <input type="text" name="memAddress_detail" id="memAddress_detail" size="40" />
				</td>
			</tr>
		</table>
		<div class="btn-box">
			<input type="submit" value="회원가입" class="btn-item"/>
			<input type="reset" value="다시입력" class="btn-item"/>
			<input type="button" value="메인으로" class="btn-item" onclick="location='/board/list'"/>
		</div>
	</form>

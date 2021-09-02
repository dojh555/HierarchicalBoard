/**
 * 
 */
 	var isCheckId='f';
 	
 	//joinForm.jsp - 회원가입 유효성 체크
	function checkJoin(){
		//데이터유효성 체크
		if($("#id").val()==''){
			alert("ID를 입력하세요.");
			$("#id").focus();
			return false;
		}
		
		if(isCheckId=='f'){
			alert('ID 중복확인 해주세요');
			$("#id").focus();
			return false;
		}
		
		if($("#pw").val()==''){
			alert("비밀번호를 입력하세요.");
			$("#pw").focus();
			return false;
		}
		
		if($("#pw2").val()==''){
			alert("비밀번호확인를 입력하세요.");
			$("#pw2").focus();
			return false;
		}
		
		//비밀번호와 비밀번호확인이 같은지 비교
		if($("#pw").val()!=$("#pw2").val()){
			alert("비밀번호 확인이 일치하지 않습니다.");
			$("#pw2").val('');//내용삭제
			$("#pw2").focus();
			return false;
		}
		
		//이름
		if($("#memName").val()==''){
			alert("이름을 입력하세요.");
			$("#memName").focus();
			return false;
		}
		
		if($("#email1").val()==''||$("#email2").val()==''){
			alert('이메일을 입력하세요.')
			$("#email1").focus();
			
			return false;
		}
		
		$("#memTel").val($("#tel1").val()+"-"+$("#tel2").val()+"-"+$("#tel3").val());
		$("#memEmail").val($("#email1").val() + $("#email2").val());
		
		return true;
	}//check()-end

	//joinForm.jsp - 아이디중복 체크, Ajax
	function confirmIDCheck(){
		if($('#id').val()==''){
			alert("ID를 입력하세요.");
		}else{//ID가 입력되었을 때
			
			$.ajax({
				type:"POST",
				url:"/member/checkId",
				data:"id="+$("#id").val(),//서버로 넘길 인수값
				dataType: "JSON",
				success:function(data){
					if(data.check==1){
						//사용가능한 ID
						alert('사용가능한 ID 입니다.');
						isCheckId = 'y';
						$("#pw").focus();
					}else if(data.check==-1){
						//사용중인 ID
						alert('사용중인 ID 입니다. 다른 ID를 입력하세요.');
						isCheckId = 'f';
						$("#id").val('').focus();
					}
				},//success-end
				error:function(){
					alert('연결이 원활하지 않습니다.');
				}
			});
		}//else-end
		
	}//confirmIDCheck-end
	
 	//writeForm.jsp,updateForm.jsp - 글쓰기 유효성검사
 	function writeCheck(){
		
		if($('#bTitle').val()==''){
			alert('글제목을 입력하시오.');
			$('#bTitle').focus();
			return false;
		}
		
		if($('#bContent').val()==''){
			alert('글내용을 입력하시오.');
			$('#bContent').focus();
			return false;
		}
		
		return true;
	}
		
	//loginForm.jsp - 로그인 시 아이디 패스워드 검사
	function check(){
		if(document.loginForm.id.value==''){
			alert('ID를 입력하세요')
			document.loginForm.id.focus();
			return false;
		}
		
		if(document.loginForm.pw.value==''){
			alert('비밀번호를 입력하세요')
			document.loginForm.pw.focus();
			return false;
		}
	    var form_data = "id="+$('#id').val()+"&pw="+$('#pw').val()
		
		//Ajax
		$.ajax({
			type:"POST",
			url:"/member/loginCheck",
			data: form_data,//서버로 넘길 인수값
			dataType: "JSON",
			success:function(data){
				if(data.check==1){
					alert('로그인 되었습니다.');
					location.href="/board/list"
				}else if(data.check==-1){
					//사용중인 ID
					alert('가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.');
				}
			},//success-end
			error:function(){
				alert('연결이 원활하지 않습니다.');
			}
		});
        
		return false;
	}
	
	//infoMember.jsp - 비밀번호 검사: 수정폼가기
	function checkpw(){
		
		if($("#pw").val()==''){
			alert("비밀번호를 입력하세요.");
			$("#pw").focus();
			return false;
		}else if($("#pw2").val()==''){
			alert("비밀번호확인를 입력하세요.");
			$("#pw2").focus();
			return false;
		}else if($("#pw").val()!=$("#pw2").val()){
			alert("비밀번호 확인이 일치하지 않습니다.");
			$("#pw2").val('');//내용삭제
			$("#pw2").focus();
			return false;
		}else{
			
			$.ajax({
				type:"POST",
				url:"/member/checkPw",
				data: "pw="+$('#pw').val(),//서버로 넘길 인수값
				dataType: "JSON",
				success:function(data){
					if(data.check==1){
						location.href="/member/infoForm"
					}else if(data.check==-1){
						alert('잘못된 비밀번호입니다.');
					}
				},//success-end
				error:function(){
					alert('연결이 원활하지 않습니다.');
				}
			});
		}//else-end
		return false;
		
	}
	
	//infoMember.jsp - 회원 탈퇴
	function deleteMem(){
		
		if($("#pw").val()==''){
			alert("비밀번호를 입력하세요.");
			$("#pw").focus();
			return false;
		}else if($("#pw2").val()==''){
			alert("비밀번호확인를 입력하세요.");
			$("#pw2").focus();
			return false;
		}else if($("#pw").val()!=$("#pw2").val()){
			alert("비밀번호 확인이 일치하지 않습니다.");
			$("#pw2").val('');//내용삭제
			$("#pw2").focus();
			return false;
		}else{
			
			$.ajax({
				type:"POST",
				url:"/member/checkPw",
				data: "pw="+$('#pw').val(),//서버로 넘길 인수값
				dataType: "JSON",
				success:function(data){
					if(data.check==1){
						alert('회원탈퇴가 완료되었습니다.')
						location.href='/member/delete';
						
					}else if(data.check==-1){
						//사용중인 ID
						alert('잘못된 비밀번호입니다.');
					}
				},//success-end
				error:function(){
					alert('연결이 원활하지 않습니다.');
				}
			});
		}//else-end
		return false;
		
	}
	
	//infoForm.jsp - 정보수정 유효성검사
	function checkSubmit(){
		//데이터유효성 체크
		if($("#pw").val()==''){
			alert("비밀번호를 입력하세요.");
			$("#pw").focus();
			return false;
		}
		
		if($("#pw2").val()==''){
			alert("비밀번호확인를 입력하세요.");
			$("#pw2").focus();
			return false;
		}
		
		//비밀번호와 비밀번호확인이 같은지 비교
		if($("#pw").val()!=$("#pw2").val()){
			alert("비밀번호 확인이 일치하지 않습니다.");
			$("#pw2").val('');//내용삭제
			$("#pw2").focus();
			return false;
		}
		
		//이름
		if($("#memName").val()==''){
			alert("이름을 입력하세요.");
			$("#memName").focus();
			return false;
		}
		
		if($("#email1").val()==''||$("#email2").val()==''){
			alert('이메일을 입력하세요.')
			$("#email1").focus();
			
			return false;
		}
		
		
		$("#memTel").val($("#tel1").val()+"-"+$("#tel2").val()+"-"+$("#tel3").val());
		$("#memEmail").val($("#email1").val() + $("#email2").val());
		
		return true;
	}
	
	/* 게시물 관련 */
//	content.jsp - Ajax 댓글 쓰기 + 바로 출력
	function addComment(_obj,bNum,pageNum){
		if($("#rpContent1").val().trim()==""){
			alert('댓글을 입력하세요');
		}else{
			
			var liNum = $("#comment-ul li").length;  //마지막 리스트 번호를 알아내기 위해서 tr태그의 length를 구함.
			var jsondata = {"rpContent":$("#rpContent1").val()}//val() 꼭 쓰기!!!!
			$.ajax({
				type:"POST",
				url:"/board/reply/write/"+bNum+"?liNum="+liNum+"&pageNum="+pageNum,
				data:JSON.stringify(jsondata),
				contentType: "application/json; charset=utf-8",
				dataType:"JSON",
	            beforeSend : function(xmlHttpRequest){
                	xmlHttpRequest.setRequestHeader("AJAX", "true"); // ajax 호출을  header에 기록
            	},
				success:function(data){
					alert('댓글이 등록되었습니다.');
					
					var html = data.html;
					$("#comment-ul").html(html);//댓글업데이트
					
					var bAnswerCnt = data.bAnswerCnt;
					$("#commentCount164_0").html(bAnswerCnt);//댓글수 업데이트
					
					$("#rpContent1").val('');//댓글 초기화
					
					if(data.isAddBtn){//댓글 추가시 10개 이상이면 더보기활성화
						var foldBtn = "<div class=\"addBtnBox\">";
							foldBtn +="<button id=\"addBtn\" onclick=\"moreList("+bNum+","+pageNum+");\"><span>더보기(More)</span></button>";
							foldBtn +="</div>";
						
						$(".addBtnOutter").html(foldBtn);
					}
					
				},
				error:function(request,status,error){
				console.log(request);
				console.log(request.status);
					if(request.status==500){
						alert('로그인이 필요한 서비스입니다.');
						location.replace('/member/loginForm');
					}
						//alert('연결이 원활하지 않습니다.');
				}
				
			});
				
		}
		
	}
	
//	content.jsp - 댓글 삭제
	function deleteComment(rpNum,bNum,pageNum){
		var isDelComment = confirm("댓글을 삭제하시겠습니까?");
		
		if(isDelComment){
			//다시 리스트로 돌아오기 위해 pageNum,bNum을 넘긴다.
			location.href="/board/reply/delete/"+rpNum+"?pageNum="+pageNum+"&bNum="+bNum;	
		}
	}
	
//	content.jsp - 댓글 수정 텍스트폼으로 바꾸기
	function updateComment(bNum,rpNum){
		//기존 댓글 가져오기
		var commentContent = $("#commentContent"+rpNum).html();

		//수정가능하도록 댓글 변화
		var html = "<textarea class='rpUpdateTxt' id='rpUpContent"+rpNum+"' name='rpContent2'>"+commentContent+"</textarea>";
		$("#rp_contentBox"+rpNum).html(html);
		
		//수정하기 , 취소하기 버튼 보이기
		$(".underTxtBtn"+rpNum).css('display','inline-block');
			
		//이름,날짜 위로 올리기
		$("#comment"+rpNum+" .author-meta").css('position','relative');
		$("#comment"+rpNum+" .author-meta").css('top','0px');
		
		//기존 수정/삭제버튼 안보이게하기
		$(".control").css('display','none');
		
		//댓글 수정동안에는 새댓글 작성 불가.
		$("#rpContent1").attr('readonly', true);
		
	}
//	content.jsp - 댓글 수정 취소 원래 폼으로 돌리기
	function cancelUpdate(rpNum){
		//1. 댓글 원래대로 돌려놓기
		var commentContent = $("#rpUpContent"+rpNum).html();// 기존 댓글 가져오기
		var html = "<p id=\"commentContent"+rpNum+"\">"+commentContent+"</p>";
		$("#rp_contentBox"+rpNum).html(html);
		
		//2. 수정하기 , 취소하기 버튼 숨기기
		$(".underTxtBtn"+rpNum).css('display','none');
		
		//3. 이름,날짜 위로 올리기
		$("#comment"+rpNum+" .author-meta").css('position','absolute');
		$("#comment"+rpNum+" .author-meta").css('top','30px');
		
		//4. 기존 수정/삭제버튼 보이게하기
		$(".control").css('display','block');
		
		//5. 댓글 입력 다시 활성화
		$("#rpContent1").attr('readonly', false);
	}
	
//	content.jsp - 댓글 수정하기
	function updateReply(bNum,rpNum,pageNum){
		//댓글 수정 후 기존 댓글 창 입력 다시 활성화
		$("#rpContent1").attr('readonly', false);
		
		if($("#rpUpContent"+rpNum).val().trim()==''){
			alert('댓글을 입력해주세요.');
			return false;
		}
		
		var liNum = $("#comment-ul li").length;  //마지막 리스트 번호를 알아내기 위해서 tr태그의 length를 구함.
		var jsondata = {"rpNum":rpNum,"rpContent":$("#rpUpContent"+rpNum).val()};//수정할 댓글 내용 넣기
		
		var isUpComment = confirm("댓글을 수정하시겠습니까?");
		
		if(isUpComment){//확인한다면 수정
			
			 $.ajax({
					type:"POST",
					url:"/board/reply/update/"+bNum+"?liNum="+liNum+"&pageNum="+pageNum,
					data:JSON.stringify(jsondata),
					contentType: "application/json; charset=utf-8",
					dataType: "JSON",
					success:function(data){
						alert('댓글이 수정되었습니다.');
						
						var html = data.html;
						$("#comment-ul").html(html);//댓글업데이트
						
					},
					error:function(){
						alert('연결이 원활하지 않습니다11.');
					}
					
				});
		}
		 
	}
	
//	content.jsp - 댓글 더보기
	function moreList(bNum,pageNum){
		//댓글 수정동안 더보기클릭하여 취소하기버튼이 없어져 readonly false를 못할때 대비
		$("#rpContent1").attr('readonly', false);
		
		 var liNum = $("#comment-ul li").length;  //마지막 리스트 번호를 알아내기 위해서 tr태그의 length를 구함.
		 var jsondata = {"liNum":liNum}
		 
		 $.ajax({
				type:"POST",
				url:"/board/reply/add/"+bNum+"?pageNum="+pageNum,
				data:JSON.stringify(jsondata),
				contentType: "application/json; charset=utf-8",
				dataType: "JSON",
				success:function(data){
					var liNum = $("#comment-ul li").length;  //마지막 리스트 번호를 알아내기 위해서 tr태그의 length를 구함.
					
					var html = data.html;
					$("#comment-ul").html(html);//댓글업데이트
					
					
					if(data.isTotal){//마지막게시물이 출력되고 나면 더보기 -> 접기
						var foldBtn = 
							"<button id=\"addBtn\" onclick=\"foldList("+bNum+","+pageNum+");\"><span>접기(Fold)</span></button>";
						
						$(".addBtnBox").html(foldBtn);
					}
				},
				error:function(){
					alert('연결이 원활하지 않습니다.');
				}
				
			});
	}
	
//	content.jsp - 댓글 접기
	function foldList(bNum,pageNum){
		//댓글 수정동안 더보기클릭하여 취소하기버튼이 없어져 readonly false를 못할때 대비
		$("#rpContent1").attr('readonly', false);
		
		//따로 데이터가 갈 필요 없을 듯 하다.
		 $.ajax({
				type:"POST",
				url:"/board/reply/fold/"+bNum+"?pageNum="+pageNum,
				success:function(data){
					var liNum = $("#comment-ul li").length;  //마지막 리스트 번호를 알아내기 위해서 tr태그의 length를 구함.
					
					var html = data.html;
					$("#comment-ul").html(html);//댓글업데이트
					
					
					//다시 더보기 버튼으로 바꾸기
					var foldBtn = 
						"<button id=\"addBtn\" onclick=\"moreList("+bNum+","+pageNum+");\"><span>더보기(More)</span></button>";
					
					$(".addBtnBox").html(foldBtn);
				},
				error:function(){
					alert('연결이 원활하지 않습니다.');
				}
				
			});
	}
	
//	content.jsp - 게시물 삭제
	function deleteBoard(bNum){
		
		var isDeleteBoard = confirm("게시물을 삭제하시겠습니까?");
		
		if(isDeleteBoard){//확인한다면 글삭제
		location.href = "/board/delete?bNum="+bNum
		}
	}
	

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<form id="joinForm" name="joinForm" autocomplete="off">
		<input type="hidden" name="userId" />
		<input type="hidden" name="password" />
	</form>
	<form id="loginForm" name="loginForm" autocomplete="off">
		<input type="hidden" id="RSAModulus" name="RSAModulus" value="${RSAModulus}"/>
		<input type="hidden" id="RSAExponent" name="RSAExponent" value="${RSAExponent}"/>
		<input type="hidden" id="userId" name="userId" />
		<input type="hidden" id="password" name="password" />
		<div class="img">
	        <div class="join pull-right"><a href="#" onclick="fnJoin()">가입</a></div>
	        <div class="content">
	            <h1>Pine Tree</h1>
				<div id="information" class="card">
					<input type="email" id="id" name="id" class="form-control" required="required" placeholder="ID 입력(이메일)"/>
					<input type="password" id="pw" name="pw" class="form-control" required="required" placeholder="Password 입력"/>
				</div>
				<div id="rememberDiv" class="form-check pull-left">
	    			<input type="checkbox" class="form-check-input" id="remember-me">
	    			<label class="form-check-label" for="remember-me">로그인 정보 저장</label>
	  			</div>
				<div id="loginProcDiv" class="submit">
					<button type="button" class="btn btn-primary btn-lg btn-block" onclick="fnUserIdCheck()">로그인</button>
				</div>
				<div id="joinDiv" class="submit">
				</div>
				<div id="findDiv" class="submit">
				</div>
				<div id="forgotDiv" class="forgot pull-left">
					<a href="#" onclick="fnForgot()">비밀번호를 잊으셨나요?</a>
				</div>
				<div id="loginDiv" class="forgot pull-right">
				</div>
	        </div>
	    </div>
	</form>
	<script>
	$(document).ready(function() {
		//$('#joinDiv, #findDiv, #loginDiv').hide();
		
	});
	
	
	function fnEnterKey() {
		$('#userId, #password').keypress(function(ev) {
			var keycode = (ev.keyCode ? ev.keyCode : ev.which);
			if(keycode == '13') {
				fnLoginProc();
			}
		});
	}
	
	function fnLogin() {
		$('#id, #pw').val('');
		$('#rememberDiv')
			.empty()
			.append('<input type="checkbox" class="form-check-input" id="remember-me">')
			.append('<label class="form-check-label" for="remember-me">로그인 정보 저장</label>');
		$('#loginProcDiv').empty().append('<button type="button" class="btn btn-primary btn-lg btn-block" onclick="fnUserIdCheck()">로그인</button>');
		$('#findDiv, #loginDiv, #joinDiv, #loginDiv').empty();
		$('#forgotDiv').empty().append('<a href="#" onclick="fnForgot()">비밀번호를 잊으셨나요?</a>');
		
		$('#information')
			.empty()
			.append('<input type="email" id="id" name="id" class="form-control" required="required" placeholder="ID 입력(이메일)"/>')
			.append('<input type="password" id="pw" name="pw" class="form-control" required="required" placeholder="Password 입력"/>');
	}
	
	function fnLoginProc() {
		
		$('#loginForm').attr({
			action : '/login.do',
			method : 'post'
		});
		
		$('#loginForm').submit();
		
	}
	
	function fnJoin() {
		$('#id, #pw').val('');
		$('#joinDiv').empty().html('<button type="button" class="btn btn-success btn-lg btn-block" onclick="fnJoinProc()">가입하기</button>');
		$('#loginDiv').empty().html('<a href="#" onclick="fnLogin()">로그인하러 가기</a>');
		$('#findDiv, #loginProcDiv, #rememberDiv').empty();
		$('#forgotDiv').empty().append('<a href="#" onclick="fnForgot()">비밀번호를 잊으셨나요?</a>');
		
		$('#information')
			.empty()
			.append('<input type="text" id="id" name="id" class="form-control" required="required" placeholder="ID 입력"/>')
			.append('<input type="password" id="pw" name="pw" class="form-control" required="required" placeholder="Password 입력"/>')
			.append('<input type="text" id="name" name="name" class="form-control" required="required" placeholder="이름 입력"/>')
			.append('<input type="email" id="email" name="email" class="form-control" required="required" placeholder="이메일 입력"/>');
	}
	
	function fnJoinProc() {
		$("#userId").val(rsa.encrypt(id));
		$("#password").val(rsa.encrypt(pw));
	      
		
		$('#joinForm').attr({
			action : '/login.do',
			method : 'post'
		});
		
		$('#joinForm').submit();
	}
	
	function fnForgot() {
		$('#id, #pw').val('');
		$('#findDiv').empty().html('<button type="button" class="btn btn-success btn-lg btn-block" onclick="fnFind()">찾기</button>');
		$('#loginDiv').empty().html('<a href="#" onclick="fnLogin()">로그인하러 가기</a>');
		$('#loginProcDiv, #joinDiv, #forgotDiv, #rememberDiv').empty();
		
		$('#information')
			.empty()
			.append('<input type="email" id="id" name="id" class="form-control" required="required" placeholder="ID 입력(이메일)"/>');
	}
	
	function fnFind() {
		
	}
	
	function fnUserIdCheck() {
		
		var rsa = new RSAKey();
		rsa.setPublic($('#RSAModulus').val(),$('#RSAExponent').val());
		
		var id = $('#id').val();
		var pw = $('#pw').val();
		
		$("#userId").val(rsa.encrypt(id));
		$("#password").val(rsa.encrypt(pw));
		
		$.ajax({
			url : '/user/userIdCheck.json',
			type : 'post',
			dataType : 'json',
			data : {
				userId : $('#userId').val()
			},
			success : function(data) {
				console.log(data);
				if(data.success) {
					fnLoginProc();
				} else {
					alert('중복된 아이디가 존재합니다.');
				}
			}
		});
	}
	
</script>
</body>
</html>
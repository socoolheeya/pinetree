<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<form id="loginForm" name="loginForm" autocomplete="off">
		<input type="hidden" id="RSAModulus" name="RSAModulus" value="${RSAModulus}"/>
		<input type="hidden" id="RSAExponent" name="RSAExponent" value="${RSAExponent}"/>
		<input type="hidden" id="userId" name="userId" />
		<input type="hidden" id="password" name="password" />
		<div class="img">
	        <div class="join pull-right"><a href="#" onclick="fnJoin()">가입</a></div>
	        <div class="content">
	            <h1>Pine Tree</h1>
				<div class="card">
					<input type="text" id="id" name="id" class="form-control" placeholder="ID 입력"/>
					<input type="password" id="pw" name="pw" class="form-control" placeholder="Password 입력"/>
				</div>
				<div class="form-check pull-left">
	    			<input type="checkbox" class="form-check-input" id="remember-me">
	    			<label class="form-check-label" for="remember-me">로그인 정보 저장</label>
	  			</div>
				<div id="loginProcDiv" class="submit">
					<button type="button" class="btn btn-primary btn-lg btn-block" onclick="fnLoginProc()">로그인</button>
				</div>
				<div id="joinDiv" class="submit">
					<button type="button" class="btn btn-success btn-lg btn-block" onclick="fnJoin()">가입하기</button>
				</div>
				<div id="findDiv" class="submit">
					<button type="button" class="btn btn-success btn-lg btn-block" onclick="fnFind()">찾기</button>
				</div>
				<div id="forgotDiv" class="forgot pull-left">
					<a href="#" onclick="fnForgot()">비밀번호를 잊으셨나요?</a>
				</div>
				<div id="loginDiv" class="forgot pull-left">
					<a href="#" onclick="fnLogin()">로그인하러 가기</a>
				</div>
	        </div>
	    </div>
	</form>
	<script>
	$(document).ready(function() {
		$('#joinDiv, #findDiv, #loginDiv').hide();
		
	});
	
	
	function fnEnterKey() {
		$('#userId, #password').keypress(function(ev) {
			var keycode = (ev.keyCode ? ev.keyCode : ev.which);
			if(keycode == '13') {
				fnLoginProc();
			}
		});
	}
	
	function fnLoginProc() {
		var rsa = new RSAKey();
		rsa.setPublic($('#RSAModulus').val(),$('#RSAExponent').val());
		
		var id = $('#id').val();
		var pw = $('#pw').val();
		
		$("#userId").val(rsa.encrypt(id));
		$("#password").val(rsa.encrypt(pw));
	      
		
		$('#loginForm').attr({
			action : '/login.do',
			method : 'post'
		});
		
		$('#loginForm').submit();
		
	}
	
	function fnLogin() {
		
	}
	
	function fnJoin() {
		$('#joinDiv').show();
		$('#loginProcDiv').hide();
	}
	
	function fnFotgot() {
		
	}
</script>
</body>
</html>
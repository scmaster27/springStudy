<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
$(function(){
	$("#btnLogin").click(function(){
		document.frmLogin.method = "post";
		document.frmLogin.action = "${path}/admin/login_check.do";
		document.frmLogin.submit();
	});
});
</script>
<style>
#wrap {
	position: relative;
	width: 1300px;
	margin: 0 auto;
	padding: 100px 0px;
	background: #f5f6f7;
}

#header {
	width: 200px;
	margin: 0 auto;
}

.blind {
	position: absolute;
	clip: rect(0 0 0 0);
	overflow: hidden;
}

.input_row {
    height: 30px;
    margin: 0 0 15px;
    padding: 5px 5px;
    border: solid 1px #dadada;
    background: #fff;
}

.int {
    font-size: 15px;
    width: 100%;
    height: 15px;
    padding: 10px 0 10px;
    border: none;
}

.btn_login {
    font-size: 20px;
    font-weight: 400;
    width: 100%;
    height: 61px;
    margin: 30px 0 15px;
    padding-top: 1px;
    border: none;
    background-color: #03c75a;
    color: #fff;
    cursor: pointer;
    text-align: center;
    font-weight: 700;
}

img {
	width: 100%;
}

fieldset {
	border: 0;
}

</style>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<div id="wrap">
	<!-- header -->
	<div id="header">
		<h1>
			<img alt="logo" src="${path}/images//spring.png">
		</h1>
	</div>
	<!-- //header -->
	<!-- container -->
	<div id="container">
		<!-- content -->
		<div id="content">
			<form id="frmLogin" name="frmLogin" target="_top" autocomplete="off">
				<fieldset class="login_form">
					<legend class="blind">Sign in</legend>
					<div class="input_row">
						<input type="text" id="id" name="id" accesskey="L"
							placeholder="ID: aaa" class="int" maxlength="40" value="">
					</div>
					<div class="input_row">
						<input type="password" id="pw" name="pw" placeholder="Password: aaa"
							class="int" maxlength="15">
					</div>
					<c:if test="${result == 'error'}">
						<div style="color: red;">${message}</div>
					</c:if> 
					<c:if test="${result == 'fail'}">
						<div style="color: red;">${message}</div>
					</c:if> 
					<button type="button" id="btnLogin" class="btn_login">Sign in</button> 
				</fieldset>
			</form>
		</div>
	</div>
</div>
</body>
</html>
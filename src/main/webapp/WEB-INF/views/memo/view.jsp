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
	$("#btnUpdate").click(function(){
		document.form1.action="${path}/memo/update/${dto.idx}";
		document.form1.submit();
	});
	$("#btnDelete").click(function(){
		if(confirm("削除しますか?")){
			location.href="${path}/memo/delete/${dto.idx}";
		}
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>メモ</h2>
<form method="post" name="form1">
<table border="1" width="500px">
	<tr>
		<td>No</td>
		<td>${dto.idx}</td>
	</tr>
	<tr>
		<td>氏名</td>
		<td><input name="writer" value="${dto.writer}"></td>
	</tr>
	<tr>
		<td>メモ</td>
		<td><input name="memo" value="${dto.memo}" size="50"></td>
	</tr>
	<tr align="center">
		<td colspan="2">
			<input type="hidden" name="idx" value="${dto.idx}">
			<button type="button" id="btnUpdate">修正</button>	
			<button type="button" id="btnDelete">削除</button>	
		</td>
	</tr>
</table>
</form>
</body>
</html>
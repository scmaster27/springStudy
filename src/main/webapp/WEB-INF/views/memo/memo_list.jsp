<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
function memo_view(idx) {
	location.href="${path}/memo/view/"+idx;
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>メモ</h2>

<form method="post" action=${path}/memo/insert.do>
	氏名: <input name="writer" size="10">
	メモ: <input name="memo" size="40">
	<input type="submit" value="確認">
</form>

<table border="1" style="width:500px">
	<tr>
		<th>No</th>
		<th>氏名</th>
		<th>メモ</th>
		<th>日付</th>
	</tr>
<c:forEach var="row" items="${list}">
	<tr>
		<td>${row.idx}</td>
		<td>${row.writer}</td>
		<td><a href="#" onclick="memo_view('${row.idx}')">${row.memo}</a></td>
		<td><fmt:formatDate value="${row.post_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
</c:forEach>
</table>
<form method="post" action=${path}/memo/csvDownload.do>
	<input type="submit" value="CSV出力">
</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="${path}">Home</a>　|
<a href="${path}/geoip/CurrentGeoInfo">ユーザの地域情報取得(geoLite2)</a>　|
<a href="${path}/upload/uploadForm">ファイルアップロード(UUID)</a>　|
<a href="${path}/memo/list.do">メモ(AWS RDS)</a>　|
<div style="text-align:right">
<a href="${path}/admin/login.do">ログイン(デザイン、簡単な有効性検査)</a>
</div>
<hr>
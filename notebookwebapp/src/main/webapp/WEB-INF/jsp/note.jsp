<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Note page</title>
</head>
<body>
<c:url value="note" var="noteUrl" />
<c:url value="notebook" var="notebookUrl" />
<c:url value="logout" var="logoutUrl" />
<h2>Заметка</h2>
<form action="${noteUrl}" method="post">
  <table>
  <tr>
    <td rowspan="2"><div style="width: 50px; height: 50px">${identIcon}</div></td>
    <td>${title}</td>
    <td><input type="submit" value="Удалить" /></td>
  </tr>
  <tr>
    <td><textarea rows="4" cols="45"> ${text}</textarea></td>
    <td><input type="hidden" name="number" value="${number}"/></td>      
  </tr>
</table>
</form>
<div><a style="margin-right: 20px" href="${notebookUrl}">Назад</a><a href="${logoutUrl}">Выход</a></div>
</body>
</html>
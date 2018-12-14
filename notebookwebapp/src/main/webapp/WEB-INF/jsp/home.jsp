<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home page</title>
</head>
<body>
  <h2>Добро пожаловать!</h2>
  <c:url value="notebook" var="notebookUrl" />
  <p>Перейдите по <a href="${notebookUrl}">ссылке</a></p>

</body>
</html>
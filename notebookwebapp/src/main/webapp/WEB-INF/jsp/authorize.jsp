<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Authorize page</title>
</head>
<body>
  <c:if test="${not empty errorMessage}">
    <p style="color: red; font-weight: bold">${errorMessage}</p>
  </c:if>
  <c:url value="login" var="loginUrl"/>
  <c:url value="register" var="registerUrl" />
  <form action="${loginUrl}" method="post">
    <h1 style="text-align:left; padding: 0 0 0 5;">Авторизация</h1>
	  <table>
        <tr>
          <td>Пользователь:</td><td><input type="text" name="username" id="name" value='<c:out value="${currentName}" />' /></td>
        </tr>
	    <tr>
	      <td>Пароль:</td><td><input type="password" name="password" id="password" value='<c:out value="${currentPassword}" />'/></td>
	    </tr>
	    <tr>
	      <td align="left"><a href="${registerUrl}">Регистрация</a></td><td align="right"><input type="submit" value="Вход" /></td>
	    </tr>
	  </table>
	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  </form>
</body>
</html>
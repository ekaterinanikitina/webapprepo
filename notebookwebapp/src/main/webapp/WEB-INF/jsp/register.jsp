<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Register page</title>
</head>
<body>
<h2>Регистрация</h2>
<c:if test="${not empty errorMessage}">
  <p style="color: red; font-weight: bold">${errorMessage}</p>
</c:if>
<c:url value="register" var="registerUrl" />
<form:form action="${registerUrl}" method="post" modelAttribute="user">
  <table>
    <tr>
      <td><form:label path="name">Пользователь: </form:label></td>
      <td><form:input path="name" value="${name}"/></td>
    </tr>
    <tr>
      <td><form:label path="password">Пароль: </form:label></td>
      <td><form:password path="password" value="${pass}"/></td>
    </tr>
    <tr>
      <td colspan="2" align="right"><input type="submit" value="Зарегистрировать" /></td>
    </tr>
  </table>
</form:form>
</body>
</html>
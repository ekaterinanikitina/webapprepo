<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Notebook page</title>
</head>
<body>
<c:if test="${not empty errorMessage}">
  <p style="color: red; font-weight: bold">${errorMessage}</p>
</c:if>
<c:url value="notebook" var="notebookUrl" />
<c:url value="note" var="noteUrl" />
<c:url value="logout" var="logoutUrl" />
<h2>Пользовательские заметки</h2>
<p>Пользователь: ${name}</p>
<form action="${notebookUrl}" method="post">
  <table style="margin-bottom: 20px">
    <tr>
      <td>Заголовок: </td>
      <td><input style="width: 380px" type="text" name="newTitle" /></td>
      <td rowspan="2"><input type="submit" name="add" value="Добавить"/></td>
    </tr>
    <tr>
      <td>Текст: </td>
      <td><textarea rows="10" cols="45" name="newText"></textarea></td>
    </tr>
  </table>
  <c:if test="${not empty notes}">
    <div style="margin-bottom: 20px">
      <input style="margin-right: 20px" type="text" name="fragment" value='<c:out value="${fragment}" />'/>
      <input type="submit" name="find" value="Поиск">
    </div>
  </c:if> 
  <table>
    <c:if test="${not empty notes}">
      <c:forEach var="note" items="${notes}">
        <tr>
          <td rowspan="2"><div style="width: 50px; height: 50px">${note.identIcon}</div></td>
          <td>${note.title}</td>
          <td><a href="${noteUrl}?number=${note.number}">Просмотр</a></td>
          <!-- <td><input type="submit" name="preview" value="Просмотр" /></td> -->
          <td></td>
        </tr>
        <tr>
          <td><textarea rows="4" cols="45"> ${note.text}</textarea></td>  
          <td><input type="hidden" name="number" value="${note.number}"/></td>      
        </tr>
      </c:forEach>
    </c:if>
  </table>
  <div><a href="${logoutUrl}">Выход</a></div>
</form>
</body>
</html>
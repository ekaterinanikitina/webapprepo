<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Confirm page</title>
</head>
<body>
<c:url value="confirm" var="confirmUrl"/>
<form action="${confirmUrl}" method="post">
  <div><input type="hidden" name="number" value="${number}"/></div>
  <table>
    <tr>
      <td colspan="2">Удалить заметку?</td>
    </tr>
    <tr>
      <td align="right"><input type="submit" name="remove" value="Да" /></td>
      <td align="right"><input type="submit" name="noRemove" value="Нет" /></td>
    </tr>
  </table>
</form>
</body>
</html>
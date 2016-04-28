

<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 19.04.2016
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Список пользователей</title>
</head>

<body>
<form action="<c:url value="/edit"/>" method="POST">

  <input type="hidden" name="Id" value="${user.id}"/>
  <table>
    <tr>
      <td>Id:</td><td><input type="number" name="Id" value="${user.id}"/></td>
    </tr>

    <tr>
      <td>Gender:</td><td><input type="text" name="Gender" value="${user.gender}"/></td>
    </tr>
    <tr>
    <td>Name:</td><td><input type="text" name="Name" value="${user.name}"/></td>
  </tr>
    <tr>
      <td>SecondName:</td><td><input type="text" name="SecondName" value="${user.secondName}"/></td>
    </tr>
    <tr>
      <td>Age:</td><td><input type="number" name="Age" value="${user.age}"/></td>
    </tr>
    <tr>
      <td>Info:</td><td><input type="text" name="Info" value="${user.secondName}"/></td>
    </tr>
  </table>
  <table>
    <tr>
      <td><input type="submit" value="OK" name="OK"/></td>
      <td><input type="submit" value="Cancel" name="Cancel"/></td>
    </tr>
  </table>
</form>
</body>
</html>
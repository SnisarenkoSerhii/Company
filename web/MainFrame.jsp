<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 19.04.2016
  Time: 18:27
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Список студентов</title>
</head>

<body>
<form action="<c:url value="/main"/>" method="POST">

  <table>
    <tr>
      <td>Список пользователей:
        <select name="Id">
          <c:forEach var="user" items="${form.users}">
                <option value="${user.id}" selected><c:out value="${user.name}"/></option>
          </c:forEach>
        </select>
      </td>
      <td><input type="submit" name="getList" value="Обновить"/></td>
    </tr>
  </table>

  <p>
  <b>Список студентов для выбранных параметров:<b>
    <br/>
    <table>
      <tr>
        <th> </th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
      </tr>
      <c:forEach var="user" items="${form.users}">
        <tr>
          <td><input type="radio" name="studentId" value="${user.userId}"></td>
          <td><c:out value="${user.secondName}"/></td>
          <td><c:out value="${student.name}"/></td>
          <td><c:out value="${student.gender}"/></td>
        </tr>
      </c:forEach>
    </table>

    <table>
      <tr>
        <td><input type="submit" value="Add" name="Add"/></td>
        <td><input type="submit" value="Edit" name="Edit"/></td>
        <td><input type="submit" value="Delete" name="Delete"/></td>
      </tr>
    </table>

</body>
</html>
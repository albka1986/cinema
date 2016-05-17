<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <head>
        <title>USER</title>
        <style type="text/css">
            BODY {
                background-image: url("http://2.bp.blogspot.com/-6Unz0GdPIPE/Ti_XYAe8okI/AAAAAAAABd0/FY4zKhSy6g4/s1600/Opera-Background-Blue-Bubbles.png");
                background-size: cover;
            }

            TABLE {
                border-collapse: collapse; /* Убираем двойные линии между ячейками */
                border: 2px solid black; /* Прячем рамку вокруг таблицы */
            }

            TD, TH {
                padding: 3px; /* Поля вокруг содержимого таблицы */
                border: 1px solid black; /* Параметры рамки */
                text-align: left; /* Выравнивание по левому краю */
            }
        </style>
    </head>
</head>
<body>
<form name="loginForm" method="post" action="/AuthorizationServlet" ${sessionScope.ifRegistered} >
    <input type="text" name="login" placeholder="Login"/> <br/>
    <input type="password" name="password" placeholder="Password"/> <br/>
    <input type="submit" value="Login"/>

</form>
<form method="get" action="registration.jsp" ${sessionScope.ifRegistered}>
    <button>Register</button>
</form>

<form ${sessionScope.ifNotRegistered} action="/LogoutServlet">
    <h3> user: ${sessionScope.user.name} ${sessionScope.user.surname}!
        <input type="submit" value="Logout"></h3>
</form>
<p><a name="top"></a></p>
<center><h1><a href="/"> Cinema by Ponomarenko</a></h1></center>
<center><h1><a href="/UserServlet"> Profile </a></h1></center>

<h2>Your tickets</h2>
<table>
    <tbody>
    <tr>
        <th>Id ticket</th>
        <th>Movie</th>
        <th>Date</th>
        <th>Row</th>
        <th>Column</th>
    </tr>

    <c:forEach items="${ticketDTOs}" var="ticket">
        <tr>
            <td><c:out value="${ticket.id}"></c:out></td>
            <td><c:out value="${ticket.sessionDTO.movieDTO.title}"></c:out></td>
            <td><c:out value="${ticket.sessionDTO.sessionDate}"></c:out></td>
            <td><c:out value="${ticket.row}"></c:out></td>
            <td><c:out value="${ticket.column}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>

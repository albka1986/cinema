<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/sql" prefix="sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <title>Title</title>
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
<center><h1><a href="/"> Cinema by Ponomarenko</a></h1></center>
<br>
<h1>
    <center>${sessionScope.movie.title}</center>
</h1>
<br>

<h4>Duration:</h4> ${sessionScope.movie.duration} minutes<br>
<h4>Description:</h4> ${sessionScope.movie.description}<br>


<br>
<h2>List of sessions:</h2>
<table>
    <tbody>
    <c:forEach items="${sessionScope.sessionDTOs}" var="session">
        <form method="post" action="/TicketServlet">
            <tr>
                <td align="left" valign="baseline">
                    <input name="idSession" readonly value="${session.id}" hidden>
                        <%--<c:out value="${session.sessionDate}"/>--%>
                    <fmt:parseDate value="${session.sessionDate}" var="parsedEmpDate" pattern="yyyy-MM-dd'T'HH:mm"/>
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${parsedEmpDate}"/>
                </td>
                <td align="center" valign="baseline">
                    <input type="submit" name="showTickets" value="Buy ticket">
                </td>
            </tr>
        </form>
    </c:forEach>
    </tbody>
</table>


</body>
</html>

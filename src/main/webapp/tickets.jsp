<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Tickets</title>
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
<div>
    <center>
        <h2><font color="red">
            ${message}
        </font>
        </h2>
    </center>
</div>
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
<div>
    <h1>
        <center>Buy ticket</center>
    </h1>
    <h2>
        <center>
            Movie: "${sessionScope.sessionMovieTitle}"</br>
            <%--Date movie: "${sessionScope.sessionDate}"</br>--%>
            Date & Time:
            <fmt:parseDate value="${sessionScope.sessionDate}" var="parsedEmpDate" pattern="yyyy-MM-dd'T'HH:mm"/>
            <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${parsedEmpDate}"/>

        </center>
    </h2>
</div>
<%--

<table align="center" cols="16">
    <tbody>
    <tr>
        <c:forEach items="${columns}" var="column">
            <td><c:out value="${column}"></c:out></td>
        </c:forEach>
    </tr>
    <c:set var="count" value="0" scope="page"/>

    <c:forEach items="${allTicketsBySessionId}" var="ticketsAtRow">
        <tr>
            <td>
                <c:out value="${rows[count]}"></c:out>
            </td>
            <c:set var="count" value="${count + 1}" scope="page"/>
            <c:forEach items="${ticketsAtRow}" var="ticketsColumn">
                <td><c:out value="${ticketsColumn}"></c:out></td>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>
</table>
--%>
<form>
    <table name="checkbox" align="center" cols="16">
        <tbody>
        <tr>
            <c:forEach items="${columns}" var="column">
                <td><c:out value="${column}"></c:out></td>
            </c:forEach>
        </tr>
        <c:set var="count" value="0" scope="page"/>

        <c:forEach items="${allTicketsBySessionId}" var="ticketsAtRow">
            <tr>
                <td>
                    <c:out value="${rows[count]}"></c:out>
                </td>
                <c:set var="count" value="${count + 1}" scope="page"/>
                <c:forEach items="${ticketsAtRow}" var="ticketsColumn">

                    <%--<td><c:out value="${ticketsColumn} "></c:out></td>--%>
                    <td>
                        <c:if test="${ticketsColumn}">
                            <input type="checkbox" checked <%--disabled--%>/>
                        </c:if>
                        <c:if test="${!ticketsColumn}">
                            <input type="checkbox" <%--disabled--%> />
                        </c:if>


                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>


<center>
    <h2> Choose row and column: </h2>
    <form method="post" action="/BuyTicketServlet">
        Row: <input type="number" min="1" max="${rowsAmount}" placeholder="row" name="row">
        Column: <input type="number" min="1" max="${columnsAmount}" placeholder="column" name="column">
        <input value="${idSession}" name="idSession" hidden>

        <p><input type="submit" value="Buy"></p>
        <c:remove var="message" scope="session"/>
    </form>
</center>


true - occupied seat</br>
false - empty seat

</body>
</html>

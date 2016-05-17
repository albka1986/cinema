<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Cinema by Ponomarenko</title>
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
<center><h1><a href="/UserServlet"${sessionScope.ifNotRegistered}> Profile </a></h1></center>
<center><h1><a href="/Admin" ${sessionScope.ifNotAdmin}> ADMIN</a></h1></center>
<br>
<div><h2>Now Playing</h2></div>
<table>
    <tbody>
    <form method="post" action="/MovieServlet">
        <c:forEach items="${sessionScope.moviesTitles}" var="movieTitle">
            <tr>
                <p><input type="submit" value="${movieTitle}" name="movieTitle"/></p>


            </tr>
        </c:forEach>
    </form>
    <tbody>
</table>


</body>
</html>
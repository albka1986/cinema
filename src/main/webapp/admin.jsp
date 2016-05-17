<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>ADMIN</title>
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
<center><h1><a href="/Admin"> ADMIN</a></h1></center>
<center><h1><a href="/UserServlet"> Profile </a></h1></center>

<h2> List of users</h2>
<form name="userForm" action="/DeleteUserServlet" method="post">
    <table>
        <tbody>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Login</th>
            <th>Password</th>
            <th>Birthday</th>
            <th>Role</th>
            <th>Email</th>
            <th>Choose</th>
        </tr>
        <c:forEach items="${sessionScope.userDTOs}" var="userDTO">
            <tr>
                <td><c:out value="${userDTO.id}"></c:out></td>
                <td><c:out value="${userDTO.name}"></c:out></td>
                <td><c:out value="${userDTO.surname}"></c:out></td>
                <td><c:out value="${userDTO.login}"></c:out></td>
                <td><c:out value="${userDTO.password}"></c:out></td>
                    <%--<td><c:out value="${userDTO.birthday}"></c:out></td>--%>
                <td>
                    <fmt:parseDate value="${userDTO.birthday}" var="parsedEmpDate" pattern="yyyy-MM-dd"/>
                    <fmt:formatDate type="Date" dateStyle="short" value="${parsedEmpDate}"/>
                </td>
                <td><c:out value="${userDTO.role}"></c:out></td>
                <td><c:out value="${userDTO.email}"></c:out></td>
                <td><input type="radio" name="idUserDelete" value="${userDTO.id}"/></td>
            </tr>
            <br>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <input type="submit" value="Delete user"/>
</form>
<br>
<hr>
<h2> List of movies</h2>
<form name="someName" method="post" action="/DeleteMovieServlet">
    <table>
        <tbody>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Description</th>
            <th>Duration</th>
            <th>Choose</th>
        </tr>
        <c:forEach items="${sessionScope.movieDTOs}" var="movieDTO">
            <tr>
                <td><c:out value="${movieDTO.id}"></c:out></td>
                <td><c:out value="${movieDTO.title}"></c:out></td>
                <td><c:out value="${movieDTO.description}"></c:out></td>
                <td><c:out value="${movieDTO.duration}"></c:out></td>
                <td><input type="radio" name="idMovieDelete" value="${movieDTO.id}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <input type="submit" value="Delete movie"/>
</form>

<h2> Add movie</h2>
<form name="createMovie" method="post" action="/CreateMovieServlet">
    <input type="text" name="title" placeholder="Name"/> </br>
    <textarea name="description" placeholder="Description" cols="40" rows="4"/></textarea> </br>
    <input type="text" name="duration" placeholder="Duration"/> </br>
    <input type="submit" value="Add movie"/>
</form>

<hr>

<h2> List of sessions</h2>
<form name="anyName" method="post" action="/DeleteSessionServlet">
    <table>
        <tbody>
        <tr>
            <th>Title</th>
            <th>Date & Time</th>
            <th>Choose</th>
        </tr>
        <c:forEach items="${sessionScope.sessionDTOs}" var="session">
        <tr>
            <td><c:out value="${session.movieDTO.title}"></c:out></td>
                <%--<td><c:out value="${session.sessionDate}"></c:out></td>--%>
            <td>
                <fmt:parseDate value="${session.sessionDate}" var="parsedEmpDate" pattern="yyyy-MM-dd'T'HH:mm"/>
                <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${parsedEmpDate}"/>
            </td>
            <td align="center"><input type="radio" name="idSessionDelete" value="${session.id}"/></td>
        </tr>
        </c:forEach>
        <tbody>
    </table>
    <br>
    <input type="submit" value="Delete session"/>
</form>

<h2> Add session</h2>
<form action="/CreateSessionServlet" method="post">
    <select name="id_movie">
        <option value=null>Choose Movie</option>
        <c:forEach items="${sessionScope.movieDTOs}" var="movieDTO">
            <option value="${movieDTO.id}">${movieDTO.title}</option>
        </c:forEach>
    </select>
    <input type="datetime-local" name="sessionDate">
    <p><input type="submit" value="Add session"></p>

</form>


</body>
</html>

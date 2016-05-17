<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
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
<center><h1><a href="/"> Cinema by Ponomarenko</a></h1></center>
</br>

<h1> Registration</h1>
<form name="registerForm" method="post" action="/RegistrationServlet">
    <input type="text" name="name" placeholder="Name"/> </br>
    <input type="text" name="surname" placeholder="Surname"/> </br>
    <input type="text" name="login" placeholder="Login"/> <br/>
    <input type="password" name="password" placeholder="Password"/> <br/>
    <input type="date" name="birthday" placeholder="Birthday"/> <br/>
    <input type="email" name="email" placeholder="Email"/> </br>
    <input type="submit" value="Register"/>
</form>

</body>
</html>

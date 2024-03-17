<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Match</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <div class="name_project">
        Табло теннисного счета
    </div>
    <nav class="navigation">
        <ul>
            <li><a href="${pageContext.request.contextPath}/menu">Главная</a></li>
            <li><a href="${pageContext.request.contextPath}/new-match">Создать новую игру</a></li>
            <li><a href="${pageContext.request.contextPath}/matches">Посмотреть все законченные матчи</a></li>
        </ul>
    </nav>
    <hr/>
</header>
    <div>
        <form action="new-match" method="post" class="new_game">
            <h1>Введите имена игроков:</h1>
            <input type="text" name="playerFirstName" placeholder="Первый игрок">
            <input type="text" name="playerSecondName" placeholder="Второй игрок">
            <button type="submit">Начать игру</button>
        </form>
    </div>
    <footer class="footer">
        <p>Created by AsckarUgin</p>
    </footer>
</body>
</html>

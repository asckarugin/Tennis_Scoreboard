<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
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
    <section>
        <div id="wrapper">
            <p>Краткие правила игры:</p>
            <ul>
                <li><p>Матч делится на сеты, сеты играются до 2 побед</p></li>
                <li><p>Каждый сет длится до 7 выигранных геймов. Если счет в гейме 6-6, то играется тай-брейк</p></li>
                <li><p>Гейм длится до 40 очков + еще 1 выигранная подача. Если у обоих игроков счет 40-40, то следующая удачная подача дает преимущество.</p></li>
            </ul>
        </div>
    </section>
    <footer class="footer">
        <p>Created by AsckarUgin</p>
    </footer>
</body>
</html>

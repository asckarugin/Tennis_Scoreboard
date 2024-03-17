<%@ page import="by.asckarugin.Models.Match" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Matches</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <% List<Match> matches = (List<Match>) request.getAttribute("matches"); %>
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
    <div class="search">
        <form action="matches" method="get" class="search_form">
            <input type="text" name="filter_by_player_name" placeholder="Найти игрока по имени" class="search_text">
            <button type="submit" class="search_btn"><img src="https://mywebicons.ru/i/png/231ddb274f6038290cdda169711a1a03.png" alt="">
            </button>
        </form>
    </div>
    <div class="matches-container">
        <table class="matches-table">
            <thead>
                <tr>
                    <th>Первый игрока</th>
                    <th>Второй игрока</th>
                    <th>Победитель</th>
                </tr>
            </thead>
            <tbody>
                <% for (Match match : matches) { %>
                <tr>
                    <td><%= match.getFirstPlayer().getName()%></td>
                    <td><%= match.getSecondPlayer().getName()%></td>
                    <td><%= match.getWinnerPlayer().getName()%></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</section>
<div class="matches-text-container">
    <%
        int numberPage = (int) request.getAttribute("numberPage");
    %>
    <% if (numberPage > 1) { %>
    <form action="matches" method="get">
        <button class="submit-button" type="submit" name="page" value= <%= numberPage - 1 %>><%= numberPage - 1 %>
        </button>
    </form>
    <%= numberPage %>
    <% if (matches.size() == 15) { %>
    <form action="matches" method="get">
        <button class="submit-button" type="submit" name="page" value= <%= numberPage + 1 %>><%= numberPage + 1 %>
        </button>
    </form>
    <% } %>
    <% } else { %>
    <%= numberPage %>
    <% if (matches.size() == 15) { %>
    <form action="matches" method="get">
        <button class="submit-button" type="submit" name="page" value= <%= numberPage + 1 %>><%= numberPage + 1 %>
        </button>
    </form>
    <% } %>
    <% } %>
</div>
<footer class="footer">
    <p>Created by AsckarUgin</p>
</footer>
</body>
</html>

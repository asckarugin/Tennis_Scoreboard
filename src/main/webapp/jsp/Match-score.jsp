<%@ page import="by.asckarugin.DTO.MatchDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match Score</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <% MatchDTO match = (MatchDTO) request.getAttribute("match"); %>
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
<div class="main-container">

    <div class="match-special-status-div">
        <% if(match.isAdvantage()) {%> <span class="status-massage">ADVANTAGE</span> <%}
    else if (match.isTieBreak()) {%> <span class="status-massage">TIE BREAK</span> <%}
    %>
    </div>

    <div class="scoreboard-div">
        <div class="player-scoreboard-div" id="first-player-scoreboard">
            <div class="player-score-cell-div">
                <h4>SET</h4>
                <h2><%=match.getFirstPlayer().getSet()%></h2>
            </div>

            <div class="player-score-cell-div">
                <h4>GAME</h4>
                <h2><%=match.getFirstPlayer().getGame()%></h2>
            </div>

            <div class="player-score-cell-div">
                <h4>SCORE</h4>
                <h2><%=match.getFirstPlayer().getScore()%></h2>
            </div>

            <div class="player-name-div">
                <h4><%=match.getFirstPlayer().getName()%></h4>
                <div>
                    <form method="post" class="plus-point-button-div">
                        <button type="submit" name="playerName" value=<%= match.getFirstPlayer().getName()%>>Добавить очко</button>
                    </form>
                </div>
            </div>

        </div>

        <div class="player-scoreboard-div" id="second-player-scoreboard">
                <div class="player-score-cell-div">
                    <h4>SET</h4>
                    <h2><%=match.getSecondPlayer().getSet()%></h2>
                </div>

                <div class="player-score-cell-div">
                    <h4>GAME</h4>
                    <h2><%=match.getSecondPlayer().getGame()%></h2>
                </div>

                <div class="player-score-cell-div">
                    <h4>SCORE</h4>
                    <h2><%=match.getSecondPlayer().getScore()%></h2>
                </div>

                <div class="player-name-div">
                    <h3><%=match.getSecondPlayer().getName()%></h3>
                    <div>
                        <form method="post" class="plus-point-button-div">
                            <button type="submit" name="playerName" value=<%= match.getSecondPlayer().getName()%>>Добавить очко</button>
                        </form>
                    </div>
                </div>

            </div>
        </div>
</div>
<footer class="footer">
    <p>Created by AsckarUgin</p>
</footer>
</body>
</html>

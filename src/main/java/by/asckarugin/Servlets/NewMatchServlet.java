package by.asckarugin.Servlets;

import by.asckarugin.Services.OngoingMatchesService;
import by.asckarugin.Utils.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/new-match")
public class NewMatchServlet extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getINSTANCE();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspPath.getPathJsp("New-match")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstPlayer = req.getParameter("playerFirstName");
        String secondPlayer = req.getParameter("playerSecondName");

        UUID matchUUID = ongoingMatchesService.startNewGame(firstPlayer, secondPlayer);

        resp.sendRedirect("/match-score?uuid="+matchUUID.toString());
    }
}

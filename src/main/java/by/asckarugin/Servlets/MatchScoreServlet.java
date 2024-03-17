package by.asckarugin.Servlets;

import by.asckarugin.DTO.MatchDTO;
import by.asckarugin.Services.MatchScoreCalculationService;
import by.asckarugin.Services.OngoingMatchesService;
import by.asckarugin.Utils.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@WebServlet(urlPatterns = "/match-score")
public class MatchScoreServlet extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getINSTANCE();
    private final MatchScoreCalculationService matchScoreCalculationService = MatchScoreCalculationService.getINSTANCE();
    private final Map<UUID, MatchDTO> currentMatches = ongoingMatchesService.getCurrentMatches();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UUID uuid = UUID.fromString(req.getParameter("uuid"));

        MatchDTO currentMatch = currentMatches.get(uuid);

        req.setAttribute("match", currentMatch);
        req.getRequestDispatcher(JspPath.getPathJsp("Match-score")).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        String name = req.getParameter("playerName");

        matchScoreCalculationService.pointToPlayer(uuid, name);

        MatchDTO currentMatch = currentMatches.get(uuid);

        if(currentMatch == null){
            resp.sendRedirect("/matches");
        } else{
            req.setAttribute("match", currentMatch);
            req.getRequestDispatcher(JspPath.getPathJsp("Match-score")).forward(req, resp);
        }

    }
}

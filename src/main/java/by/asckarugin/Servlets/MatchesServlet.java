package by.asckarugin.Servlets;

import by.asckarugin.Models.Match;
import by.asckarugin.Services.FinishedMatchesPersistenceService;
import by.asckarugin.Utils.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/matches")
public class MatchesServlet extends HttpServlet {

    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("filter_by_player_name");
        String page = req.getParameter("page");
        int numberPage = 1;
        List<Match> matches;

        if(page != null){
            numberPage = Integer.parseInt(page);
        }

        if(name == null){
            matches = finishedMatchesPersistenceService.getMatches(numberPage);
        } else{
            matches = finishedMatchesPersistenceService.getMatchesByName(name, numberPage);
        }

        req.setAttribute("matches", matches);
        req.setAttribute("numberPage", numberPage);
        req.getRequestDispatcher(JspPath.getPathJsp("Matches")).forward(req, resp);
    }

}

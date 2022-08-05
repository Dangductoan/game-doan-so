package cybersoft.javabackend.java18.gamedoanso.servlet;

import cybersoft.javabackend.java18.gamedoanso.model.GameSession;
import cybersoft.javabackend.java18.gamedoanso.model.Guess;
import cybersoft.javabackend.java18.gamedoanso.model.Player;
import cybersoft.javabackend.java18.gamedoanso.service.GameService;
import cybersoft.javabackend.java18.gamedoanso.utils.JspUtils;
import cybersoft.javabackend.java18.gamedoanso.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "gameServlet", urlPatterns = {
        UrlUtils.GAME,
        UrlUtils.NEW_GAME,
        UrlUtils.XEP_HANG
})
public class GameServlet extends HttpServlet {
    private GameService gameService;

    // init -> service -> destroy
    @Override
    public void init() throws ServletException {
        super.init();
        gameService = GameService.getINSTANCE();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.GAME, UrlUtils.NEW_GAME -> loadGame(req, resp);
            case UrlUtils.XEP_HANG -> req.getRequestDispatcher(JspUtils.XEP_HANG)
                    .forward(req, resp);
            default -> resp.sendRedirect(req.getContextPath() + UrlUtils.NOT_FOUND);
        }
    }

    private void loadGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var currentUser = (Player) req.getSession().getAttribute("currentUser");
        // create new game/get existed game
        GameSession game = gameService.getCurrentGame(currentUser.getUsername());
        // put in req
        req.setAttribute("game", game);
        req.getRequestDispatcher(JspUtils.GAME)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.GAME -> processGame(req, resp);
            case UrlUtils.NEW_GAME -> processNewGame(req, resp);
            case UrlUtils.XEP_HANG -> req.getRequestDispatcher(JspUtils.XEP_HANG)
                    .forward(req, resp);
            default -> resp.sendRedirect(req.getContextPath() + UrlUtils.NOT_FOUND);
        }
    }

    private void processNewGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var currentUser = (Player) req.getSession().getAttribute("currentUser");
        // create new game/get existed game
        gameService.skipAndPlayNewGame(currentUser.getUsername());
        
        doGet(req, resp);
    }

    private void processGame(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String gameSessionId = req.getParameter("game-session");
        int guessNumber = Integer.parseInt(req.getParameter("guess"));

        var gameSession = gameService.getGameSession(gameSessionId);

        if (gameSession == null) { // if the session is not existed, ask the player to sign in again
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + UrlUtils.DANG_NHAP);
            return;
        }

        if (guessNumber == gameSession.getTargetNumber()) {
            gameSession.getGuess().add(new Guess(guessNumber, gameSessionId, 0));
            gameSession.setCompleted(true);
            sendResultToGameUI(req, resp, gameSession);
            return;
        }

        if (guessNumber > gameSession.getTargetNumber()) {
            gameSession.getGuess().add(new Guess(guessNumber, gameSessionId, 1));
        } else {
            gameSession.getGuess().add(new Guess(guessNumber, gameSessionId, -1));
        }

        sendResultToGameUI(req, resp, gameSession);
    }

    private void sendResultToGameUI(HttpServletRequest req, HttpServletResponse resp, GameSession gameSession) throws ServletException, IOException {
        req.setAttribute("game", gameSession);
        doGet(req, resp);
    }
}

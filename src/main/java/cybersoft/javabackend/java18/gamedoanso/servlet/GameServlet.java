package cybersoft.javabackend.java18.gamedoanso.servlet;

import cybersoft.javabackend.java18.gamedoanso.model.GameSession;
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
            case UrlUtils.GAME -> {
                // get current user
                var currentUser = (Player) req.getSession().getAttribute("currentUser");
                // create new game/get existed game
                GameSession game = gameService.getCurrentGame(currentUser.getUsername());
                // put in req
                req.setAttribute("game", game);
                req.getRequestDispatcher(JspUtils.GAME)
                        .forward(req, resp);
            }
            case UrlUtils.XEP_HANG -> req.getRequestDispatcher(JspUtils.XEP_HANG)
                    .forward(req, resp);
            default -> resp.sendRedirect(req.getContextPath() + UrlUtils.NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.GAME -> processGame(req, resp);
            case UrlUtils.XEP_HANG -> req.getRequestDispatcher(JspUtils.XEP_HANG)
                    .forward(req, resp);
            default -> resp.sendRedirect(req.getContextPath() + UrlUtils.NOT_FOUND);
        }
    }

    private void processGame(HttpServletRequest req, HttpServletResponse resp) {

    }
}

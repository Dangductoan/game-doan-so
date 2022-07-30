package cybersoft.javabackend.java18.gamedoanso.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.java18.gamedoanso.Utils.JspUtils;
import cybersoft.javabackend.java18.gamedoanso.Utils.UrlUtils;

@WebServlet(name = "gameServlet", urlPatterns = {
		UrlUtils.GAME,
		UrlUtils.XEP_HANG
})
public class GameServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
			case UrlUtils.GAME -> req.getRequestDispatcher(JspUtils.GAME)
					.forward(req, resp);
			case UrlUtils.XEP_HANG -> req.getRequestDispatcher(JspUtils.XEP_HANG)
					.forward(req, resp);
		}
	}
}

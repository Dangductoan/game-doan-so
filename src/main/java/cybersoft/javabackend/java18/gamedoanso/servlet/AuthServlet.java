package cybersoft.javabackend.java18.gamedoanso.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.java18.gamedoanso.utils.JspUtils;
import cybersoft.javabackend.java18.gamedoanso.utils.UrlUtils;
import cybersoft.javabackend.java18.gamedoanso.service.GameService;

@WebServlet(name = "authServlet", urlPatterns = {
		UrlUtils.DANG_KY,
		UrlUtils.DANG_NHAP,
		UrlUtils.DANG_XUAT
})
public class AuthServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
			case UrlUtils.DANG_KY -> req.getRequestDispatcher(JspUtils.DANG_KY)
					.forward(req, resp);
			case UrlUtils.DANG_NHAP -> req.getRequestDispatcher(JspUtils.DANG_NHAP)
					.forward(req, resp);
			case UrlUtils.DANG_XUAT -> {
				req.getSession().invalidate();
				resp.sendRedirect(req.getContextPath() + UrlUtils.DANG_NHAP);
			}
			default -> resp.sendRedirect(req.getContextPath() + UrlUtils.NOT_FOUND);
		}
	}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.DANG_KY -> processRegister(req, resp);
            case UrlUtils.DANG_NHAP -> processLogin(req, resp);
            default -> resp.sendRedirect(req.getContextPath() + UrlUtils.NOT_FOUND);
        }
    }

    private void processLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var player = GameService.getINSTANCE().dangNhap(req.getParameter("username"), req.getParameter("password"));

        if (player == null) {
            req.setAttribute("errors", "Username or password is incorrect!");
            this.doGet(req, resp);
        } else {
            req.getSession().setAttribute("currentUser", player);
            resp.sendRedirect(req.getContextPath() + UrlUtils.GAME);
        }
    }

    private void processRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        var newPlayer = GameService.getINSTANCE().dangKy(username, password, name);

        if (newPlayer != null) {
            req.getSession().setAttribute("currentUser", newPlayer);
            resp.sendRedirect(req.getContextPath() + UrlUtils.GAME);
        } else {
            req.setAttribute("errors", "Thông tin người dùng không hợp lệ hoặc đã được sử dụng.");
            doGet(req, resp);
        }
    }
}

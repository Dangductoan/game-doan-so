package cybersoft.javabackend.java18.gamedoanso.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.java18.gamedoanso.Utils.JspUtils;
import cybersoft.javabackend.java18.gamedoanso.Utils.UrlUtils;
import cybersoft.javabackend.java18.gamedoanso.model.NguoiChoi;
import cybersoft.javabackend.java18.gamedoanso.service.GameService;

@WebServlet(name = "authServlet", urlPatterns = {
		UrlUtils.DANG_KY,
		UrlUtils.DANG_NHAP
})
public class AuthServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		switch (req.getServletPath()) {
		case UrlUtils.DANG_KY: 
			req.getRequestDispatcher(JspUtils.DANG_KY)
				.forward(req, resp);
			break;
		case UrlUtils.DANG_NHAP: 
			req.getRequestDispatcher(JspUtils.DANG_NHAP)
			.forward(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
			case UrlUtils.DANG_KY -> processRegister(req, resp);
			case UrlUtils.DANG_NHAP -> processLogin(req, resp);
		}
	}

	private void processLogin(HttpServletRequest req, HttpServletResponse resp) {
		// TODO: process login
	}

	private void processRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String name = req.getParameter("name");

		var newPlayer = GameService.getINSTANCE().dangKy(username, password, name);

		if(newPlayer != null) {
			req.getSession().setAttribute("currentUser", newPlayer);
			resp.sendRedirect(req.getContextPath() + UrlUtils.GAME);
		} else {
			req.getSession().setAttribute("errors", "Thông tin người dùng không hợp lệ hoặc đã được sử dụng.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.DANG_KY);
		}
	}
}

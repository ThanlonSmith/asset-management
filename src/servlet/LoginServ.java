package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;

@WebServlet(name="LoginServ",value="/LoginServ")
public class LoginServ extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String name = new String(request.getParameter("name").getBytes("ISO8859_1"), "utf-8");
		String pwd = new String(request.getParameter("pwd").getBytes("ISO8859_1"), "utf-8");
		AdminDao aDao = new AdminDao();
		if (aDao.login(name, pwd)) {
			request.getSession().setAttribute("admin", name);
			request.getRequestDispatcher("/admin/loginSuccess.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/admin/loginError.jsp").forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
	}

}

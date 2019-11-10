package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import dao.BaseDao;
import entity.Admin;

@WebServlet("/AlterPwdServ")
public class AlterPwdServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String newpwd = request.getParameter("newpwd");
//		System.out.println("name:" + name + "\n" + "pwd:" + pwd + '\n' + "newpwd："+newpwd);
		AdminDao aDao = new AdminDao();
		if (aDao.findAdmin(name, pwd)) {
//			System.out.println("查到这个人");
			if (aDao.alterPwd(name, newpwd)) {
				request.getRequestDispatcher("/SignOutServ").forward(request, response);
//				System.out.println("修改成功!");
			} else {
				request.getRequestDispatcher("/admin/changePwd.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

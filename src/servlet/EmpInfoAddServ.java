package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import entity.Employee;

@WebServlet("/EmpInfoAddServ")
public class EmpInfoAddServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html,charset=utf-8");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		Employee emp = new Employee();
		emp.setId(id);
		emp.setName(name);
		emp.setSex_id(Byte.parseByte(request.getParameter("sex_id")));
		emp.setAge(Integer.parseInt(request.getParameter("age")));
		emp.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
		emp.setPost_id(Integer.parseInt(request.getParameter("post_id")));
		emp.setAddress(request.getParameter("address"));
		emp.setPhone(request.getParameter("phone"));
		emp.setStatus(request.getParameter("status"));
//		System.out.println(id);
//		System.out.println(name);
		AdminDao aDao = new AdminDao();
		if (!aDao.isExists_emp(id)) {
//			System.out.println("1");
			if (!aDao.isExists_byname(name)) {
				if (aDao.empAdd(emp)) {
					request.getRequestDispatcher("/QueryEmpServ").forward(request, response);
				} else {
					request.getRequestDispatcher("/admin/errorPage/addEmpErr.jsp").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("/admin/errorPage/empByNameNoErr.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/admin/errorPage/empManagerNoErr.jsp").forward(request, response);
		}
	}
}

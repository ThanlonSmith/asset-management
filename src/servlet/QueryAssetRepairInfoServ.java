package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import entity.AssetInfo;
import entity.AssetRepairInfo;

@WebServlet("/QueryAssetRepairInfoServ")
public class QueryAssetRepairInfoServ extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		if (page == null || "".equals(page)) {
			page = "1";
		}
		int page_num = Integer.parseInt(page);
		AdminDao aDao = new AdminDao();
		List<AssetRepairInfo> list;
		try {
			list = aDao.queryAssetRepairInfo(page_num);
			if (list!=null) {
				request.setAttribute("list", list);
				request.setAttribute("datacount", aDao.selectRepairAssetCount());
				request.setAttribute("page_num", page_num);
				request.getRequestDispatcher("/admin/repairManage.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

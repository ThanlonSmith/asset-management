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

@WebServlet("/QueryAssetByNameServ")
public class QueryAssetByNameServ extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String page = request.getParameter("page");
		if (page == null || "".equals(page)) {
			page = "1";
		}
		int page_num = Integer.parseInt(page);
		String assetName = request.getParameter("assetName");
		// System.out.println(assetName);
		AdminDao aDao = new AdminDao();
		List<AssetInfo> list;
		try {
			list = aDao.queryAssetByName(assetName);
			if (list != null) {
				request.setAttribute("list", list);
				request.setAttribute("datacount", aDao.selectAssetCountByName(assetName));
				request.setAttribute("page_num", page_num);
				request.getRequestDispatcher("/admin/findAsset.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}

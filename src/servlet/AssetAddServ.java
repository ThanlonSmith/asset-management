package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.AdminDao;
import entity.AssetInfo;

@WebServlet("/AssetAddServ")
public class AssetAddServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		AssetInfo assetInfo = new AssetInfo();
		assetInfo.setAssetNo(request.getParameter("assetNo"));
		assetInfo.setAssetName(request.getParameter("assetName"));
		assetInfo.setAssetUnitPrice(Integer.parseInt(request.getParameter("assetUnitPrice")));
		assetInfo.setAssetNum(Integer.parseInt(request.getParameter("assetNum")));
		assetInfo.setManufacturer(request.getParameter("manufacturer"));
		assetInfo.setUserCompany(request.getParameter("userCompany"));
		assetInfo.setStoragePlace(request.getParameter("storagePlace"));
		assetInfo.setPurchaser(request.getParameter("purchaser"));
		assetInfo.setAssetType(request.getParameter("assetType"));
		assetInfo.setAssetStatus(request.getParameter("assetStatus"));
		assetInfo.setRemark(request.getParameter("remark"));
		AdminDao aDao = new AdminDao();
		try {
			if (aDao.assetAdd(assetInfo)) {
				request.getRequestDispatcher("/QueryAssetInfoServ").forward(request, response);
			} else {
				request.getRequestDispatcher("/admin/addAssetErr.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

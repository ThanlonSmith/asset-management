package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import entity.AssetInfo;

@WebServlet("/AssetEditServ")
public class AssetEditServ extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		AssetInfo assetInfo = new AssetInfo();
		assetInfo.setAssetNo(request.getParameter("assetNo"));
		assetInfo.setAssetName(request.getParameter("assetName"));
		assetInfo.setAssetUnitPrice(Float.parseFloat(request.getParameter("assetUnitPrice")));
		assetInfo.setAssetNum(Integer.parseInt(request.getParameter("assetNum")));
		assetInfo.setManufacturer(request.getParameter("manufacturer"));
		assetInfo.setUserCompany(request.getParameter("userCompany"));
		assetInfo.setStoragePlace(request.getParameter("storagePlace"));
		assetInfo.setPurchaser(request.getParameter("purchaser"));
		assetInfo.setAssetType(request.getParameter("assetType"));
		assetInfo.setAssetStatus(request.getParameter("assetStatus"));
		assetInfo.setRemark(request.getParameter("remark"));
		
//		System.out.println(request.getParameter("assetNo"));
//		System.out.println(request.getParameter("assetName"));
		AdminDao aDao = new AdminDao();
		if (aDao.assetEdit(assetInfo)) {
			request.getRequestDispatcher("/QueryAssetInfoServ").forward(request, response);
		} else {
			request.getRequestDispatcher("/admin/editAssetErr.jsp").forward(request, response);
		}
	}
}

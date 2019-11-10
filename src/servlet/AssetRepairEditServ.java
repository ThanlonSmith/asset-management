package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import entity.AssetInfo;
import entity.AssetRepairInfo;


@WebServlet("/AssetRepairEditServ")
public class AssetRepairEditServ extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=utf-8");
		AssetRepairInfo repairInfo = new AssetRepairInfo();
//		System.out.println(request.getParameter("id"));
		repairInfo.setId(Integer.parseInt(request.getParameter("id")));
		repairInfo.setAssetId(request.getParameter("assetId"));
		repairInfo.setSendRepairPerson(request.getParameter("sendRepairPerson"));
		repairInfo.setPassHandPerson(request.getParameter("passHandPerson"));
		repairInfo.setRepairReason(request.getParameter("repairReason"));
		AdminDao aDao = new AdminDao();	
		if(aDao.assetRepairEdit(repairInfo)) {
			request.getRequestDispatcher("/QueryAssetRepairInfoServ").forward(request, response);
		}else {
			System.out.println("³ö´í");
		}
	}
}

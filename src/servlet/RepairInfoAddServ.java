package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import entity.AssetRepairInfo;

@WebServlet("/RepairInfoAddServ")
public class RepairInfoAddServ extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		AssetRepairInfo repairInfo = new AssetRepairInfo();
//		repairInfo.setSendRepairTime(Date.valueOf(request.getParameter("sendRepairTime")));
		String assetId = request.getParameter("assetId");
		String sendRepairPerson = request.getParameter("sendRepairPerson");
		String passHandPerson = request.getParameter("passHandPerson");
		String repairReason = request.getParameter("repairReason");
		repairInfo.setAssetId(assetId);
		repairInfo.setSendRepairPerson(sendRepairPerson);
		repairInfo.setPassHandPerson(passHandPerson);
		repairInfo.setRepairReason(repairReason);
//		System.out.println(sendRepairPerson);
		AdminDao aDao = new AdminDao();
		if(aDao.isExists_asset(assetId)) {
			if (aDao.addRepairInfo(repairInfo)) {
				request.getRequestDispatcher("/QueryAssetRepairInfoServ").forward(request, response);
			} else {
				request.getRequestDispatcher("/admin/errorPage/addAssetRepairErr.jsp").forward(request, response);
			}
			
		}else {
			request.getRequestDispatcher("/admin/errorPage/repairAssetNoErr.jsp").forward(request, response);
		}
		
	}

}

package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.rowset.internal.Row;

import entity.Admin;
import entity.AssetInfo;
import entity.AssetLend;
import entity.AssetRepairInfo;
import entity.Employee;
import javafx.css.PseudoClass;
import utils.Md5;

public class AdminDao {
	/**
	 * 管理员登录
	 * 
	 * @param name
	 * @param pwd
	 * @return boolean
	 */
	public boolean login(String name, String pwd) {
		Connection conn = BaseDao.getConnection();
		pwd = Md5.md5(pwd, "Thanlon");
//		System.out.println(pwd);
		if (conn != null) {
			String sql = "select *from admin where name='" + name + "' and pwd='" + pwd + "'";
			PreparedStatement stmt;
			ResultSet rs;
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				if (rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 添加资产
	 * 
	 * @param assetInfo
	 * @return
	 * @throws SQLException
	 */
	public boolean assetAdd(AssetInfo assetInfo) throws SQLException {
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "INSERT assetInfo("
					+ "assetNo,assetName,assetUnitPrice,manufacturer,assetNum,userCompany,storagePlace,purchaser,assetType,assetStatus,remark)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, assetInfo.getAssetNo());
				stmt.setString(2, assetInfo.getAssetName());
				stmt.setFloat(3, assetInfo.getAssetUnitPrice());
				stmt.setString(4, assetInfo.getManufacturer());
				stmt.setInt(5, assetInfo.getAssetNum());
				stmt.setString(6, assetInfo.getUserCompany());
				stmt.setString(7, assetInfo.getStoragePlace());
				stmt.setString(8, assetInfo.getPurchaser());
				stmt.setString(9, assetInfo.getAssetType());
				stmt.setString(10, assetInfo.getAssetStatus());
				stmt.setString(11, assetInfo.getRemark());
				int row = stmt.executeUpdate();
				if (row >= 1) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				stmt.close();
				conn.close();
			}
		}
		return false;
	}

	/**
	 * 查询所有资产
	 * 
	 * @return list
	 * @throws SQLException
	 */
	public List<AssetInfo> queryAssetInfo(int page_num) throws SQLException {
		List<AssetInfo> list = new ArrayList<AssetInfo>();
		Connection conn = BaseDao.getConnection();
		int perpage_size = 5;
		if (conn != null) {
			String sql = "select *from assetInfo limit " + (page_num - 1) * perpage_size + "," + perpage_size + "";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while (rs.next()) {
					AssetInfo assetInfo = new AssetInfo();
					assetInfo.setAssetNo(rs.getString("assetNo"));
					assetInfo.setAssetName(rs.getString("assetName"));
					assetInfo.setAssetUnitPrice(rs.getFloat("assetUnitPrice"));
					assetInfo.setManufacturer(rs.getString("manufacturer"));
					assetInfo.setAssetNum(rs.getInt("assetNum"));
					assetInfo.setUserCompany(rs.getString("userCompany"));
					assetInfo.setStoragePlace(rs.getString("storagePlace"));
					assetInfo.setAssetType(rs.getString("assetType"));
					assetInfo.setAssetStatus(rs.getString("assetStatus"));
					assetInfo.setRemark(rs.getString("remark"));
					assetInfo.setPurchaser(rs.getString("purchaser"));
					list.add(assetInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs.close();
				stmt.close();
				conn.close();
			}
		}
		return list;
	}

	public int selectAssetCount() {
		Connection conn = BaseDao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int row = 0;
		String sql = "select * from assetInfo";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				row++;
			}
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	public int selectRepairAssetCount() {
		Connection conn = BaseDao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int row = 0;
		String sql = "select * from assetrepairinfo";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				row++;
			}
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	public int selectRepairEmpCount() {
		Connection conn = BaseDao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int row = 0;
		String sql = "select * from employee";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				row++;
			}
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	public int selectLendCount() {
		Connection conn = BaseDao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int row = 0;
		String sql = "select * from lend";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				row++;
			}
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	public int selectAssetCountByName(String assetName) {
		Connection conn = BaseDao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int row = 0;
		String sql = "SELECT * FROM assetinfo WHERE assetName LIKE '%" + assetName + "%'";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				row++;
			}
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	/**
	 * 刪除资产信息
	 * 
	 * @param assetNo
	 * @return
	 */
	public boolean delAssetInfo(String assetNo) {
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "DELETE FROM assetInfo WHERE assetNo = ?";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, assetNo);
				int row = stmt.executeUpdate();
				System.out.println(row);
				if (row >= 1) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 根据资产编号查询
	 * 
	 * @param assetNo
	 * @return list到修改资产信息
	 * @throws SQLException
	 */
	public List<AssetInfo> queryAssetInfoByAssetNo(String assetNo) throws SQLException {
		List<AssetInfo> list = new ArrayList<AssetInfo>();
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "select *from assetInfo WHERE assetNo = ?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, assetNo);
				rs = stmt.executeQuery();
				while (rs.next()) {
					AssetInfo assetInfo = new AssetInfo();
					assetInfo.setAssetNo(rs.getString("assetNo"));
					assetInfo.setAssetName(rs.getString("assetName"));
					assetInfo.setAssetUnitPrice(rs.getFloat("assetUnitPrice"));
					assetInfo.setManufacturer(rs.getString("manufacturer"));
					assetInfo.setAssetNum(rs.getInt("assetNum"));
					assetInfo.setUserCompany(rs.getString("userCompany"));
					assetInfo.setStoragePlace(rs.getString("storagePlace"));
					assetInfo.setAssetType(rs.getString("assetType"));
					assetInfo.setAssetStatus(rs.getString("assetStatus"));
					assetInfo.setRemark(rs.getString("remark"));
					assetInfo.setPurchaser(rs.getString("purchaser"));
					list.add(assetInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs.close();
				stmt.close();
				conn.close();
			}
		}
		return list;
	}

	/**
	 * 修改资产信息
	 * 
	 * @param assetInfo
	 * @return
	 * @throws SQLException
	 */
	public boolean assetEdit(AssetInfo assetInfo) {
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "UPDATE assetInfo SET assetName=?,assetUnitPrice=?,manufacturer=?,assetNum=?,userCompany=?,storagePlace=?,purchaser=?,assetType=?,assetStatus=?,remark=? WHERE assetNo=?";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, assetInfo.getAssetName());
				stmt.setFloat(2, assetInfo.getAssetUnitPrice());
				stmt.setString(3, assetInfo.getManufacturer());
				stmt.setInt(4, assetInfo.getAssetNum());
				stmt.setString(5, assetInfo.getUserCompany());
				stmt.setString(6, assetInfo.getStoragePlace());
				stmt.setString(7, assetInfo.getPurchaser());
				stmt.setString(8, assetInfo.getAssetType());
				stmt.setString(9, assetInfo.getAssetStatus());
				stmt.setString(10, assetInfo.getRemark());
				stmt.setString(11, assetInfo.getAssetNo());
				int row = stmt.executeUpdate();
//				System.out.println(row);
				if (row >= 1) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 添加員工信息
	 * 
	 * @param assetInfo
	 * @return
	 * @throws SQLException
	 */
	public boolean empAdd(Employee emp) {
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "INSERT employee(" + "id,name,sex_id,age,department_id,post_id,address,phone,status)"
					+ "values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, emp.getId());
				stmt.setString(2, emp.getName());
				stmt.setByte(3, emp.getSex_id());
				stmt.setInt(4, emp.getAge());
				stmt.setInt(5, emp.getDepartment_id());
				stmt.setInt(6, emp.getPost_id());
				stmt.setString(7, emp.getAddress());
				stmt.setString(8, emp.getPhone());
				stmt.setString(9, emp.getStatus());
				int row = stmt.executeUpdate();
				if (row >= 1) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 查询所有资产
	 * 
	 * @return list
	 * @throws SQLException
	 */
	public List<Employee> queryEmpInfo(int page_num) throws SQLException {
		List<Employee> list = new ArrayList<Employee>();
		Connection conn = BaseDao.getConnection();
		int perpage_size = 5;
		if (conn != null) {
			String sql = "SELECT *FROM employee AS e" + " INNER JOIN post AS p ON e.post_id = p.id"
					+ " INNER JOIN department as d ON d.id = e.department_id LIMIT " + (page_num - 1) * perpage_size
					+ "," + perpage_size;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while (rs.next()) {
					Employee emp = new Employee();
					emp.setId(rs.getString("id"));
					emp.setName(rs.getString("name"));
					emp.setSex_id(rs.getByte("sex_id"));
					emp.setAge(rs.getInt("age"));
					emp.setDepart_name(rs.getString("depart_name"));
					emp.setPost_name(rs.getString("post_name"));
					emp.setPost_id(rs.getInt("post_id"));
					emp.setAddress(rs.getString("address"));
					emp.setPhone(rs.getString("phone"));
					emp.setStatus(rs.getString("status"));
					list.add(emp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs.close();
				stmt.close();
				conn.close();
			}
		}
		return list;
	}

	/**
	 * 添加维修信息
	 * 
	 * @param assetInfo
	 * @return
	 * @throws SQLException
	 */
	public boolean addRepairInfo(AssetRepairInfo repairInfo) {
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "INSERT assetrepairinfo(" + "assetId,sendRepairPerson,intermediator,reason)"
					+ "values(?,?,?,?)";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, repairInfo.getAssetId());
//				stmt.setDate(2, null);
				stmt.setString(2, repairInfo.getSendRepairPerson());
				stmt.setString(3, repairInfo.getPassHandPerson());
				stmt.setString(4, repairInfo.getRepairReason());
				int row = stmt.executeUpdate();
				if (row >= 1) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 查询維修信息
	 * 
	 * @return list
	 * @throws SQLException
	 */
	public List<AssetRepairInfo> queryAssetRepairInfo(int page_num) throws SQLException {
		List<AssetRepairInfo> list = new ArrayList<AssetRepairInfo>();
		Connection conn = BaseDao.getConnection();
		int perpage_size = 5;
		if (conn != null) {
			String sql = "select *from assetrepairinfo LIMIT " + (page_num - 1) * perpage_size + "," + perpage_size;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while (rs.next()) {
					AssetRepairInfo assetRepairInfo = new AssetRepairInfo();
					assetRepairInfo.setId(rs.getInt("id"));
					// System.out.println(rs.getInt("id"));
					assetRepairInfo.setAssetId(rs.getString("assetId"));
					assetRepairInfo.setSendRepairTime(rs.getDate("sendRepairTime"));
					assetRepairInfo.setSendRepairPerson(rs.getString("sendRepairPerson"));
					assetRepairInfo.setPassHandPerson(rs.getString("intermediator"));
					assetRepairInfo.setRepairReason(rs.getString("reason"));
					list.add(assetRepairInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs.close();
				stmt.close();
				conn.close();
			}
		}
		return list;
	}

	/**
	 * 借出资产
	 * 
	 * @param assetInfo
	 * @return
	 * @throws SQLException
	 */
	public boolean assetLendAdd(AssetLend assetLend) throws SQLException {
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "INSERT lend(asset_id,employee_id,shouldReturnTime) "
					+ "values(?,?,date_add(now(),interval 3 day))";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, assetLend.getAsset_id());
				stmt.setString(2, assetLend.getEmployee_id());
//				stmt.setDate(3, (Date) assetLend.getLendTime());
				int row = stmt.executeUpdate();
				if (row > 0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				stmt.close();
				conn.close();
			}
		}
		return false;
	}

	/**
	 * 查询借还信息
	 * 
	 * @return list
	 * @throws SQLException
	 */
	public List<AssetLend> queryAssetLendInfo(int page_num) throws SQLException {
		List<AssetLend> list = new ArrayList<AssetLend>();
		Connection conn = BaseDao.getConnection();
		int perpage_size = 5;
		if (conn != null) {
			String sql = "select *from lend AS l LEFT JOIN employee AS e ON l.employee_id = e.id limit "
					+ (page_num - 1) * perpage_size + "," + perpage_size;
//			System.out.println(sql);
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while (rs.next()) {
					AssetLend assetLend = new AssetLend();
					assetLend.setId(rs.getInt("id"));
					assetLend.setAsset_id(rs.getString("asset_id"));
					assetLend.setEmployee_id(rs.getString("employee_id"));
					assetLend.setLender(rs.getString("name"));
					assetLend.setLendTime(rs.getDate("lendTime"));
					assetLend.setShouldReturnTime(rs.getDate("shouldReturnTime"));
					list.add(assetLend);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs.close();
				stmt.close();
				conn.close();
			}
		}
		return list;
	}

	/**
	 * 刪除借出资产
	 * 
	 * @param assetNo
	 * @return
	 */
	public boolean delAssetLend(String lendNo) {
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "DELETE FROM lend WHERE id = ?";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, lendNo);
				int row = stmt.executeUpdate();
//				System.out.println(row);
				if (row >= 1) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 刪除资产维修信息
	 * 
	 * @param assetNo
	 * @return
	 */
	public boolean delAssetRepair(String id) {
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "DELETE FROM assetrepairinfo WHERE id = ?";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				int row = stmt.executeUpdate();
				System.out.println(row);
				if (row >= 1) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 刪除员工
	 * 
	 * @param assetNo
	 * @return
	 */
	public boolean delEmp(String id) {
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "DELETE FROM employee WHERE id = ?";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				int row = stmt.executeUpdate();
				System.out.println(row);
				if (row >= 1) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 根据资产id查询
	 * 
	 * @param no
	 * @return list到修改资产信息
	 * @throws SQLException
	 */
	public List<AssetRepairInfo> queryRepairByNo(String id) throws SQLException {
		List<AssetRepairInfo> list = new ArrayList<AssetRepairInfo>();
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "select *from assetrepairinfo WHERE id = ?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				rs = stmt.executeQuery();
				while (rs.next()) {
					AssetRepairInfo assetRepairInfo = new AssetRepairInfo();
					assetRepairInfo.setAssetId(rs.getString("assetId"));
					assetRepairInfo.setSendRepairTime(rs.getDate("sendRepairTime"));
					assetRepairInfo.setSendRepairPerson(rs.getString("sendRepairPerson"));
					assetRepairInfo.setPassHandPerson(rs.getString("intermediator"));
					assetRepairInfo.setRepairReason(rs.getString("reason"));
					assetRepairInfo.setId(rs.getInt("id"));
					list.add(assetRepairInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs.close();
				stmt.close();
				conn.close();
			}
		}
		return list;
	}

	/**
	 * 修改维修信息
	 * 
	 * @param assetInfo
	 * @return
	 * @throws SQLException
	 */
	public boolean assetRepairEdit(AssetRepairInfo repairInfo) {
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "UPDATE assetrepairinfo SET assetId=?,sendRepairPerson=?,intermediator=?,reason=? WHERE id=?";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, repairInfo.getAssetId());
				stmt.setString(2, repairInfo.getSendRepairPerson());
				stmt.setString(3, repairInfo.getPassHandPerson());
				stmt.setString(4, repairInfo.getRepairReason());
//				System.out.println(repairInfo.getId());
				stmt.setInt(5, repairInfo.getId());
				int row = stmt.executeUpdate();
				if (row >= 1) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 员工信息修改 根据员工id查询
	 * 
	 * @param no
	 * @return list到修改资产信息
	 * @throws SQLException
	 */
	public List<Employee> queryEmpByNo(String id) throws SQLException {
		List<Employee> list = new ArrayList<Employee>();
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "select *from employee WHERE id = ?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				rs = stmt.executeQuery();
				while (rs.next()) {
					Employee emp = new Employee();
					emp.setId(rs.getString("id"));
					emp.setName(rs.getString("name"));
					emp.setSex_id(rs.getByte("sex_id"));
					emp.setAge(rs.getInt("age"));
					emp.setDepartment_id(rs.getInt("department_id"));
					emp.setPost_id(rs.getInt("post_id"));
					emp.setAddress(rs.getString("address"));
					emp.setPhone(rs.getString("phone"));
					emp.setStatus(rs.getString("status"));
					list.add(emp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs.close();
				stmt.close();
				conn.close();
			}
		}
		return list;
	}

	/**
	 * 编辑員工信息
	 * 
	 * @param assetInfo
	 * @return
	 * @throws SQLException
	 */
	public boolean empEdit(Employee emp) {
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "UPDATE employee SET name=?,sex_id=?,age=?,department_id=?,post_id=?,address=?,phone=?,status=? WHERE id=?";
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);

				stmt.setString(1, emp.getName());
//				System.out.println(emp.getName());
				stmt.setByte(2, emp.getSex_id());
				stmt.setInt(3, emp.getAge());
				stmt.setInt(4, emp.getDepartment_id());
				stmt.setInt(5, emp.getPost_id());
				stmt.setString(6, emp.getAddress());
				stmt.setString(7, emp.getPhone());
				stmt.setString(8, emp.getStatus());
//				System.out.println(emp.getStatus());
				stmt.setString(9, emp.getId());
//				System.out.println(emp.getId());
				int row = stmt.executeUpdate();
				if (row >= 1) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 根据资产名称查询资产
	 * 
	 * @return list
	 * @throws SQLException
	 */
	public List<AssetInfo> queryAssetByName(String assetName) throws SQLException {
		List<AssetInfo> list = new ArrayList<AssetInfo>();
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			// System.out.println(assetName);
			String sql = "SELECT *FROM assetInfo WHERE assetName LIKE '%" + assetName + "%'";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while (rs.next()) {
					AssetInfo assetInfo = new AssetInfo();
					assetInfo.setAssetNo(rs.getString("assetNo"));
					assetInfo.setAssetName(rs.getString("assetName"));
					assetInfo.setAssetUnitPrice(rs.getFloat("assetUnitPrice"));
					assetInfo.setManufacturer(rs.getString("manufacturer"));
					assetInfo.setAssetNum(rs.getInt("assetNum"));
					assetInfo.setUserCompany(rs.getString("userCompany"));
					assetInfo.setStoragePlace(rs.getString("storagePlace"));
					assetInfo.setAssetType(rs.getString("assetType"));
					assetInfo.setAssetStatus(rs.getString("assetStatus"));
					assetInfo.setRemark(rs.getString("remark"));
					assetInfo.setPurchaser(rs.getString("purchaser"));
					list.add(assetInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs.close();
				stmt.close();
				conn.close();
			}
		}
		return list;
	}

	public Boolean findAdmin(String name, String pwd) {
		Connection conn = BaseDao.getConnection();
		pwd = Md5.md5(pwd, "Thanlon");
		if (conn != null) {
			String sql = "SELECT count(*) FROM admin WHERE name='" + name + "' and pwd='" + pwd + "'";
//			System.out.println(sql);
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				int row = 0;
//				System.out.println(rs);
				while (rs.next()) {
					row++;
				}
//				System.out.println(row);
				if (row > 0) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Boolean alterPwd(String name, String newpwd) {
		Connection conn = BaseDao.getConnection();
		newpwd = Md5.md5(newpwd, "Thanlon");
//		System.out.println(newpwd);
		if (conn != null) {
			String sql = "UPDATE admin set pwd='" + newpwd + "' WHERE name='" + name + "'";
//			System.out.println(sql);
			PreparedStatement stmt = null;
			int row = 0;
			try {
				stmt = conn.prepareStatement(sql);
				row = stmt.executeUpdate();
				if (row > 0) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean isExists_asset(String asset_id) {
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "SELECT *from assetinfo WHERE assetNo='" + asset_id + "'";
//			System.out.println(sql);
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				int row = 0;
				while (rs.next()) {
					row++;
				}
				if (row > 0) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean isExists_emp(String id) {
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "SELECT *from employee WHERE id='" + id + "'";
//			System.out.println(sql);
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				int row = 0;
				while (rs.next()) {
					row++;
				}
				if (row > 0) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean isExists_byname(String name) {
		Connection conn = BaseDao.getConnection();
		if (conn != null) {
			String sql = "SELECT *from employee WHERE name='" + name + "'";
//			System.out.println(sql);
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				int row = 0;
				while (rs.next()) {
					row++;
				}
				if (row > 0) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}

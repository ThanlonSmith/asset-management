package entity;

import java.sql.Date;

public class AssetLend {

	private int id;
	private String lender;
	private Date lendTime;
	private Date returnTime;
	private Date shouldReturnTime;
	private String employee_id;
	private String asset_id;
	public AssetLend() {
	}
	public AssetLend(int id, String lender, Date lendTime, Date returnTime, Date shouldReturnTime, String employee_id,
			String asset_id) {
		this.id = id;
		this.lender = lender;
		this.lendTime = lendTime;
		this.returnTime = returnTime;
		this.shouldReturnTime = shouldReturnTime;
		this.employee_id = employee_id;
		this.asset_id = asset_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLender() {
		return lender;
	}
	public void setLender(String lender) {
		this.lender = lender;
	}
	public Date getLendTime() {
		return lendTime;
	}
	public void setLendTime(Date lendTime) {
		this.lendTime = lendTime;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	public Date getShouldReturnTime() {
		return shouldReturnTime;
	}
	public void setShouldReturnTime(Date shouldReturnTime) {
		this.shouldReturnTime = shouldReturnTime;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}
}

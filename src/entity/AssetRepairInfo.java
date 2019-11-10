package entity;

import java.sql.Date;

public class AssetRepairInfo {
	private int id;
	private String assetId;
	private Date sendRepairTime;
	private String sendRepairPerson;
	private String passHandPerson;
	private String repairReason;

	public AssetRepairInfo() {

	}

	public AssetRepairInfo(int id, String assetId, Date sendRepairTime, String sendRepairPerson, String passHandPerson,
			String repairReason) {
		this.id = id;
		this.assetId = assetId;
		this.sendRepairTime = sendRepairTime;
		this.sendRepairPerson = sendRepairPerson;
		this.passHandPerson = passHandPerson;
		this.repairReason = repairReason;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public Date getSendRepairTime() {
		return sendRepairTime;
	}

	public void setSendRepairTime(Date sendRepairTime) {
		this.sendRepairTime = sendRepairTime;
	}

	public String getSendRepairPerson() {
		return sendRepairPerson;
	}

	public void setSendRepairPerson(String sendRepairPerson) {
		this.sendRepairPerson = sendRepairPerson;
	}

	public String getPassHandPerson() {
		return passHandPerson;
	}

	public void setPassHandPerson(String passHandPerson) {
		this.passHandPerson = passHandPerson;
	}

	public String getRepairReason() {
		return repairReason;
	}

	public void setRepairReason(String repairReason) {
		this.repairReason = repairReason;
	}
}

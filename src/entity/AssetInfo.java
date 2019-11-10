package entity;

public class AssetInfo {
	private String assetNo;
	private String assetName;
	private float assetUnitPrice;
	private int assetNum;
	private String manufacturer;
	private String userCompany;
	private String storagePlace;
	private String purchaser;
	private String assetType;
	private String assetStatus;
	private String remark;

	public AssetInfo() {
		
	}

	public AssetInfo(String assetNo, String assetName, float assetUnitPrice, int assetNum, String manufacturer,
			String userCompany, String storagePlace, String purchaser, String assetType, String assetStatus,
			String remark) {
		super();
		this.assetNo = assetNo;
		this.assetName = assetName;
		this.assetUnitPrice = assetUnitPrice;
		this.assetNum = assetNum;
		this.manufacturer = manufacturer;
		this.userCompany = userCompany;
		this.storagePlace = storagePlace;
		this.purchaser = purchaser;
		this.assetType = assetType;
		this.assetStatus = assetStatus;
		this.remark = remark;
	}

	public String getAssetNo() {
		return assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public float getAssetUnitPrice() {
		return assetUnitPrice;
	}

	public void setAssetUnitPrice(float assetUnitPrice) {
		this.assetUnitPrice = assetUnitPrice;
	}

	public int getAssetNum() {
		return assetNum;
	}

	public void setAssetNum(int assetNum) {
		this.assetNum = assetNum;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}

	public String getStoragePlace() {
		return storagePlace;
	}

	public void setStoragePlace(String storagePlace) {
		this.storagePlace = storagePlace;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getAssetStatus() {
		return assetStatus;
	}

	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}

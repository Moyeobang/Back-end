package com.ssafy.store;

public class StoreDto {
	String storeId;
	String storeName;
	String category;
	String dongCode;
	String address;
	String latitude;
	String longitude;
	
	public StoreDto() {}
	
	public StoreDto(String storeId, String storeName, String category, String dongCode, String address, String latitude,
			String longitude) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.category = category;
		this.dongCode = dongCode;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "StoreDto [storeId=" + storeId + ", storeName=" + storeName + ", category=" + category + ", dongCode="
				+ dongCode + ", address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}

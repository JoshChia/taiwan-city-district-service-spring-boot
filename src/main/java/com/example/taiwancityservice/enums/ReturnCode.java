package com.example.taiwancityservice.enums;

public enum ReturnCode {

	SUCCESS("0000","處理成功"),
	NO_RESULTS("0001","查無資料"),

	FIELD_FORMAT_ERROR("1001"," 輸入欄位缺漏或格式有誤"), 
	JSON_FORMAT_ERROR("1002","JSON格式錯誤"),  
	
	DB_CONNECTION_ERROR("2001","資料庫連線異常"),  
	DB_QUERY_ERROR("2002","資料庫查詢錯誤"),  
	DB_INSERT_ERROR("2003","資料新增失敗,該筆資料已存在資料庫中"),  
	DB_UPDATE_ERROR("2004","資料庫更新錯誤"),  
	DB_DELETE_ERROR("2005","資料庫刪除錯誤"),
	IMAGE_FORMAT_ERROR("4001", "檔案上傳失敗,檔案格式不符"),
	IMAGE_SIZE_TOO_LARGE("4002", "檔案上傳失敗,檔案大小過大"),
	IMAGE_STORE_ERROR("4004", "圖檔儲存失敗"),
	IMAGE_FETCH_FAILURE("4003","圖檔取得失敗"),
	FILE_DOWNLOAD_FAILURE("4006", "檔案下載失敗,找不到檔案或其他原因"),
	FILE_DELETE_FAILURE("4007", "檔案刪除失敗"),
	FILE_UPLOAD_FAILURE("4008", "上傳檔案儲存失敗"),
	
	BACKEND_CONNECTION_ERROR("3001","後台系統連線異常"),  
	BACKEND_DATA_TRANSFER_ERROR("3002","後台系統資料傳輸異常"),  
	
	UNKNOWN_ERROR("9999","其他未定義錯誤");
 
	String code;
	String message;
	ReturnCode(String code, String message) {
		 this.code=code;
		 this.message=message;
	}
	public String getCode() {
		return code;
	}
	void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	void setMessage(String message) {
		this.message = message;
	}  
	 
}

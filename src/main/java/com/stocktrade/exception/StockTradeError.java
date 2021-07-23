package com.stocktrade.exception;

import java.io.Serializable;

public class StockTradeError implements Serializable {

	private static final long serialVersionUID = 1L;
	private String errorid;
	private String errorMsg;

	public StockTradeError() {}

	public StockTradeError(String errorid, String errorMsg) {
		super();
		this.errorid = errorid;
		this.errorMsg = errorMsg;
	}

	public String getErrorid() {
		return errorid;
	}

	public void setErrorid(String errorid) {
		this.errorid = errorid;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}

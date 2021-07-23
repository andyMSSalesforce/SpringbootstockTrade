package com.stocktrade.model;

import java.io.Serializable;

import com.stocktrade.exception.StockTradeError;

public class StockTradeResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private StockTrade stockTrade;
	private StockTradeError error;

	public StockTradeResponse() {
	}

	public StockTradeResponse(StockTrade stockTrade, StockTradeError error) {
		super();
		this.stockTrade = stockTrade;
		this.error = error;
	}

	public StockTradeError getError() {
		return error;
	}

	public void setError(StockTradeError error) {
		this.error = error;
	}

	public StockTrade getStockTrade() {
		return stockTrade;
	}

	public void setStockTrade(StockTrade stockTrade) {
		this.stockTrade = stockTrade;
	}

}

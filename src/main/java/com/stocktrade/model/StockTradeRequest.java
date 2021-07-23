package com.stocktrade.model;

import java.io.Serializable;

public class StockTradeRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	public String type;
	public Integer user_id;
	public String symbol;
	public Integer shares;
	public Integer price;

	public StockTradeRequest(String type, Integer user_id, String symbol, Integer shares, Integer price,
			Long timestamp) {
		super();
		this.type = type;
		this.user_id = user_id;
		this.symbol = symbol;
		this.shares = shares;
		this.price = price;
		this.timestamp = timestamp;
	}

	public Long timestamp;

	public StockTradeRequest() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Integer getShares() {
		return shares;
	}

	public void setShares(Integer shares) {
		this.shares = shares;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}

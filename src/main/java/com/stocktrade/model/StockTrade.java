package com.stocktrade.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "StockTrade.searchByTypeId", query = "SELECT p FROM StockTrade p WHERE LOWER(p.type) = LOWER(?1)")
@NamedQuery(name = "StockTrade.searchByUserId", query = "SELECT p FROM StockTrade p WHERE p.user_id = ?1")
public class StockTrade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	public Integer id;

	public String type;
	public Integer user_id;
	public String symbol;
	public Integer shares;
	public Integer price;
	public Long timestamp;

	public StockTrade() {
	}

	public StockTrade(Integer id, String type, Integer user_id, String symbol, Integer shares, Integer price,
			Long timestamp) {
		super();
		this.id = id;
		this.type = type;
		this.user_id = user_id;
		this.symbol = symbol;
		this.shares = shares;
		this.price = price;
		this.timestamp = timestamp;
	}

	public StockTrade(String type, Integer user_id, String symbol, Integer shares, Integer price, Long timestamp) {
		super();
		this.type = type;
		this.user_id = user_id;
		this.symbol = symbol;
		this.shares = shares;
		this.price = price;
		this.timestamp = timestamp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

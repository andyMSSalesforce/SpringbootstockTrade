package com.stocktrade.component;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stocktrade.exception.SystemException;
import com.stocktrade.model.StockTrade;
import com.stocktrade.model.StockTradeRequest;
import com.stocktrade.service.StockTradeService;

@Component
public class StockTradeComponent {

	@Autowired
	StockTradeService stockTradeService;

	public List<StockTrade> getTrades(Optional<String> typeId, Optional<String> userId) throws SystemException {
		String stockTradeTypeId = (String) stockTradeService.getTypeId(typeId);
		if (stockTradeTypeId != null) {
			return stockTradeService.searchByTypeId(stockTradeTypeId);
		}

		String stockTradeUserId = (String) stockTradeService.getUserId(userId);
		if (stockTradeUserId != null) {
			return stockTradeService.searchByUserId(stockTradeUserId);
		}
		return null;
	}

	public StockTrade getTrade(String tradeId) {
		return stockTradeService.getTrade(tradeId).get(0);
	}

	public StockTrade createStockTrade(StockTradeRequest stockTradeRequest) throws SystemException {

		StockTrade stockTrade = new StockTrade(stockTradeRequest.getType(), stockTradeRequest.getUser_id(),
				stockTradeRequest.getSymbol(), stockTradeRequest.getShares(), stockTradeRequest.getPrice(),
				stockTradeRequest.getTimestamp());
		try {
			stockTrade = stockTradeService.save(stockTrade);
			return stockTrade;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException("204", String.format("userId with exception msg: %s.", e.getMessage()));
		}
	}

}

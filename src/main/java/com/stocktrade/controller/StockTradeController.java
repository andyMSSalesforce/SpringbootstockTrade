package com.stocktrade.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocktrade.component.StockTradeComponent;
import com.stocktrade.exception.StockTradeError;
import com.stocktrade.exception.SystemException;
import com.stocktrade.model.StockTrade;
import com.stocktrade.model.StockTradeRequest;
import com.stocktrade.model.StockTradeResponse;

@RestController
public class StockTradeController {

	private static final Log LOGGING = LogFactory.getLog(StockTradeController.class);

	@Autowired
	StockTradeComponent stockTradeComponent;

	@Autowired(required = false)
	private String json;

	@GetMapping("/trades/?typeId={typeId}&userId={userId}")
	public ResponseEntity<StockTradeResponse> getTrades(@RequestParam Optional<String> typeId,
			@RequestParam Optional<String> userId) {
		List<StockTrade> listStockTrade = null;
		LOGGING.info("info logging");
		try {
			listStockTrade = stockTradeComponent.getTrades(typeId, userId);
		} catch (SystemException e) {
			LOGGING.error("Logging for exception - server error" + e.getMsg());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new StockTradeResponse(null, new StockTradeError(e.getErrId(), e.getMsg())));
		}
		if (listStockTrade != null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				json = mapper.writeValueAsString(listStockTrade);
			} catch (JsonProcessingException e) {
				LOGGING.error("Logging for exception - server error - convert StockTrade to json");
				e.printStackTrace();
			}
			LOGGING.info("info logging StockTrade - " + json.toString());
			// get first element from list
			StockTrade stockTradeaux = listStockTrade.get(0);
			return ResponseEntity.status(HttpStatus.OK).body(new StockTradeResponse(stockTradeaux, null));
		} else {
			LOGGING.error("Logging for exception - server error - StockTrade is null");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new StockTradeResponse(null, new StockTradeError("102", "StockTrade is null")));
		}
	}

	@GetMapping("/trades/?Id={tradeId}")
	public ResponseEntity<StockTradeResponse> getTrade(@RequestParam String tradeId) {
		StockTrade stockTrade = null;
		LOGGING.info("info logging");
		try {
			stockTrade = stockTradeComponent.getTrade(tradeId);
		} catch (SystemException e) {
			LOGGING.error("Logging for exception - server error" + e.getMsg());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new StockTradeResponse(null, new StockTradeError(e.getErrId(), e.getMsg())));
		}
		if (stockTrade != null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				json = mapper.writeValueAsString(stockTrade);
			} catch (JsonProcessingException e) {
				LOGGING.error("Logging for exception - server error - convert StockTrade to json");
				e.printStackTrace();
			}
			LOGGING.info("info logging StockTrade - " + json.toString());
			return ResponseEntity.status(HttpStatus.OK).body(new StockTradeResponse(stockTrade, null));
		} else {
			LOGGING.error("Logging for exception - server error - StockTrade is null");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new StockTradeResponse(null, new StockTradeError("102", "StockTrade is null")));
		}
	}

	@PostMapping(value = "/trade/{tradeId}")
	public ResponseEntity<StockTradeResponse> createStockTrade(@RequestBody StockTradeRequest stockTradeRequest) {
		StockTrade stockTrade = new StockTrade();
		try {
			stockTrade = stockTradeComponent.createStockTrade(stockTradeRequest);
		} catch (SystemException e) {
			LOGGING.error("Logging for exception - server error" + e.getMsg());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new StockTradeResponse(null, new StockTradeError(e.getErrId(), e.getMsg())));
		}

		if (stockTrade != null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				json = mapper.writeValueAsString(stockTrade);
			} catch (JsonProcessingException e) {
				LOGGING.error("Logging for exception - server error - convert StockTrade to json");
				e.printStackTrace();
			}
			LOGGING.info("info logging StockTrade - " + json.toString());
			return ResponseEntity.status(HttpStatus.CREATED).body(new StockTradeResponse(stockTrade, null));
		} else {
			LOGGING.error("Logging for exception - server error - StockTrade is null");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new StockTradeResponse(null, new StockTradeError("204", "StockTrade is null")));
		}
	}

	@DeleteMapping(value = "/trade/{tradeId}")
	public ResponseEntity<StockTradeResponse> deleteStockTrade(@RequestParam String tradeId) {
		StockTrade stockTrade = new StockTrade();
		stockTrade.setId(Integer.parseInt(tradeId));
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new StockTradeResponse(null, new StockTradeError("404", "StockTrade is Not Allowed")));
	}
}

package com.stocktrade.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stocktrade.exception.SystemException;
import com.stocktrade.model.StockTrade;
import com.stocktrade.repository.StockTradeRepository;

@Service
public class StockTradeService {

	@Autowired
	private StockTradeRepository repository;

	public String getTypeId(Optional<String> typeId) {
		if (typeId.isPresent()) {
			try {
				return typeId.get();
			} catch (Exception e) {
				throw new SystemException("103", String.format("typeId with exception msg: %s.", e.getMessage()));
			}
		}
		return null;
	}

	public String getUserId(Optional<String> userId) {
		if (userId.isPresent()) {
			try {
				return userId.get();
			} catch (Exception e) {
				throw new SystemException("103", String.format("userId with exception msg: %s.", e.getMessage()));
			}
		}
		return null;
	}

	public List<StockTrade> getTrade(String tradeId) {
		return toList(this.findById(Integer.parseInt(tradeId)));
	}

	public static <T> List<T> toList(Optional<T> opt) {
		return opt.map(Collections::singletonList).orElseGet(Collections::emptyList);
	}

	public List<StockTrade> findAll() {
		return repository.findAll();
	}

	public List<StockTrade> searchByTypeId(String typeId) {
		return repository.searchByTypeId(typeId);
	}

	public List<StockTrade> searchByUserId(String userId) {
		return repository.searchByUserId(userId);
	}

	public Optional<StockTrade> findById(Integer id) {
		return repository.findById(id);
	}

	public StockTrade save(StockTrade post) {
		return repository.saveAndFlush(post);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}
}

package com.stocktrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocktrade.model.StockTrade;

@Repository
public interface StockTradeRepository extends JpaRepository<StockTrade, Integer> {

	List<StockTrade> searchByTypeId(String typeId);

	List<StockTrade> searchByUserId(String userId);
}

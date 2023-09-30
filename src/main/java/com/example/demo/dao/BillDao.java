package com.example.demo.dao;

import com.example.demo.dto.BillDetailsDto;
import com.example.demo.dto.BillHistoryDto;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@Dao
@ConfigAutowireable
public interface BillDao {
	@Select
	List<BillHistoryDto> getBillHistory(Integer customerId);

	@Select
	BillDetailsDto getBillDetails(Integer billId, Integer customerId);
}
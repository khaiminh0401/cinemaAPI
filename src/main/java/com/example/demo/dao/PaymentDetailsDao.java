package com.example.demo.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.dto.PaymentDetailsDto;
import com.example.demo.entity.PaymentDetails;

@Dao
@ConfigAutowireable
public interface PaymentDetailsDao {
    @Select
    List<PaymentDetailsDto> findAll();

    @Insert
    int insert(PaymentDetails paymentDetails);

    @Update(include = {"id", "status"})
    int updateStatus(PaymentDetails paymentDetails);

    @Select
    PaymentDetails findByTransactionNo(String vnp_TransactionNo);
}

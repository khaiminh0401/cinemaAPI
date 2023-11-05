package com.example.demo.dto;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import lombok.Data;

@Data
@Entity
public class PaymentDetailsDto {
    @Column
    private String id;
    @Column
    private String paymethodid;
    @Column
    private String staffid;
    @Column
    private Integer billid;
    @Column
    private Boolean paystatus;
    @Column(name = "staffName")
    private String staffName;
    @Column(name = "paymentmethodName")
    private String paymentmethodName;
}

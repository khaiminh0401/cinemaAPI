package com.example.demo.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(immutable = true)
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topping {
   @Id
   @Column
   private String id;
   @Column
   private String name;
   @Column
   private double price;
   @Column
   private String Image;
   
   
}

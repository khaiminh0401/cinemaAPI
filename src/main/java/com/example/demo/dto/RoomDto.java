package com.example.demo.dto;

import java.sql.Time;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Transient;

import com.example.demo.entity.Room;

import lombok.Data;

@Data
@Entity
public class RoomDto extends Room {
	@Column(name = "branchName")
	private String branchName;
	@Column(name="starttime")
	private Time starttime;
	@Column
	private String total;
	@Column
	private String status;
}

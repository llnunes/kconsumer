package com.live.kafka.consumer.dtos;

import java.io.Serializable;

public class CarDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String model;
	private String color;
	
	public CarDTO() {		
	}
	
	public CarDTO(String model, String color) {
		super();
		this.model = model;
		this.color = color;
	}
	public CarDTO(String id, String model, String color) {
		super();
		this.id = id;
		this.model = model;
		this.color = color;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CarDTO [id=");
		builder.append(id);
		builder.append(", model=");
		builder.append(model);
		builder.append(", color=");
		builder.append(color);
		builder.append("]");
		return builder.toString();
	}	
}

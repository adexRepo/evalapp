package com.kkp.evalapp.model;


public abstract class SimpleEntity {

	private Integer id;

	protected SimpleEntity() {
		super();
	}

	public SimpleEntity(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	protected void setId(Integer id) {
		this.id = id;
	}
}

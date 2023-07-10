package com.kkp.evalapp.model;


public abstract class SimpleEntity {

	private Long id;

	protected SimpleEntity() {
		super();
	}

	public SimpleEntity(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}
}

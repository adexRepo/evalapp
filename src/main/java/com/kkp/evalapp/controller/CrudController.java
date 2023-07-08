package com.kkp.evalapp.controller;

import com.kkp.evalapp.model.SimpleEntity;

public interface CrudController {

	public void add();

	public void render(SimpleEntity id);
	
	public void save();
	
}
package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Area;
import com.uniovi.repositories.AreaRepository;

@Service
public class AreaService {

	@Autowired AreaRepository repository;
	
	public void saveArea(Area area) {
		repository.save(area);
	}

	public void delete(Area a) {
		repository.delete(a);
	}

}

package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Area;
import com.uniovi.repositories.AreaRepository;

@Service
public class AreaService {

	@Autowired AreaRepository repository;
	
	/**
	 * Método que guarda el área en la bd
	 * @param area Area
	 */
	public void saveArea(Area area) {
		repository.save(area);
	}

	/**
	 * Método que borra el área de la bd
	 * @param a Area
	 */
	public void delete(Area a) {
		repository.delete(a);
	}

}

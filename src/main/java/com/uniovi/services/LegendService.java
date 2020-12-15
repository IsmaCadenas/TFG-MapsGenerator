package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Legend;
import com.uniovi.repositories.LegendRepository;

@Service
public class LegendService {

	@Autowired LegendRepository repository;
	
	/**
	 * Método que guarda la leyenda en la bd
	 * @param legend Legend
	 */
	public void saveLegend(Legend legend) {
		repository.save(legend);
	}

	/**
	 * Método que elimina la leyenda de la bd
	 * @param a Legend
	 */
	public void delete(Legend a) {
		repository.delete(a);
	}
}

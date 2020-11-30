package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Legend;
import com.uniovi.repositories.LegendRepository;

@Service
public class LegendService {

	@Autowired LegendRepository repository;
	
	public void saveLegend(Legend legend) {
		repository.save(legend);
	}

	public void delete(Legend a) {
		repository.delete(a);
	}
}

package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.MasterMap;
import com.uniovi.repositories.MasterMapRepository;

@Service
public class MasterMapService {

	@Autowired MasterMapRepository repository;
	
	public MasterMap getMapByUsername(String name) {
		return repository.findByNamecode(name);
	}

	public Iterable<MasterMap> getAllMasterMaps() {
		return repository.findAll();
	}

}

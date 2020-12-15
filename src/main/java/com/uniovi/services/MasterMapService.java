package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.MasterMap;
import com.uniovi.repositories.MasterMapRepository;

@Service
public class MasterMapService {

	@Autowired MasterMapRepository repository;
	
	/**
	 * Método que devuelve un mapa maestro por su nombre
	 * @param name
	 * @return MasterMap
	 */
	public MasterMap getMapByUsername(String name) {
		return repository.findByNamecode(name);
	}

	/**
	 * Método que devuelve todos los mapas maestros
	 * @return Iterable<MasterMap>
	 */
	public Iterable<MasterMap> getAllMasterMaps() {
		return repository.findAll();
	}

}

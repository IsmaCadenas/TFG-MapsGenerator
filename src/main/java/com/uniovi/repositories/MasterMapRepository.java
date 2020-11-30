package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.MasterMap;

public interface MasterMapRepository extends CrudRepository<MasterMap, Long>{

	MasterMap findByNamecode(String name);

}

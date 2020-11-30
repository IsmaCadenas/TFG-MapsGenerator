package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Map;
import com.uniovi.entities.User;

public interface MapRepository extends CrudRepository<Map, Long> {

	Iterable<Map> findByUser(User loggedUser);

}

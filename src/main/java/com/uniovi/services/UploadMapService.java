package com.uniovi.services;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Area;
import com.uniovi.entities.Legend;
import com.uniovi.entities.Map;
import com.uniovi.entities.MasterMap;
import com.uniovi.entities.UploadMap;
import com.uniovi.repositories.UploadMapRepository;
import com.uniovi.util.GoogleDrive;

@Service
public class UploadMapService {

	@Autowired UsersServiceImpl userService;
	@Autowired UploadMapRepository repository;
	GoogleDrive drive = new GoogleDrive();
	
	private String username;
	
	/**
	 * Método que guarda un mapa propuesto
	 * @param map
	 */
	public void saveUploadMap(UploadMap map) {
		// Guardar los archivos en la carpeta userMaps
		try {
			username = userService.getLoggedUser().getUsername();
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.setNameUser(username);
		saveFile(map);
		repository.save(map);
	}
	
	/**
	 * Método que transforma la cadena de texto recibida con las áreas
	 * @param areas
	 * @return String
	 */
	private String transformAreas(String areas) {
		if(!areas.isEmpty()) {
			areas = areas.substring(1, areas.length()-1);
			areas = areas.replace(":{\"dato\"", "");
			areas = areas.replace("\"}", "\"");
			areas = areas.replace(",", ",\n");
		}
		return areas; 
	}
	
	/**
	 * Método que guarda los archivos del mapa propuesto
	 * @param map
	 */
	private void saveFile(UploadMap map) {
		Writer fileWriter;
		try {
			
			File directorio = new File("./src/main/resources/static/jQuery-Mapael-2.2.0/js/userMaps/"+username);
			directorio.mkdirs();
			fileWriter = new FileWriter("./src/main/resources/static/jQuery-Mapael-2.2.0/js/userMaps/"+username+"/"+map.getName()+".js");
			fileWriter.write(map.getInputJS());
			fileWriter.close();
			drive.saveFile(username, map.getName(), "js");
			fileWriter = new FileWriter("./src/main/resources/static/jQuery-Mapael-2.2.0/js/userMaps/"+username+"/"+map.getName()+".txt");
			fileWriter.write(transformAreas(map.getInputAreas()));
			fileWriter.close();
			drive.saveFile(username, map.getName(), "txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que devuelve todos los mapas propuestos
	 * @return Iterable<UploadMap>
	 */
	public Iterable<UploadMap> getAllUploadedMaps() {
		return repository.findAll();
	}

	/**
	 * Método que borra un mapa propuesto
	 * @param id
	 */
	public void deleteUploadMap(Long id) {
		repository.deleteById(id);
	}
	
}

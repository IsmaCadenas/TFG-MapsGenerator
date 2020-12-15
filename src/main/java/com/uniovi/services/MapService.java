package com.uniovi.services;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Area;
import com.uniovi.entities.Legend;
import com.uniovi.entities.Map;
import com.uniovi.entities.MasterArea;
import com.uniovi.entities.MasterMap;
import com.uniovi.entities.UploadMap;
import com.uniovi.entities.User;
import com.uniovi.repositories.MapRepository;

@Service
public class MapService {

	@Autowired MapRepository repository;
	@Autowired AreaService areaService;
	@Autowired LegendService legendService;
	@Autowired UsersServiceImpl userService;
	@Autowired MasterMapService masterMapService;
	@Autowired UploadMapService uploadMapService;
	
	/**
	 * Método que guarda el mapa en la bd
	 * @param map Map
	 */
	public void saveMap(Map map) {
		createAreas(map);
		createLegends(map);
		linkUser(map);
		repository.save(map);
	}

	/**
	 * Método que asocia el usuario que crea el mapa al mapa
	 * @param map Map
	 */
	private void linkUser(Map map) {
		try {
			map.setUser(userService.getLoggedUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que crea las áreas editadas del mapa
	 * @param map Map
	 */
	private void createAreas(Map map) {
		Set<Area> areas = new HashSet<>();		
		String[] list = map.getArea1fill().split(",");
		if(!map.getArea1fill().isEmpty()) {
			for(int i = 0; i < list.length; i++) {
				String area1 = list[i];
				String[] listArea = area1.split(":");
				String id = listArea[0].replace("{", "");
				String fill = listArea[3].replace("}", "");
				Area area = new Area(id, fill);
				areaService.saveArea(area);
				areas.add(area);
			}
		}
		map.setAreas(areas);	
	}
	
	/**
	 * Método que crea las leyendas del mapa
	 * @param map Map
	 */
	private void createLegends(Map map) {
		Set<Legend> legends = new HashSet<>();		
		String[] list = map.getLegendInput().split("attrs");
		if(!map.getLegendInput().isEmpty()) {
			for(int i = 1; i < list.length; i++) {
				String legend1 = list[i];
				String[] listLegend = legend1.split(":");
				String[] color = listLegend[2].split(",");
				String[] label = listLegend[4].split(",");
				String colorLegend = color[0];
				String labelLegend = label[0].replace("}]", "").replace("}", "");
				Legend legend = new Legend(colorLegend, labelLegend);
				legendService.saveLegend(legend);
				legends.add(legend);
			}
		}
		map.setLegend(legends);
	}
	
	/**
	 * Método que obtiene el mapa por su id
	 * @param id 
	 * @return Map
	 * @throws Exception
	 */
	public Map getMapById(Long id) throws Exception {
		return repository.findById(id).orElseThrow( () -> new Exception("Mapa no encontrado"));
	}

	/**
	 * Método que obtiene los mapas del usuario en sesión
	 * @return Iterable<Map>
	 */
	public Iterable<Map> getLoggedUserMaps() {
		try {
			return repository.findByUser(userService.getLoggedUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * Método que devuelves las áreas guardadas de un mapa editado
	 * @param id Long del mapa
	 * @return String cadena de texto con las áreas
	 * @throws Exception
	 */
	public String getStringAreas(Long id) throws Exception {
		Map m = getMapById(id);
		String cadena = "";	
		for(Area a : m.getAreas()) {
			cadena= cadena + a.getIdentifier();
			cadena= cadena + ": { attrs : { fill :";
			cadena = cadena + a.getFill();
			cadena= cadena +"} }, ";			
		}
		if(!cadena.isEmpty()) {
			String area1 = "{" + cadena;
			area1 = area1.substring(0, area1.length()-2);
			area1 = area1 + "}";
			m.setArea1fill(area1);
		}
		
		
		return cadena;
	}

	/**
	 * Elimina un mapa 
	 * @param id Long 
	 * @throws Exception
	 */
	public void deleteMap(Long id) throws Exception {
		Map map = getMapById(id);
		Set<Area> areas = map.getAreas();
		Set<Legend> leyendas = map.getLegend();
		//Si comento la linea de abajo entonces no borra las areas (??? ni idea...)
		map.getAreas().size();
		map.getLegend().size();
		repository.delete(map);
		for(Area a : areas) {
			areaService.delete(a);
		}
		for(Legend a : leyendas) {
			legendService.delete(a);
		}
	}

	/**
	 * Método que borra los mapas del usuario
	 * @param user User
	 */
	public void deleteMaps(User user) {
		try {
			Iterable<Map> maps = repository.findByUser(user);
			for(Map m : maps) {
				deleteMap(m.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que obtiene los nombres de las áreas de un mapa
	 * @param name String
	 * @return String una cadena de texto con los nombres de las áreas
	 */
	public String getStringTooltips(String name) {
		MasterMap m = masterMapService.getMapByUsername(name);
		String cadena = "";
		for(MasterArea a : m.getAreas()) {
			cadena= cadena + a.getCode();
			cadena= cadena + ": { tooltip : { content :";
			cadena = cadena + a.getTooltip();
			cadena= cadena +"} }, ";			
		}
		return cadena;
	}

	/**
	 * Método que devuelve las leyendas de un mapa editado
	 * @param id Long
	 * @return String una cadena de texto con los datos de las leyendas
	 * @throws Exception
	 */
	public String getStringLegends(Long id) throws Exception {
		Map m = getMapById(id);
		String cadena = "[";
		for(Legend a : m.getLegend()) {
			cadena= cadena + "{ attrs: { fill: " + a.getFill() + ", stroke: " + a.getFill() + " }, label: " + a.getLabel() ;
			cadena= cadena + "},";		
		}
		
		
		if(!m.getLegend().isEmpty()) {
			cadena = cadena.substring(0, cadena.length()-2);
		}
		
		cadena= cadena + "]";
		m.setLegendInput(cadena);
		System.out.println(cadena);
		return cadena;	
	}

	/**
	 * Método que guarda los datos de un mapa editado
	 * @param map
	 * @param edited
	 * @param editedLegend
	 * @throws Exception
	 */
	public void editMap(Map map, String edited,String editedLegend) throws Exception {
		Map toMap = getMapById(map.getId());		
		createAreas(map);
		createLegends(map);
		mapMap(map, toMap, edited,editedLegend);
		repository.save(toMap);
	}
	
	/**
	 * Método que cambia los datos del anterior mapa por los del nuevo mapa
	 * @param from
	 * @param to
	 * @param edited
	 * @param editedLegend
	 */
	protected void mapMap(Map from,Map to,String edited,String editedLegend) {
		to.setBackground(from.getBackground());
		to.setBorderColor(from.getBorderColor());
		to.setLegendLabelColor(from.getLegendLabelColor());
		to.setLegendLabelSize(from.getLegendLabelSize());
		to.setLegendLabelFont(from.getLegendLabelFont());
		to.setLegendTitleColor(from.getLegendTitleColor());
		to.setLegendTitleSize(from.getLegendTitleSize());
		to.setLegendTitleFont(from.getLegendTitleFont());
		to.setLegendInput(from.getLegendInput());	
		to.setPredefinedColor(from.getPredefinedColor());
		to.setBorderWidth(from.getBorderWidth());
		to.setBackgroundLegendColor(from.getBackgroundLegendColor());
		to.setBorderLegendColor(from.getBorderLegendColor());
		to.setBorderLegendWidth(from.getBorderLegendWidth());
		to.setBorderLegendRadio(from.getBorderLegendRadio());
		to.setName(from.getName());
		to.setLegendTitle(from.getLegendTitle());
		if(edited.equals("true")) {
			to.setAreas(from.getAreas());
			
		}
		if(editedLegend.equals("true")) {
			to.setLegend(from.getLegend());
		}
	}

	/**
	 * Método que devuelve el nombre del mapa
	 * @param name
	 * @return String el nombre del mapa
	 * @throws Exception
	 */
	public String getNameMap(String name) throws Exception {
		try {
			MasterMap m = masterMapService.getMapByUsername(name);
			return m.getNameMap();
		}catch (Exception e) {
			throw new Exception("Username o email ya utilizado");
		}
		
	}

	/**
	 * Método que devuelve los mapas maestros
	 * @return Iterable<MasterMap>
	 */
	public Iterable<MasterMap> getAllMasterMaps() {
		return masterMapService.getAllMasterMaps();
	}

	/**
	 * Método que devuelve los últimos mapas editados por el usuario
	 * @return Iterator<Map>
	 */
	public Iterator<Map> getLastMaps() {
		List<Map> maps = new ArrayList<Map>();
		List<Map> lastMaps = new ArrayList<Map>();
		for(Map m : getLoggedUserMaps()) {
			maps.add(m);
		}
		if(maps.size()>0) {
			lastMaps.add(maps.get(maps.size()-1));
		}
		if(maps.size()>1) {
			lastMaps.add(maps.get(maps.size()-2));
		}
		if(maps.size()>2) {
			lastMaps.add(maps.get(maps.size()-3));
		}
		if(maps.size()>3) {
			lastMaps.add(maps.get(maps.size()-4));
		}
		if(maps.size()>4) {
			lastMaps.add(maps.get(maps.size()-5));
		}
		return lastMaps.iterator();
	}

	/**
	 * Método que devuelve el ancho del mapa
	 * @param name
	 * @return String el ancho del mapa en %
	 */
	public String getWidth(String name) {
		MasterMap m = masterMapService.getMapByUsername(name);
		return m.getWidth();
	}

	/**
	 * Método que guarda un mapa propuesto
	 * @param map
	 */
	public void saveUploadMap(UploadMap map) {
		uploadMapService.saveUploadMap(map);
	}

	/**
	 * Método que obtiene todos los mapas propuestos
	 * @return UploadedMap 
	 */
	public Object getAllUploadedMaps() {
		return uploadMapService.getAllUploadedMaps();
	}

	/**
	 * Método que borra un mapa propuesto
	 * @param id
	 */
	public void deleteUploadMap(Long id) {
		uploadMapService.deleteUploadMap(id);
	}

	
}

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
	
	public void saveMap(Map map) {
		createAreas(map);
		createLegends(map);
		linkUser(map);
		repository.save(map);
	}

	private void linkUser(Map map) {
		try {
			map.setUser(userService.getLoggedUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
	
	
	public Map getMapById(Long id) throws Exception {
		return repository.findById(id).orElseThrow( () -> new Exception("Mapa no encontrado"));
	}

	public Iterable<Map> getLoggedUserMaps() {
		try {
			return repository.findByUser(userService.getLoggedUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
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

	public void editMap(Map map, String edited,String editedLegend) throws Exception {
		Map toMap = getMapById(map.getId());		
		createAreas(map);
		createLegends(map);
		mapMap(map, toMap, edited,editedLegend);
		repository.save(toMap);
	}
	
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

	public String getNameMap(String name) throws Exception {
		try {
			MasterMap m = masterMapService.getMapByUsername(name);
			return m.getNameMap();
		}catch (Exception e) {
			throw new Exception("Username o email ya utilizado");
		}
		
	}

	public Iterable<MasterMap> getAllMasterMaps() {
		return masterMapService.getAllMasterMaps();
	}

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

	public String getWidth(String name) {
		MasterMap m = masterMapService.getMapByUsername(name);
		return m.getWidth();
	}

	public void saveUploadMap(UploadMap map) {
		uploadMapService.saveUploadMap(map);
	}

	public Object getAllUploadedMaps() {
		return uploadMapService.getAllUploadedMaps();
	}

	public void deleteUploadMap(Long id) {
		uploadMapService.deleteUploadMap(id);
	}

	
}

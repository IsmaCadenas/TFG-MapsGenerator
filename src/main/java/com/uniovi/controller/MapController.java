package com.uniovi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.uniovi.entities.Map;
import com.uniovi.entities.UploadMap;
import com.uniovi.services.MapService;

@Controller
public class MapController {

	@Autowired MapService mapService;
	
	@GetMapping("/map/{name}")
	public String getMap(Model model, @PathVariable String name) {
		
		try {
			model.addAttribute("edit", false);
			Map map = new Map();
			
			model.addAttribute("map",name);
			model.addAttribute("mapForm", map);
			model.addAttribute("drawAreas", false);
			model.addAttribute("drawLegend", false);
			model.addAttribute("nameMap", mapService.getNameMap(name));
			model.addAttribute("width",mapService.getWidth(name));
			model.addAttribute("tooltipsText", mapService.getStringTooltips(name));
			String nameScript = "./../jQuery-Mapael-2.2.0/js/maps/" + name + ".js";
			model.addAttribute("nameScript", nameScript);
			return "map/map";
		} catch (Exception e) {
			model.addAttribute("tooltipsText", mapService.getStringTooltips("world_countries_mercator"));
			model.addAttribute("masterMapList",mapService.getAllMasterMaps());
			return "map/mapsNotLoaded";
		}
		
	}
	
	@PostMapping("/saveMap")
	public String saveMap(@Valid @ModelAttribute("mapForm")Map map, ModelMap model) {
		mapService.saveMap(map);
		return getMyMaps(model);
	}
	
	@PostMapping("/editMap")
	public String editMap(@Valid @ModelAttribute("mapForm")Map map, ModelMap model) {
		try {
			mapService.editMap(map, map.getEdited(), map.getEditedLegend());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/myMaps";
	}
	 
	@GetMapping("/myMaps")
	public String getMyMaps(ModelMap model) {
		model.addAttribute("mapList", mapService.getLoggedUserMaps());
		return "map/myMaps";
	}
	
	@GetMapping("/editMap/{id}")
	public String editMap(ModelMap model, @PathVariable Long id) {
		Map map;
		try {
			map = mapService.getMapById(id);
			if(map == null) {
				return "error/403.html";
			}
			model.addAttribute("edit", true);
			model.addAttribute("drawAreas", true);
			model.addAttribute("drawLegend", true);
			
			String areas = mapService.getStringAreas(id);
			String legends = mapService.getStringLegends(id);
			System.out.println("areas: "+areas);
			System.out.println("legends: "+legends);
			model.addAttribute("areas", areas);
			model.addAttribute("legends", legends);
			model.addAttribute("map", map.getNamecode());
			model.addAttribute("mapForm",map);
			
			
			String width = mapService.getWidth(map.getNamecode());
			String tooltips = mapService.getStringTooltips(map.getNamecode());
			String nameMap = mapService.getNameMap(map.getNamecode());
			model.addAttribute("width",width);
			model.addAttribute("tooltipsText", tooltips);
			model.addAttribute("nameMap", nameMap);
			model.addAttribute("nameScript", map.getNamecode());
			
			String nameScript = "./../jQuery-Mapael-2.2.0/js/maps/" + map.getNamecode() + ".js";
			model.addAttribute("nameScript", nameScript);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/403.html";
		}
		return "map/map";
	}
	
	@GetMapping("/deleteMap/{id}")
	public String deleteMap(Model model, @PathVariable(name="id")Long id) {
		try {
			mapService.deleteMap(id);
		} 
		catch (Exception e) {
		}
		return "redirect:/myMaps";
	}
	
	@GetMapping("/maps")
	public String getMaps(Model model) {
		model.addAttribute("tooltipsText", mapService.getStringTooltips("world_countries_mercator"));
		model.addAttribute("masterMapList",mapService.getAllMasterMaps());
		return "map/maps";
	}
	
	@GetMapping("/uploadMap")
	public String uploadMap(Model model) {
		UploadMap map = new UploadMap();
		model.addAttribute("mapDTO", map);
		model.addAttribute("success",false);
		return "map/uploadMap";
	}
	
	
	@PostMapping("/uploadMap")
	public String saveUploadMap(Model model, @Valid @ModelAttribute("mapDTO")UploadMap map) {
		mapService.saveUploadMap(map);
		model.addAttribute("success",true);
		return "map/uploadMap";
	}
	
	@GetMapping("/uploadedMaps")
	public String uploadedMaps(Model model) {
		model.addAttribute("uploadedMapsList",mapService.getAllUploadedMaps());
		return "map/uploadedMaps";
	}
	
	@GetMapping("/deleteUploadMap/{id}")
	public String deleteUploadMap(Model model, @PathVariable(name="id")Long id) {
		try {
			mapService.deleteUploadMap(id);
		} 
		catch (Exception e) {
			model.addAttribute("listErrorMessage",e.getMessage());
		}
		return "redirect:/uploadedMaps";
	}
}

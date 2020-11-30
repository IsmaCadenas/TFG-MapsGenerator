package com.uniovi.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uniovi.services.MapService;

@Controller
public class HomeController {
	
	@Autowired MapService mapService;
	
	@GetMapping({"/"})
	public String index(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getName().equals("anonymousUser")) {
			model.addAttribute("mapList", mapService.getLastMaps());
		}
		
		return "home/index";
	}
	
	@GetMapping({"/help"})
	public String help() {
		return "home/help";
	}
}

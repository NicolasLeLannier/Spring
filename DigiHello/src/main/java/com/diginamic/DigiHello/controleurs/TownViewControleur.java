/**
 *
 */
package com.diginamic.DigiHello.controleurs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.diginamic.DigiHello.service.DepartementService;
import com.diginamic.DigiHello.service.VilleService;

/** 
 * @author Nicolas LE LANNIER
 */
@Controller
public class TownViewControleur {
	
	@Autowired
	private VilleService villeService;
	@Autowired
	private DepartementService departementService;

	@GetMapping("/towns")
	public ModelAndView getTowns() {
		Map<String, Object> model = new HashMap<>();
		model.put("towns", villeService.extractVilles());
		model.put("departements", departementService.getAllDepartements());
		return new ModelAndView("town/towns", model);
	}
	
	@GetMapping
	public String getIndex(Model model, Authentication authentication) {
		model.addAttribute("authentication", authentication);
		return "index";
	}
}

package it.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/home")
public class HomeController {
	
	
	
	@GetMapping(path = "/index")
	public String getHome() {
		return "index"; //il viewResolver aggiunge il suffisso specificato nell'application properties(.jsp)

}
	
}

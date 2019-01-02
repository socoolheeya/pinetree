package co.io.pinetree.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import co.io.pinetree.util.model.CommandMap;

@Controller
public class MainController {
	
	private static Logger log = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value= {"/", "/main.do"})
	public String main(Model model, CommandMap cmMap) throws Exception {
		log.debug("===================================================================================");
		log.debug("[METHDO] : " + Thread.currentThread().getStackTrace()[1].getMethodName().toString() + "()");
		log.debug("[PARAM] : " + cmMap.getMap());
		log.debug("===================================================================================");
		
		return "main/main";
	}
}

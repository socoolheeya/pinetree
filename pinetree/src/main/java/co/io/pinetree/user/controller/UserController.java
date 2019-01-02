package co.io.pinetree.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.io.pinetree.user.service.UserService;
import co.io.pinetree.util.model.CommandMap;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/userList.do")
	public String userList(Model model, CommandMap cmMap) throws Exception {
		log.debug("===================================================================================");
		log.debug("[METHDO] : " + Thread.currentThread().getStackTrace()[1].getMethodName().toString() + "()");
		log.debug("[PARAM] : " + cmMap.getMap());
		log.debug("===================================================================================");
		
		return "user/userList";
	}
	
	@RequestMapping(value="/userList.json", method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> userListJson(CommandMap cmMap) throws Exception {
		log.debug("===================================================================================");
		log.debug("[METHDO] : " + Thread.currentThread().getStackTrace()[1].getMethodName().toString() + "()");
		log.debug("[PARAM] : " + cmMap.getMap());
		log.debug("===================================================================================");
		
		return userService.list(cmMap.getMap());
	}
	
	@RequestMapping(value="/userInsert.do")
	public String userInsert(CommandMap cmMap) throws Exception {
		log.debug("===================================================================================");
		log.debug("[METHDO] : " + Thread.currentThread().getStackTrace()[1].getMethodName().toString() + "()");
		log.debug("[PARAM] : " + cmMap.getMap());
		log.debug("===================================================================================");
		
		return "user/userInsert";
	}
	
	@RequestMapping(value="userInsert.json", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userInsertJson(CommandMap cmMap) throws Exception {
		log.debug("===================================================================================");
		log.debug("[METHDO] : " + Thread.currentThread().getStackTrace()[1].getMethodName().toString() + "()");
		log.debug("[PARAM] : " + cmMap.getMap());
		log.debug("===================================================================================");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int result = userService.insert(cmMap.getMap());
		
		if(result > 0) {
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value="/userUpdate.do")
	public String userUpdate(Model model, CommandMap cmMap) throws Exception {
		log.debug("===================================================================================");
		log.debug("[METHDO] : " + Thread.currentThread().getStackTrace()[1].getMethodName().toString() + "()");
		log.debug("[PARAM] : " + cmMap.getMap());
		log.debug("===================================================================================");
		
		Map<String, Object> userView = userService.view(cmMap.getMap());
		model.addAttribute("userView", userView);
		
		return "user/userUpdate";
	}
	
	@RequestMapping(value="userUpdate.json", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userUpdateJson(CommandMap cmMap) throws Exception {
		log.debug("===================================================================================");
		log.debug("[METHDO] : " + Thread.currentThread().getStackTrace()[1].getMethodName().toString() + "()");
		log.debug("[PARAM] : " + cmMap.getMap());
		log.debug("===================================================================================");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int result = userService.update(cmMap.getMap());
		
		if(result > 0) {
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value="userDelete.json", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userDeleteJson(CommandMap cmMap) throws Exception {
		log.debug("===================================================================================");
		log.debug("[METHDO] : " + Thread.currentThread().getStackTrace()[1].getMethodName().toString() + "()");
		log.debug("[PARAM] : " + cmMap.getMap());
		log.debug("===================================================================================");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int result = userService.delete(cmMap.getMap());
		
		if(result > 0) {
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
}

package co.io.pinetree.main.controller;

import java.security.PrivateKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.io.pinetree.user.service.UserService;
import co.io.pinetree.util.model.CommandMap;
import co.io.pinetree.util.model.RsaUtil;

@Controller
public class LoginController {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/login.do")
	public String login(Model model, HttpServletRequest request, CommandMap cmMap) throws Exception {
		log.debug("===================================================================================");
		log.debug("[METHDO] : " + Thread.currentThread().getStackTrace()[1].getMethodName().toString() + "()");
		log.debug("[PARAM] : " + cmMap.getMap());
		log.debug("===================================================================================");

		RsaUtil rsa = new RsaUtil();
		rsa.init(request);

		return "main/login";
	}

	@PostMapping("/login.do")
	public String loginProc(Model model, HttpSession session, CommandMap cmMap) throws Exception {
		log.debug("===================================================================================");
		log.debug("[METHDO] : " + Thread.currentThread().getStackTrace()[1].getMethodName().toString() + "()");
		log.debug("[PARAM] : " + cmMap.getMap());
		log.debug("===================================================================================");

		String userId = (String) cmMap.get("userId");
		String password = (String) cmMap.get("password");

		PrivateKey privateKey = (PrivateKey) session.getAttribute(RsaUtil.RSA_WEB_KEY);

		RsaUtil rsa = new RsaUtil();
		userId = rsa.decryptRsa(privateKey, userId);
		password = rsa.decryptRsa(privateKey, password);
		
		cmMap.put("userId", userId);
		cmMap.put("password", password);
		
		// 개인키 삭제
		session.removeAttribute(RsaUtil.RSA_WEB_KEY);
		model.addAttribute("result", userService.login(cmMap.getMap(), session));
		
		return "main/main";
	}

	@RequestMapping("/logout.do")
	public void logout(HttpSession session, HttpServletResponse response) throws Exception {
		log.debug("===================================================================================");
		log.debug("[METHDO] : " + Thread.currentThread().getStackTrace()[1].getMethodName().toString() + "()");
		log.debug("===================================================================================");

		session.invalidate();
		response.sendRedirect("/login.do");
	}

	@RequestMapping("/join.json")
	public void join(Model model, CommandMap cmMap) throws Exception {
		log.debug("===================================================================================");
		log.debug("[METHDO] : " + Thread.currentThread().getStackTrace()[1].getMethodName().toString() + "()");
		log.debug("[PARAM] : " + cmMap.getMap());
		log.debug("===================================================================================");

		userService.join(cmMap.getMap());
	}

}

package common.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {
	
	@RequestMapping(value="home",method=RequestMethod.GET)
	public String AdminHomeGet(ModelMap map, HttpSession session)
	{
		return "home";
	}
}
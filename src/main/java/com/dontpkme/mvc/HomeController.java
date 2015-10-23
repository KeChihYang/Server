package com.dontpkme.mvc;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date(0);
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
//	
//	@RequestMapping(value = "/handleAJAXRequest", method=RequestMethod.POST)
//	@ResponseBody
//	public Map<String, String> handleAJAXRequest(@RequestParam String name, @RequestParam String age){
//		String result = "false";
//		Map<String, String> map = new HashMap<String, String>();
//		
//		if(name != null && age != null){
//			result = "true";
//			map.put("name", name);
//			map.put("age", age);
//		}
//		map.put("result", result);
//		return map;
//	
//	}
	
	@Autowired  
	 UserService userService;  
	  
	 @RequestMapping("/register")  
	 public ModelAndView registerUser(@ModelAttribute User user) {  
	  
	  List<String> genderList = new ArrayList<String>();  
	  genderList.add("male");  
	  genderList.add("female");  
	  
	  List<String> cityList = new ArrayList<String>();  
	  cityList.add("delhi");  
	  cityList.add("gurgaon");  
	  cityList.add("meerut");  
	  cityList.add("noida");  
	  
	  Map<String, List> map = new HashMap<String, List>();  
	  map.put("genderList", genderList);  
	  map.put("cityList", cityList);  
	  return new ModelAndView("register", "map", map);  
	 }  
	  
	 @RequestMapping("/insert")  
	 public String inserData(@ModelAttribute User user) {  
	  if (user != null)  
	   userService.insertData(user);  
	  return "redirect:/getList";  
	 }  
	  
	 @RequestMapping("/getList")  
	 public ModelAndView getUserLIst() {  
	  List<User> userList = userService.getUserList();  
	  return new ModelAndView("userList", "userList", userList);  
	 }  
	  
	 @RequestMapping("/edit")  
	 public ModelAndView editUser(@RequestParam String id,  
	   @ModelAttribute User user) {  
	  
	  user = userService.getUser(id);  
	  
	  List<String> genderList = new ArrayList<String>();  
	  genderList.add("male");  
	  genderList.add("female");  
	  
	  List<String> cityList = new ArrayList<String>();  
	  cityList.add("delhi");  
	  cityList.add("gurgaon");  
	  cityList.add("meerut");  
	  cityList.add("noida");  
	  
	  Map<String, Object> map = new HashMap<String, Object>();  
	  map.put("genderList", genderList);  
	  map.put("cityList", cityList);  
	  map.put("user", user);  
	  
	  return new ModelAndView("edit", "map", map);  
	  
	 }  
	  
	 @RequestMapping("/update")  
	 public String updateUser(@ModelAttribute User user) {  
	  userService.updateData(user);  
	  return "redirect:/getList";  
	  
	 }  
	  
	 @RequestMapping("/delete")  
	 public String deleteUser(@RequestParam String id) {  
	  System.out.println("id = " + id);  
	  userService.deleteData(id);  
	  return "redirect:/getList";  
	 }  
	
	
	
}

package org.o7planning.sbsecurity.controller;
 
import java.security.Principal;

import org.o7planning.sbsecurity.dao.AppUserDAO;
import org.o7planning.sbsecurity.entity.AppUser;
import org.o7planning.sbsecurity.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class MainController {
 
	@Autowired
    private AppUserDAO appUserDAO;
	
	private AppUser appUser;
	
    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }
 
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
         
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
         
        return "adminPage";
    }
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
 
        return "loginPage";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
    	AppUser newAppUser = new AppUser();
        model.addAttribute("appUser", newAppUser);
     	return "register";
    }
    
    @RequestMapping(value = "/register/submit", method = RequestMethod.POST)
    public String registerSubmit(Model model, @ModelAttribute("appUser") AppUser newAppUser) {
    	if(appUserDAO.findUserAccount(newAppUser.getUserName()) == null) {
    		appUser = newAppUser;
    		appUser.setYear(1);
        	appUser.setIntelligence(0);
        	appUser.setMoney(0);
        	appUser.setHappiness(100);
        	appUser.setWorkLevel(1);
        	this.appUserDAO.create(this.appUser);
    		return "registered";
    	} else
    		return "login_taken";
    }
 
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
 
    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String game(Model model, Principal principal) {
 
        // After user login successfully.
        String userName = principal.getName();
        this.appUser = this.appUserDAO.findUserAccount(userName);
 
        System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("year", appUser.getYear());
        model.addAttribute("money", appUser.getMoney());
        model.addAttribute("intelligence", appUser.getIntelligence());
        model.addAttribute("happiness", appUser.getHappiness());
        model.addAttribute("work_level", appUser.getWorkLevel());
        
        if(appUser.getYear() < 100)
	        return "game";
        else
        	return "end";
    }
 
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = WebUtils.toString(loginedUser);
 
            model.addAttribute("userInfo", userInfo);
 
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
 
        }
 
        return "403Page";
    }
    
    @RequestMapping(value = "/work", method = RequestMethod.GET)
    public String work(Model model, Principal principal) {
    	appUser.setMoney((int)(appUser.getMoney() + appUser.getWorkLevel()*(float)appUser.getHappiness()/100*10));
    	appUser.setHappiness(appUser.getHappiness()-2);
    	appUser.setYear(appUser.getYear()+1);
    	if(appUser.getYear() < 100)
    		this.appUserDAO.save(this.appUser);
    	
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("year", appUser.getYear());
        model.addAttribute("money", appUser.getMoney());
        model.addAttribute("intelligence", appUser.getIntelligence());
        model.addAttribute("happiness", appUser.getHappiness());
        model.addAttribute("work_level", appUser.getWorkLevel());

        if(appUser.getYear() < 100)
	        return "game";
        else
        	return "end";
    }
    
    @RequestMapping(value = "/study", method = RequestMethod.GET)
    public String study(Model model, Principal principal) {
    	appUser.setIntelligence((int)(appUser.getIntelligence()+(float)5*appUser.getHappiness()/100));
    	appUser.setHappiness(appUser.getHappiness()-2);
    	appUser.setYear(appUser.getYear()+1);
    	if(appUser.getYear() < 100)
    		this.appUserDAO.save(this.appUser);
    	
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("year", appUser.getYear());
        model.addAttribute("money", appUser.getMoney());
        model.addAttribute("intelligence", appUser.getIntelligence());
        model.addAttribute("happiness", appUser.getHappiness());
        model.addAttribute("work_level", appUser.getWorkLevel());

        if(appUser.getYear() < 100)
	        return "game";
        else
        	return "end";
    }
    
    @RequestMapping(value = "/fun", method = RequestMethod.GET)
    public String fun(Model model, Principal principal) {
    	appUser.setHappiness(appUser.getHappiness()+3);
    	if(appUser.getHappiness() > 100)
    		appUser.setHappiness(100);
    	appUser.setYear(appUser.getYear()+1);
    	if(appUser.getYear() < 100)
    		this.appUserDAO.save(this.appUser);
    	
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("year", appUser.getYear());
        model.addAttribute("money", appUser.getMoney());
        model.addAttribute("intelligence", appUser.getIntelligence());
        model.addAttribute("happiness", appUser.getHappiness());
        model.addAttribute("work_level", appUser.getWorkLevel());

        if(appUser.getYear() < 100)
	        return "game";
        else
        	return "end";
    }
    
    @RequestMapping(value = "/promotion", method = RequestMethod.GET)
    public String promotion(Model model, Principal principal) {
    	int required_intelligence = 5+(int)Math.pow(2, appUser.getWorkLevel());
    	if(appUser.getIntelligence() >= required_intelligence) {
    		appUser.setWorkLevel(appUser.getWorkLevel()+1);
    		if(appUser.getYear() < 100)
        		this.appUserDAO.save(this.appUser);
	        User loginedUser = (User) ((Authentication) principal).getPrincipal();
	        String userInfo = WebUtils.toString(loginedUser);
	        model.addAttribute("userInfo", userInfo);
	        model.addAttribute("year", appUser.getYear());
	        model.addAttribute("money", appUser.getMoney());
	        model.addAttribute("intelligence", appUser.getIntelligence());
	        model.addAttribute("happiness", appUser.getHappiness());
	        model.addAttribute("work_level", appUser.getWorkLevel());
	
	        if(appUser.getYear() < 100)
		        return "game";
	        else
	        	return "end";
    	} else {
    		User loginedUser = (User) ((Authentication) principal).getPrincipal();
	        String userInfo = WebUtils.toString(loginedUser);
	        model.addAttribute("userInfo", userInfo);
	        model.addAttribute("year", appUser.getYear());
	        model.addAttribute("money", appUser.getMoney());
	        model.addAttribute("intelligence", appUser.getIntelligence());
	        model.addAttribute("happiness", appUser.getHappiness());
	        model.addAttribute("work_level", appUser.getWorkLevel());
	        model.addAttribute("required_intelligence", required_intelligence);
	        return "no_promotion";
    	}
    }
    
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String reset(Model model, Principal principal) {
    	appUser.setYear(1);
    	appUser.setIntelligence(0);
    	appUser.setMoney(0);
    	appUser.setHappiness(100);
    	appUser.setWorkLevel(1);
    	this.appUserDAO.save(this.appUser);
    	
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("year", appUser.getYear());
        model.addAttribute("money", appUser.getMoney());
        model.addAttribute("intelligence", appUser.getIntelligence());
        model.addAttribute("happiness", appUser.getHappiness());
        model.addAttribute("work_level", appUser.getWorkLevel());

        if(appUser.getYear() < 100)
	        return "game";
        else
        	return "end";
    }
}
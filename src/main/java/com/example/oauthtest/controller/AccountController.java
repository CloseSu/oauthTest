package com.example.oauthtest.controller;

import com.example.oauthtest.model.OauthTypeData;
import com.example.oauthtest.model.User;
import com.example.oauthtest.oauth.service.OAuthServiceDeractor;
import com.example.oauthtest.oauth.service.OAuthServices;
import com.example.oauthtest.service.UserServiceI;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private OAuthServices oAuthServices;
    @Autowired
    private UserServiceI userService;

    @RequestMapping(value = {"", "/login"}, method= RequestMethod.GET)
    public String showLogin(Model model){
	model.addAttribute("oAuthServices", oAuthServices.getAllOAuthServices());
	return "index";
    }

    @RequestMapping(value = "/oauth/{type}/callback", method=RequestMethod.GET)
    public String clallback(@RequestParam(value = "code", required = true) String code,
			    @PathVariable(value = "type") String type,
			    HttpServletRequest request){
	OAuthServiceDeractor oAuthService = oAuthServices.getOAuthService(type);
	Token accessToken = oAuthService.getAccessToken(null, new Verifier(code));
	OauthTypeData oauthTypeData = oAuthService.getOAuthUser(accessToken);
	userService.saveData(oauthTypeData);
	request.getSession().setAttribute("msg", "Log in with " + type);
	return "redirect:/success";
    }

    @RequestMapping(value = "/register", method=RequestMethod.GET)
    public String register(){
	return "register";
    }

    @RequestMapping(value = "/registerData", method=RequestMethod.POST)
    public String registerData(@ModelAttribute User user, HttpServletRequest request){
	String msg = userService.save(user);
	request.getSession().setAttribute("msg", msg);
	return "redirect:/success";
    }

    @RequestMapping(value = "/loginWithData", method=RequestMethod.POST)
    public Object loginWithData(@ModelAttribute User user, HttpServletRequest request){
        String msg = userService.logIn(user);
	request.getSession().setAttribute("msg", msg);
	return "redirect:/success";
    }

    @RequestMapping(value = "/findTypeList", method=RequestMethod.POST)
    public Object findTypeList(@ModelAttribute User user, HttpServletRequest request){
	List<OauthTypeData> typeList = userService.findTypeList(user);

	if (typeList != null && typeList.size() > 0) {
	    request.getSession().setAttribute("msg", typeList);
	} else {
	    request.getSession().setAttribute("msg", "No data in the website!");
	}
	return "redirect:/success";
    }

    @RequestMapping(value = "/success", method=RequestMethod.GET)
    @ResponseBody
    public Object success(HttpServletRequest request){
	return request.getSession().getAttribute("msg");
    }


}

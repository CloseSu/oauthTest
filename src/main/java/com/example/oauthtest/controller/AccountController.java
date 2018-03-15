package com.example.oauthtest.controller;

import com.example.oauthtest.model.OauthTypeData;
import com.example.oauthtest.model.User;
import com.example.oauthtest.oauth.service.OAuthServiceDeractor;
import com.example.oauthtest.oauth.service.OAuthServices;
import com.example.oauthtest.repository.OauthTypeDataRepository;
import com.example.oauthtest.repository.UserRepository;
import com.example.oauthtest.service.UserServiceI;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private OAuthServices oAuthServices;
    @Autowired
    private OauthTypeDataRepository oauthUserRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceI userService;

    //prepare all login data
    @RequestMapping(value = {"", "/login"}, method= RequestMethod.GET)
    public String showLogin(Model model){
	model.addAttribute("oAuthServices", oAuthServices.getAllOAuthServices());
	return "index";
    }

    //call back from authorization server
    @RequestMapping(value = "/oauth/{type}/callback", method=RequestMethod.GET)
    public String clallback(@RequestParam(value = "code", required = true) String code,
			    @PathVariable(value = "type") String type,
			    HttpServletRequest request, Model model){
	OAuthServiceDeractor oAuthService = oAuthServices.getOAuthService(type);
	Token accessToken = oAuthService.getAccessToken(null, new Verifier(code));
	OauthTypeData oauthTypeData = oAuthService.getOAuthUser(accessToken);
	userService.saveData(oauthTypeData);
	request.getSession().setAttribute("type", type);
	return "redirect:/success";
    }

//    @RequestMapping(value = "/register", method=RequestMethod.POST)
//    public String register(Model model, User user,
//			   @RequestParam(value = "oAuthType", required = false, defaultValue = "") String oAuthType,
//			   @RequestParam(value = "oAuthId", required = true, defaultValue = "") String oAuthId,
//			   HttpServletRequest request){
//	OAuthUser oAuthInfo = new OAuthUser();
//	oAuthInfo.setOAuthId(oAuthId);
//	oAuthInfo.setOAuthType(oAuthType);
//	if(userRepository.findByUsername(user.getUsername()) != null){
//	    model.addAttribute("errorMessage", "用户名已存在");
//	    model.addAttribute("oAuthInfo", oAuthInfo);
//	    return "register";
//	}
//	user = userRepository.save(user);
//	OAuthUser oAuthUser = oauthUserRepository.findByOAuthTypeAndOAuthId(oAuthType, oAuthId);
//	if(oAuthUser == null){
//	    oAuthInfo.setUser(user);
//	    oAuthUser = oauthUserRepository.save(oAuthInfo);
//	}
//	request.getSession().setAttribute("oauthUser", oAuthUser);
//	return "redirect:/success";
//    }

    @RequestMapping(value = "/success", method=RequestMethod.GET)
    @ResponseBody
    public Object success(HttpServletRequest request){
	return "Log in with " + request.getSession().getAttribute("type");
    }
}

package com.example.oauthtest.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OAuthServices {
    @Autowired
    List<OAuthServiceDeractor> oAuthServiceDeractors;

    public OAuthServiceDeractor getOAuthService(String type){
	Optional<OAuthServiceDeractor> oAuthService = oAuthServiceDeractors.stream().filter(o -> o.getoAuthType().equals(type))
		.findFirst();
	if(oAuthService.isPresent()){
	    return oAuthService.get();
	}
	return null;
    }

    public List<OAuthServiceDeractor> getAllOAuthServices(){
	return oAuthServiceDeractors;
    }
}

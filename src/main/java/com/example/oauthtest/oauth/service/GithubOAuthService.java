package com.example.oauthtest.oauth.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.example.oauthtest.config.OAuthTypes;
import com.example.oauthtest.model.OAuthUser;
import com.example.oauthtest.model.User;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Value;

public class GithubOAuthService extends OAuthServiceDeractor{

    @Value("${oAuth.github.resource.userInfoUri}")
    private String resourceUserInfoUri;

    public GithubOAuthService(OAuthService oAuthService) {
	super(oAuthService, OAuthTypes.GITHUB);
    }

    @Override
    public OAuthUser getOAuthUser(Token accessToken) {
	OAuthRequest request = new OAuthRequest(Verb.GET, resourceUserInfoUri);
	this.signRequest(accessToken, request);
	Response response = request.send();
	OAuthUser oAuthUser = new OAuthUser();
	oAuthUser.setoAuthType(getoAuthType());
	Object result = JSON.parse(response.getBody());
	oAuthUser.setoAuthId(JSONPath.eval(result, "$.id").toString());
	oAuthUser.setUser(new User());
	oAuthUser.getUser().setUsername(JSONPath.eval(result, "$.login").toString());
	return oAuthUser;
    }
}

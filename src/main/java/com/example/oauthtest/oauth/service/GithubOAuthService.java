package com.example.oauthtest.oauth.service;

import com.example.oauthtest.config.OAuthTypes;
import com.example.oauthtest.model.OAuthUser;
import com.example.oauthtest.model.User;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.util.Map;

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
	JsonParser jsonParser = JsonParserFactory.getJsonParser();
	Map<String, Object> result = jsonParser.parseMap(response.getBody());
	oAuthUser.setoAuthId(String.valueOf(result.get("id")));
	oAuthUser.setUser(new User());
	oAuthUser.getUser().setUsername((String) result.get("login"));
	return oAuthUser;
    }
}

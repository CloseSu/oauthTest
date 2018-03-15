package com.example.oauthtest.oauth.service;

import com.example.oauthtest.config.OAuthTypes;
import com.example.oauthtest.model.OauthTypeData;
import lombok.extern.slf4j.Slf4j;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParserFactory;

import java.util.Map;

@Slf4j
public class GithubOAuthService extends OAuthServiceDeractor{

    @Value("${oAuth.github.resource.userInfoUri}")
    private String resourceUserInfoUri;

    public GithubOAuthService(OAuthService oAuthService) {
	super(oAuthService, OAuthTypes.GITHUB);
    }

    @Override
    public OauthTypeData getOAuthUser(Token accessToken) {
	OAuthRequest request = new OAuthRequest(Verb.GET, resourceUserInfoUri);
	this.signRequest(accessToken, request);
	Response response = request.send();
	log.info(response.getBody().toString());
	OauthTypeData oauthTypeData = new OauthTypeData();
	oauthTypeData.setOAuthType(getoAuthType());
	Map<String, Object> result = JsonParserFactory.getJsonParser().parseMap(response.getBody());
	oauthTypeData.setOAuthId(String.valueOf(result.get("id")));
	oauthTypeData.setUsername((String) result.get("login"));

	return oauthTypeData;
    }
}

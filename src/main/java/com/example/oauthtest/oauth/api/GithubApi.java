package com.example.oauthtest.oauth.api;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.utils.OAuthEncoder;
import org.springframework.beans.factory.annotation.Value;

public class GithubApi extends DefaultApi20 {


    @Value("${oAuth.github.userAuthorizationUri}")
    private String userAuthorizationUri;
    @Value("${oAuth.github.accessTokenUri}")
    private String accessTokenUri;

    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        final String authorise_url = userAuthorizationUri + "client_id=%s&redirect_uri=%s";
	if (config.hasScope()){
	    final String scope_authorise_url = authorise_url + "&scope=%s";
	    return String.format(scope_authorise_url,
				 config.getApiKey(),
				 OAuthEncoder.encode(config.getCallback()),
				 OAuthEncoder.encode(config.getScope()));
	}
	else{
	    return String.format(authorise_url,
				 config.getApiKey(),
				 OAuthEncoder.encode(config.getCallback()));
	}
    }

    @Override
    public String getAccessTokenEndpoint() {
	return String.format(accessTokenUri);
    }
}

package com.example.oauthtest.config;

import com.example.oauthtest.oauth.api.GithubApi;
import com.example.oauthtest.oauth.service.GithubOAuthService;
import com.example.oauthtest.oauth.service.OAuthServiceDeractor;
import org.scribe.builder.ServiceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubOAuthConfig {

    private static final String CALLBACK_URL = "%s/oauth/%s/callback";

    @Value("${oAuth.github.clientId}")
    private String githubClientId;
    @Value("${oAuth.github.clientSecret}")
    private String githubClientSecret;
    @Value("${demo.host}")
    private String host;

    @Bean
    public GithubApi githubApi(){
	return new GithubApi();
    }

    @Bean
    public OAuthServiceDeractor getGithubOAuthService(){
	return new GithubOAuthService(new ServiceBuilder()
		.provider(githubApi())
		.apiKey(githubClientId)
		.apiSecret(githubClientSecret)
		.callback(String.format(CALLBACK_URL, host, OAuthTypes.GITHUB))
		.build());
    }


}
package com.example.oauthtest.repository;

import com.example.oauthtest.model.OAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthUserRepository extends JpaRepository<OAuthUser, Integer> {
    OAuthUser findByOAuthTypeAndOAuthId(String oAuthType, String oAuthId);
}

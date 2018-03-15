package com.example.oauthtest.repository;

import com.example.oauthtest.model.OauthTypeData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthTypeDataRepository extends JpaRepository<OauthTypeData, Integer> {
    OauthTypeData findByUsername(String username);
    OauthTypeData findByOAuthType(String oAuthType);

}

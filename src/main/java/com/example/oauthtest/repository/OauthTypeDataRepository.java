package com.example.oauthtest.repository;

import com.example.oauthtest.model.OauthTypeData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OauthTypeDataRepository extends JpaRepository<OauthTypeData, Integer> {
    OauthTypeData findByUsername(String username);
    OauthTypeData findByOAuthType(String oAuthType);
    List<OauthTypeData> findByUserId(Integer userId);
}

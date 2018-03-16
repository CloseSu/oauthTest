package com.example.oauthtest.service;

import com.example.oauthtest.model.OauthTypeData;
import com.example.oauthtest.model.User;

import java.util.List;

public interface UserServiceI {
    void saveData(OauthTypeData oauthTypeData);
    String save(User user);
    String logIn(User user);
    List<OauthTypeData> findTypeList(User user);
}

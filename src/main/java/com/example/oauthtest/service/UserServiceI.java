package com.example.oauthtest.service;

import com.example.oauthtest.model.OauthTypeData;
import com.example.oauthtest.model.User;

public interface UserServiceI {
    void saveData(OauthTypeData oauthTypeData);
    String save(User user);
}

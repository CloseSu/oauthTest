package com.example.oauthtest.service.serviceImpl;

import com.example.oauthtest.model.OauthTypeData;
import com.example.oauthtest.model.User;
import com.example.oauthtest.repository.OauthTypeDataRepository;
import com.example.oauthtest.repository.UserRepository;
import com.example.oauthtest.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserServiceI{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OauthTypeDataRepository oauthTypeDataRepository;

    @Override
    public void saveData(OauthTypeData oauthTypeData) {
	User userFromDb = userRepository.findByUsername(oauthTypeData.getUsername());
	if (userFromDb == null){
	    User newUser = new User();
	    List<OauthTypeData> oauthTypeDataList = new ArrayList<>();
	    oauthTypeDataList.add(oauthTypeData);
	    newUser.setTypeDataList(oauthTypeDataList);
	    newUser.setUsername(oauthTypeData.getUsername());
	    userRepository.save(newUser);
	} else {
	    OauthTypeData oauthTypeDataFromDb = oauthTypeDataRepository.findByOAuthType(oauthTypeData.getOAuthType());
	    oauthTypeData.setUserId(userFromDb.getUserId());
	    if (oauthTypeDataFromDb == null) {
		oauthTypeDataRepository.save(oauthTypeData);
	    } else {
		oauthTypeData.setId(oauthTypeDataFromDb.getId());
		oauthTypeDataRepository.save(oauthTypeData);
	    }

	}
    }

    @Override
    public String save(User user) {
	User userFromDb = userRepository.findByUsername(user.getUsername());
	if (userFromDb == null){
	    userRepository.save(user);
	    return "Register with website";
	}else {
	    return "You have already registered !";
	}
    }

    @Override
    public String logIn(User user) {
	User userFromDb = userRepository.findByUsername(user.getUsername());
	if (userFromDb == null){
	    return "Please register !";
	}
	return "Log in with website";
    }

    @Override
    public List<OauthTypeData> findTypeList(User user) {
	User userFromDb = userRepository.findByUsername(user.getUsername());
	return userFromDb.getTypeDataList();
    }
}

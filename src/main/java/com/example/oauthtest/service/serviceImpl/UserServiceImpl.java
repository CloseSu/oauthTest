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
	}else {
	    oauthTypeDataRepository.save(oauthTypeData);
	}
    }
}

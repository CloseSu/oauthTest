package com.example.oauthtest;

import com.example.oauthtest.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OauthtestApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;


    private MockMvc mvc;
    private User user;

    @Before
    public void setup(){
	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	user = new User();
	user.setUsername("CloseSu");
	user.setPassword("asdasdasdasda");
    }

    @Test
    public void contextLoads() {
	String uri = "/findTypeList";

	MvcResult result = null;
	try {
	    result = mvc.perform(MockMvcRequestBuilders.post(uri)
		    .flashAttr("user", user)
		    .accept(MediaType.APPLICATION_JSON)).andReturn();
	    int status = result.getResponse().getStatus();
	    Assert.assertEquals("good",302 ,status);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}

package com.example.oauthtest.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OAuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private User user;

    private String oAuthType;
    private String oAuthId;


}

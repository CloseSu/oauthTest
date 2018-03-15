package com.example.oauthtest.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OauthTypeData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "oAuthType")
    private String oAuthType;

    @Column(name = "oAuthId")
    private String oAuthId;

    @Column(name = "username")
    private String username;

}

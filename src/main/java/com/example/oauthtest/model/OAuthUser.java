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

    @Column(name = "oAuthType")
    private String oAuthType;
    @Column(name = "oAuthId")
    private String oAuthId;


}

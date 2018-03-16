package com.example.oauthtest.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class OauthTypeData {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "oAuthType")
    private String oAuthType;

    @Column(name = "oAuthId")
    private String oAuthId;

    @Column(name = "username")
    private String username;

    @Column(name = "userId")
    private String userId;

}

package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "admin_info")
@Getter
@Setter
@ToString
public class AdminInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "info_id")
    private Integer infoId = 1;
    @Column(name = "login")
    private String login = "admin";
    @Column(name = "password")
    private String password = "admin";
    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;
}

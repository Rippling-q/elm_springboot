package com.example.elm_springboot.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Long userId;

    private String telephone;

    private String userName;

    private Integer userSex;

    private String userImg; //头像

    private String token;

    private String refreshToken;

}

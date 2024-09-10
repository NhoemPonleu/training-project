package com.acledabankplc.utils;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String username;
    private Long userId;
    private String userRole;
}

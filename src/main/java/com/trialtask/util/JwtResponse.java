package com.trialtask.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private Long id;
    private String username;
    private final String type = "Bearer";

    public JwtResponse(String token, Long id, String username) {
        this.token = token;
        this.id = id;
        this.username = username;
    }
}

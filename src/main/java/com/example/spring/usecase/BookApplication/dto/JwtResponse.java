package com.example.spring.usecase.BookApplication.dto;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {
    private String jwtToken;
    private String username;
}

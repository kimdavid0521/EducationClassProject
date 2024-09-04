package com.example.EducationClassProject.global.filter;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.AuthHandler;
import com.example.EducationClassProject.dto.userDTO.UserRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

   @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse httpServletResponse) throws AuthenticationException {

       UserRequestDTO.LoginRequestDTO loginRequestDTO = readBody(request);

       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken
               .unauthenticated(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

       return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);

   }

    private UserRequestDTO.LoginRequestDTO readBody(HttpServletRequest request) {

        UserRequestDTO.LoginRequestDTO loginRequestDTO = null;

        ObjectMapper om = new ObjectMapper();

        try {
            loginRequestDTO = om.readValue(request.getInputStream(), UserRequestDTO.LoginRequestDTO.class);
        } catch (IOException e) {
            throw new AuthHandler(ErrorStatus._BAD_REQUEST);
        }

        return loginRequestDTO;

    }

    // jwt token 생성 후 response 에 담기

//    @Override
//    protected void successfulAuthentication(HttpServletResponse response, HttpServletRequest request) {
//       response.setStatus(200);
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletResponse response, HttpServletRequest request) {
//       response.setStatus(401);
//    }


}

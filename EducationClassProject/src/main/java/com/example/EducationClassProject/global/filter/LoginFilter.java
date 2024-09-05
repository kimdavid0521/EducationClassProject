package com.example.EducationClassProject.global.filter;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.AuthHandler;
import com.example.EducationClassProject.dto.userDTO.UserRequestDTO;
import com.example.EducationClassProject.global.PrincipalDetails;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Slf4j
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse httpServletResponse) throws AuthenticationException {

       UserRequestDTO.LoginRequestDTO loginRequestDTO = readBody(request);

       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken
               .unauthenticated(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

       return authenticationManager.authenticate(usernamePasswordAuthenticationToken);

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

    @Override
    protected void successfulAuthentication(HttpServletResponse response, HttpServletRequest request, Authentication authResult) throws IOException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // 이메일이랑 role 받기

        String email = principalDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        // accessToken 만들어주기
        String token = jwtUtil.createAccessToken(email, role);

        // 헤더에 추가하기
        response.addHeader("Authorization", "Bearer" + token);

        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());

        BaseResponse<Object> successResponse = BaseResponse.onSuccess(null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), successResponse);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletResponse response, HttpServletRequest request) throws IOException {
       response.setContentType("application/json; charset=UTF-8");
       response.setStatus(HttpStatus.UNAUTHORIZED.value());
       ErrorStatus UN_AUTH = ErrorStatus._AUTHENTICATION_FAILED;

       BaseResponse<Object> errorResponse = BaseResponse.onFailure(UN_AUTH.getCode(), UN_AUTH.getMessage(), null);

       ObjectMapper mapper = new ObjectMapper();
       mapper.writeValue(response.getOutputStream(), errorResponse);
    }


}

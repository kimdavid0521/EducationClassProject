package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.converter.UserConverter;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.userDTO.UserRequestDTO;
import com.example.EducationClassProject.dto.userDTO.UserResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;


    // 유저 회원 가입
    @PostMapping("/join/users")
    public BaseResponse<UserResponseDTO.JoinResultDTO> joinUser(@RequestBody UserRequestDTO.JoinDTO joinDTO) {
        return BaseResponse.onSuccess(userService.joinUser(joinDTO));
    }


    // 유저 로그인
    @PostMapping("/login")
    public BaseResponse<UserResponseDTO.LoginResultDTO> loginUser(@RequestBody UserRequestDTO.LoginRequestDTO loginRequestDTO) {
        return BaseResponse.onSuccess(userService.loginUser(loginRequestDTO)); // solid 원칙에 위배되지않게 service 단에서 모든 로직을 처리
    }

    //유저 단건 조회
    @GetMapping("/find/user")
    public BaseResponse<UserResponseDTO.FindUserResultDTO> findUser(@RequestHeader("Authorization") String token) {

            User user = userService.findUser(token);
            return BaseResponse.onSuccess(UserConverter.toFindUserResultDTO(user));
    }

    // 유저 전체 조회
    @GetMapping("/find/users")
    public BaseResponse<UserResponseDTO.FindUsersListDTO> findAllUser() {
        List<User> userList = userService.findAllUsers();
        return BaseResponse.onSuccess(UserConverter.toFindUserListResultDTO(userList));
    }

    // 유저 삭제
    @DeleteMapping("/delete/user/{userId}")
    public BaseResponse<String> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return BaseResponse.onSuccess("삭제되었습니다.");
    }

    // 유저 개인정보 업데이트
    @PatchMapping("/update/user/{userId}") //개인정보 전체가 아닌 일부만 바뀌게 할 것이므로 patch 사용
    public BaseResponse<UserResponseDTO.FindUserResultDTO> updateUserInfo(@PathVariable UUID userId, @RequestBody UserRequestDTO.UpdateUserInfoDTO updateUserInfoDTO) {
    User user = userService.updateUserInfo(userId, updateUserInfoDTO);
    return BaseResponse.onSuccess(UserConverter.toFindUserResultDTO(user));
    }

}

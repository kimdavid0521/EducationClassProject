package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.userDTO.UserRequestDTO;
import com.example.EducationClassProject.dto.userDTO.UserResponseDTO;
import com.example.EducationClassProject.jwt.AuthUser;
import com.example.EducationClassProject.service.UserCommandService;
import com.example.EducationClassProject.service.UserQueryService;
import com.example.EducationClassProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;


    // 유저 회원 가입
    // ( 유저 회원가입시 이벤트로 500포인트 증정되게 설정 )
    @PostMapping("/join/users")
    public BaseResponse<UserResponseDTO.JoinResultDTO> joinUser(@RequestBody UserRequestDTO.JoinDTO joinDTO) {
        return BaseResponse.onSuccess(userCommandService.joinUser(joinDTO)); // solid 원칙
    }

    // 유저 로그인
    @PostMapping("/login")
    public BaseResponse<UserResponseDTO.LoginResultDTO> loginUser(@RequestBody UserRequestDTO.LoginRequestDTO loginRequestDTO) {
        return BaseResponse.onSuccess(userQueryService.loginUser(loginRequestDTO)); // solid 원칙에 위배되지않게 service 단에서 모든 로직을 처리
    }

    //유저 단건 조회
    @GetMapping("/find/user")
    public BaseResponse<UserResponseDTO.FindUserResultDTO> findUser(@AuthUser User user) {
            return BaseResponse.onSuccess(userQueryService.findUser(user)); // solid 원칙
    }

    // 유저 전체 조회 ( 관리자 페이지 )
    @GetMapping("/find/users")
    public BaseResponse<UserResponseDTO.FindUsersListDTO> findAllUser(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return BaseResponse.onSuccess(userQueryService.findAllUsers(pageable));
    }

    // 유저 삭제 ( 회원 탈퇴 )
    @DeleteMapping("/delete/user")
    public BaseResponse<String> deleteUser(@AuthUser User user) {
        userCommandService.deleteUser(user);
        return BaseResponse.onSuccess("삭제되었습니다.");
    }

    // 유저 개인정보 업데이트
    @PatchMapping("/update/user") //개인정보 전체가 아닌 일부만 바뀌게 할 것이므로 patch 사용
    public BaseResponse<UserResponseDTO.FindUserResultDTO> updateUserInfo(@RequestBody UserRequestDTO.UpdateUserInfoDTO updateUserInfoDTO, @AuthUser User user) {
    return BaseResponse.onSuccess(userCommandService.updateUserInfo(user, updateUserInfoDTO));
    }

}

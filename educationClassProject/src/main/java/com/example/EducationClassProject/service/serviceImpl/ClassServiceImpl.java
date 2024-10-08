package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.LectureRepository;
import com.example.EducationClassProject.repository.UserLectureRepository;
import com.example.EducationClassProject.repository.UserRepository;
import com.example.EducationClassProject.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;
    private final UserLectureRepository userLectureRepository;
    private final JWTUtil jwtUtil;

    // 클래스 생성
//    @Override
//    @Transactional
//    public ClassResponseDTO.CreateClassResultDTO createClass(ClassRequestDTO.CreateClassDTO createClassDTO, String token) {
//
//        String AccessToken = token.replace("Bearer ", "");
//        User user = jwtUtil.getUserFromToken(AccessToken);
//        if (user.getVerify().equals(Verify.FALSE)) {
//            throw new ClassHandler(ErrorStatus._NOT_VERIFY_CLASS);
//        }
//
//        // 잔여 포인트 검증
//        int currentPoint =  user.getPoint();
//        if (currentPoint < 200) {
//            throw new ClassHandler(ErrorStatus._MAKE_POINT_NOT_ENOUGH);
//        }
//
//        Class classEntity = Class.builder()
//                .className(createClassDTO.getClassName())
//                .classIntro(createClassDTO.getClassIntro())
//                .classExplain(createClassDTO.getClassExplain())
//                .classLevel(createClassDTO.getClassLevel())
//                .classDay(createClassDTO.getClassDay())
//                .classStartDay(createClassDTO.getClassStartDay())
//                .classTest(createClassDTO.getClassTest())
//                .owner(user)
//                .build();
//
//        classRepository.save(classEntity);
//
//        // 포인트 차감
//        user.updateUserPoint(currentPoint - 200);
//
//        return new ClassResponseDTO.CreateClassResultDTO(classEntity.getId(), classEntity.getClassName(), classEntity.getClassIntro());
//    }

    // class 참여 요청 보내기
//    @Override
//    public Long joinClass(Long classId, String token) {
//        Class classEntity = classRepository.findById(classId).orElseThrow(() -> {
//            throw new ClassHandler(ErrorStatus._NOT_FOUND_CLASS);
//        });
//
//        String AccessToken = token.replace("Bearer ","");
//        User user = jwtUtil.getUserFromToken(AccessToken);
//
//        // 중복 확인
//        if (userClassRepository.existsByUserAndAClass(user.getId(), classEntity.getId())) {
//            throw new ClassHandler(ErrorStatus._ALREADY_JOINED_CLASS);
//        }

//        // 잔여 포인트 검증
//        int currentPoint = user.getPoint();
//        if (currentPoint < 100) {
//            throw new ClassHandler(ErrorStatus._JOIN_POINT_NOT_ENOUGH);
//        }

//        UserClass userClass = UserClass.builder()
//                .user(user)
//                .aClass(classEntity)
//                .build();
//        userClassRepository.save(userClass);
//
//        // 포인트 차감
//        user.updateUserPoint(currentPoint - 100);
//        return classEntity.getId();
//    }

    // 클래스 객체 유저 id로 조회화기
//    @Override
//    @Transactional(readOnly = true)
//    public ClassResponseDTO.PreviewClassListResultDTO findClassesByUserId(String token) {
//        String AccessToken = token.replace("Bearer ", "");
//        User user = jwtUtil.getUserFromToken(AccessToken);
//        List<Class> classes = classRepository.findClassesByUserId(user.getId());
//        List<ClassResponseDTO.PreviewClassResultDTO> classResultDTOList = classes.stream()
//                .map(clas -> ClassResponseDTO.PreviewClassResultDTO.builder()
//                        .classId(clas.getId())
//                        .className(clas.getClassName())
//                        .classIntro(clas.getClassIntro())
//                        .classExplain(clas.getClassExplain())
//                        .classLevel(clas.getClassLevel())
//                        .build())
//                .collect(Collectors.toList());
//
//        return ClassResponseDTO.PreviewClassListResultDTO.builder()
//                .previewClassResultDTOList(classResultDTOList)
//                .build();
//    }

    // 내가 생성한 class 조회하기
//    @Override
//    public ClassResponseDTO.PreviewClassListResultDTO findClassesByOwner(String token) {
//        String AccessToken = token.replace("Bearer ","");
//        User user = jwtUtil.getUserFromToken(AccessToken);
//
//        if (user.getVerify().equals(Verify.FALSE)) {
//            throw new ClassHandler(ErrorStatus._NOT_TEACHER);
//        }
//
//        List<Class> classes = classRepository.findByOwnerId(user.getId());
//
//        List<ClassResponseDTO.PreviewClassResultDTO> classResultDTOList = classes.stream()
//                .map(classEntity -> ClassResponseDTO.PreviewClassResultDTO.builder()
//                        .classId(classEntity.getId())
//                        .className(classEntity.getClassName())
//                        .classIntro(classEntity.getClassIntro())
//                        .classExplain(classEntity.getClassExplain())
//                        .classLevel(classEntity.getClassLevel())
//                        .build())
//                .collect(Collectors.toList());
//        return ClassResponseDTO.PreviewClassListResultDTO.builder()
//                .previewClassResultDTOList(classResultDTOList)
//                .build();
//    }

    // 클래스 전체 조회
//    @Override
//    @Transactional(readOnly = true)
//    public ClassResponseDTO.PreviewClassListResultDTO findAllClasses() {
//        List<Class> classes = classRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt")); // 클래스 생성된 날짜 순으로 가져오기
//        List<ClassResponseDTO.PreviewClassResultDTO> classResultDTOList = classes.stream()
//                .map(clas -> ClassResponseDTO.PreviewClassResultDTO.builder()
//                        .classId(clas.getId())
//                        .className(clas.getClassName())
//                        .classIntro(clas.getClassIntro())
//                        .classExplain(clas.getClassExplain())
//                        .classLevel(clas.getClassLevel())
//                        .build())
//                .collect(Collectors.toList());
//        return ClassResponseDTO.PreviewClassListResultDTO.builder()
//                .previewClassResultDTOList(classResultDTOList)
//                .build();
//    }

    // 클래스 삭제 (owner 만 가능)
//    @Override
//    @Transactional
//    public void deleteClass(Long classId, String token) {
//
//        String AccessToken = token.replace("Bearer ","");
//        User user = jwtUtil.getUserFromToken(AccessToken);
//
//        Class classEntity = classRepository.findById(classId).orElseThrow(() -> {
//            throw new ClassHandler(ErrorStatus._NOT_FOUND_CLASS);
//        });
//
//        if (!classEntity.getOwner().getId().equals(user.getId())) {
//            throw new ClassHandler(ErrorStatus._NOT_CLASS_OWNER_DELETE);
//        }
//
//        classRepository.delete(classEntity);
//    }

    // 클래스 업데이트
//    @Override
//    @Transactional
//    public ClassResponseDTO.PreviewClassResultDTO updateClass(Long classId, String token, ClassRequestDTO.UpdateClassDTO updateClassDTO) {
//
//        String AccessToken = token.replace("Bearer ","");
//        User user = jwtUtil.getUserFromToken(AccessToken);
//
//        Class classEntity = classRepository.findById(classId).orElseThrow(() -> {
//            throw new ClassHandler(ErrorStatus._NOT_FOUND_CLASS);
//        });
//
//        if (!classEntity.getOwner().getId().equals(user.getId())) {
//            throw new ClassHandler(ErrorStatus._NOT_CLASS_OWNER);
//        }
//
//        classEntity.updateClass(updateClassDTO); // 더티 체킹을 통한 업데이트
//        return new ClassResponseDTO.PreviewClassResultDTO(classEntity.getId(), classEntity.getClassName(), classEntity.getClassIntro(), classEntity.getClassExplain(), classEntity.getClassLevel());
//    }
}

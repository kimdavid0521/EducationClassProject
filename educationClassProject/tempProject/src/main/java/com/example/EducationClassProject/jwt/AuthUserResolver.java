package com.example.EducationClassProject.jwt;

import com.example.EducationClassProject.global.PrincipalDetails;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthUserResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AuthUser.class) != null &&
                PrincipalDetails.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        PrincipalDetails principalDetails = (PrincipalDetails) webRequest.getAttribute("SPRING_SECURITY_CONTEXT", 0);
        return principalDetails != null ? principalDetails.getUser() : null; // 유저 객체를 반환해줍니다.
    }
}

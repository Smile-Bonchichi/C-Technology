package kg.smile.ctechnology.service;

import kg.smile.ctechnology.dto.request.UserAuthRequestDto;
import kg.smile.ctechnology.dto.response.UserAuthResponseDto;
import kg.smile.ctechnology.dto.response.base.BaseResponseDto;

public interface AuthLogoutService {
    UserAuthResponseDto authUser(UserAuthRequestDto userAuthRequestDto);

    BaseResponseDto logout();
}

package com.lunch.lunch_recommend_api.domain.user.svc;

import com.lunch.lunch_recommend_api.domain.user.dto.req.UserDtoReq;
import com.lunch.lunch_recommend_api.domain.user.dto.res.UserDtoRes;

public interface UserService{

    UserDtoRes save(UserDtoReq requestDto);

    UserDtoRes findUserById(String userId);
}

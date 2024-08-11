package com.lunch.lunch_recommend_api.domain.user.repo.custom;

import com.lunch.lunch_recommend_api.domain.user.dto.res.UserDtoRes;

public interface UserRepositoryCustom {

    String testUser();

    UserDtoRes findUserById(String userId);
}


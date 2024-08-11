package com.lunch.lunch_recommend_api.domain.user.dto.res;

import com.lunch.lunch_recommend_api.domain.user.UserEntity;
import com.querydsl.core.annotations.QueryProjection;

public record UserDtoRes(String userId, String name) {

    public static UserDtoRes from(UserEntity entity) {
        return new UserDtoRes(entity.getUserId(), entity.getName());
    }


    @QueryProjection
    public UserDtoRes(UserEntity entity) {
        this(
                entity.getUserId(),
                entity.getName()
        );
    }

}

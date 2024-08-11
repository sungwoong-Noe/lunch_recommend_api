package com.lunch.lunch_recommend_api.domain.user.repo.custom;


import com.lunch.lunch_recommend_api.domain.user.QUserEntity;
import com.lunch.lunch_recommend_api.domain.user.dto.res.QUserDtoRes;
import com.lunch.lunch_recommend_api.domain.user.dto.res.UserDtoRes;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.lunch.lunch_recommend_api.domain.user.QUserEntity.userEntity;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public String testUser() {

        queryFactory.selectFrom(userEntity).fetch();

        return "Test";
    }


    @Override
    public UserDtoRes findUserById(String userId) {

        return queryFactory.select(new QUserDtoRes(userEntity))
                .from(userEntity)
                .where(userEntity.userId.eq(userId))
                .fetchOne();
    }
}

package com.lunch.lunch_recommend_api.domain.user.repo;

import com.lunch.lunch_recommend_api.domain.user.UserEntity;
import com.lunch.lunch_recommend_api.domain.user.repo.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String>, UserRepositoryCustom {
}

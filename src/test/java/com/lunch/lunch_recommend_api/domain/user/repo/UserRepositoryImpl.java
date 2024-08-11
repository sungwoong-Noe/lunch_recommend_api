package com.lunch.lunch_recommend_api.domain.user.repo;

import com.lunch.lunch_recommend_api.domain.user.UserEntity;
import com.lunch.lunch_recommend_api.domain.user.dto.req.UserDtoReq;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryImpl {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("유저를 저장한다")
    public void save() {

        //given
        UserEntity userEntity = UserEntity.builder()
                .userId("test_user")
                .name("tester")
                .password("12345678")
                .build();

        //when
        userRepository.save(userEntity);


        //then
        assertThat(userRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("유저를 중복 저장한다")
    public void duple() {

        //given
        UserEntity userEntity_1 = UserEntity.builder()
                .userId("test_user")
                .name("tester")
                .password("12345678")
                .build();

        UserEntity userEntity_2 = UserEntity.builder()
                .userId("test_user")
                .name("tester2")
                .password("12345678")
                .build();

        //when
        userRepository.save(userEntity_1);
        userRepository.save(userEntity_2);


        //then
        assertThatThrownBy(() -> {
            userRepository.save(userEntity_2);
        }).isInstanceOf(Exception.class);

    }

}

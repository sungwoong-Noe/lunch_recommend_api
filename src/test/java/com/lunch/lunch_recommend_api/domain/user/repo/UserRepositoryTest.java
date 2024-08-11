package com.lunch.lunch_recommend_api.domain.user.repo;

import com.lunch.lunch_recommend_api.conf.QueryDslConfig;
import com.lunch.lunch_recommend_api.domain.user.UserEntity;
import com.lunch.lunch_recommend_api.domain.user.dto.req.UserDtoReq;
import com.lunch.lunch_recommend_api.domain.user.dto.res.UserDtoRes;
import jakarta.persistence.SqlResultSetMapping;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDslConfig.class)
public class UserRepositoryTest {

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
        assertThat(userRepository.findAll().size()).isEqualTo(1);
    }


    @Test
    @DisplayName("ID로 유저를 조회한다 - 성공")
    public void findUserById_test() {

        //given
        UserDtoReq requestDto = UserDtoReq.builder()
                .userId("test_user")
                .name("tester")
                .password("12345678")
                .build();


        //when
        userRepository.save(requestDto.toEntity());

        UserDtoRes userById = userRepository.findUserById(requestDto.getUserId());

        //then
        assertThat(userById.userId()).isEqualTo("test_user");
        assertThat(userById.name()).isEqualTo("tester");

    }
}

package com.lunch.lunch_recommend_api.domain.user.svc;

import com.lunch.lunch_recommend_api.domain.user.UserEntity;
import com.lunch.lunch_recommend_api.domain.user.dto.req.UserDtoReq;
import com.lunch.lunch_recommend_api.domain.user.dto.res.UserDtoRes;
import com.lunch.lunch_recommend_api.domain.user.repo.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    UserService userService;

    @BeforeEach
    public void setup() {
        this.userService = new UserServiceImpl(userRepository);
    }


    @DisplayName("유저를 저장한다.")
    @Test
    public void saveTest() {

        // given
        UserDtoReq requestDto = UserDtoReq.builder()
                .userId("test_user")
                .name("tester")
                .password("12345678")
                .build();

        UserEntity userEntity = requestDto.toEntity();

        // when
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserService userService = new UserServiceImpl(userRepository);
        UserDtoRes result = userService.save(requestDto);

        assertThat(result.name()).isEqualTo("tester");
    }

    @DisplayName("중복 저장 - 예외 발생")
    @Test
    public void dupleSave() {


        //given
        UserDtoReq requestDto_1 = UserDtoReq.builder()
                .userId("test_user")
                .name("tester")
                .password("12345678")
                .build();


        //when
        when(userRepository.existsById(requestDto_1.getUserId())).thenReturn(true);


        //then
        assertThatThrownBy(() -> userService.save(requestDto_1)).isInstanceOf(RuntimeException.class);
    }


    @DisplayName("유저 id로 조회한다 - 성공")
    @Test
    public void findUser() {
        //given
        UserDtoReq requestDto = UserDtoReq.builder()
                .userId("test_user")
                .name("tester")
                .password("12345678")
                .build();

        UserEntity entity = requestDto.toEntity();

        //when
        when(userRepository.findUserById(requestDto.getUserId())).thenReturn(UserDtoRes.from(entity));
        UserDtoRes userById = userService.findUserById(requestDto.getUserId());

        //then
        assertThat(userById.userId()).isEqualTo("test_user");
        assertThat(userById.name()).isEqualTo("tester");
    }

    @DisplayName("유저 id로 조회 - 실패")
    @Test
    public void findUserException() {

        //given
        String requestId = "test";

        //expected
        when(userRepository.findUserById(requestId)).thenReturn(null);
        assertThatThrownBy(() -> userService.findUserById(requestId)).isInstanceOf(RuntimeException.class);

    }
}

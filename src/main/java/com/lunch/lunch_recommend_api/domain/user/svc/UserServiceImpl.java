package com.lunch.lunch_recommend_api.domain.user.svc;

import ch.qos.logback.core.util.StringUtil;
import com.lunch.lunch_recommend_api.domain.user.UserEntity;
import com.lunch.lunch_recommend_api.domain.user.dto.req.UserDtoReq;
import com.lunch.lunch_recommend_api.domain.user.dto.res.UserDtoRes;
import com.lunch.lunch_recommend_api.domain.user.repo.UserRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDtoRes save(UserDtoReq requestDto) {

        if (userRepository.existsById(requestDto.getUserId())) {
            throw new RuntimeException("중복된 아이디 ==> " + requestDto.getUserId());
        }

        UserEntity userEntity = requestDto.toEntity();
        userRepository.save(userEntity);

        String s = userRepository.testUser();

        return UserDtoRes.from(userEntity);
    }

    @Override
    public UserDtoRes findUserById(String userId) {

        UserDtoRes userById = userRepository.findUserById(userId);

        if (userById == null) {
            throw new RuntimeException("유저 정보가 없습니다. ===> " + userId);
        }

        return userById;
    }
}
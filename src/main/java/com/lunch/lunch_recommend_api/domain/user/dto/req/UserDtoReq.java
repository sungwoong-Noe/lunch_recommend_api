package com.lunch.lunch_recommend_api.domain.user.dto.req;

import com.lunch.lunch_recommend_api.domain.user.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDtoReq {


    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 4, max = 20, message = "아이디는 4글자에서 20글자 사이로 작성해주세요.")
    private String userId;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8 ~ 20글자 사이로 입력해주세요.")
    private String password;


    @Builder
    public UserDtoReq(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(this.userId)
                .name(this.name)
                .password(this.password)
                .build();
    }

}

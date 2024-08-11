package com.lunch.lunch_recommend_api.domain.user;


import com.lunch.lunch_recommend_api.domain.user.dto.req.UserDtoReq;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UserEntity {

//    @NotBlank
//    @Size(min = 4, max = 20, message = "아이디는 4글자에서 20글자 이하로 작성해주세요.")
    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;


    @Builder
    public UserEntity(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

}

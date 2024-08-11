package com.lunch.lunch_recommend_api.domain.user.ctr;

import com.lunch.lunch_recommend_api.domain.user.dto.req.UserDtoReq;
import com.lunch.lunch_recommend_api.domain.user.dto.res.UserDtoRes;
import com.lunch.lunch_recommend_api.domain.user.svc.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDtoRes> save(@RequestBody UserDtoReq requestDto) {

        UserDtoRes save = userService.save(requestDto);

        return ResponseEntity.ok(save);
    }
}

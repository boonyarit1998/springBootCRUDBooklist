package com.restapi.booklists.controller;

import com.restapi.booklists.entity.UserEntity;
import com.restapi.booklists.dto.CommonResponse;
import com.restapi.booklists.dto.ErrorResponse;
import com.restapi.booklists.service.UserService;
import com.restapi.booklists.utility.bookConstant;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController implements bookConstant {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;


}

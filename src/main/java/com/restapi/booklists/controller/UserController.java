package com.restapi.booklists.controller;

import com.restapi.booklists.entity.UserEntity;
import com.restapi.booklists.dto.CommonResponse;
import com.restapi.booklists.dto.ErrorResponse;
import com.restapi.booklists.service.UserServiceImpl;
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
    private final UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        logger.info("start getAllUsers");
        CommonResponse commonResponse = new CommonResponse();
        try {
            List<UserEntity> users = userService.getAllUsers();
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(users);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR, e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("end getAllUsers");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        logger.info("start getUserById");
        CommonResponse commonResponse = new CommonResponse();
        try {
            Optional<UserEntity> user = userService.getUserById(id);
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(user);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR, e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("end getUserById");
        }
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody UserEntity userEntity) {
        logger.info("start addUser");
        CommonResponse commonResponse = new CommonResponse();
        try {
            UserEntity user = userService.registerUser(userEntity);
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(user);
            return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR, e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("end addUser");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editUser(@PathVariable Long id, @RequestBody UserEntity userEdit) {
        logger.info("start editUser");
        CommonResponse commonResponse = new CommonResponse();
        try {
            UserEntity user = userService.updateUser(userEdit, id);
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(user);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR, e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("end editUser");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        logger.info("start deleteUser");
        CommonResponse commonResponse = new CommonResponse();
        try {
            userService.deleteUser(id);
            commonResponse.setStatus(STATUS_SUCCESS);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR, e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("end deleteUser");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchUser(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email) {
        logger.info("start searchUser");
        CommonResponse commonResponse = new CommonResponse();
        try {
            List<UserEntity> users = userService.searchUsers(username, email);
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(users);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR, e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("end searchUser");
        }
    }
}

package com.leo.vetfind.service.usuario;

import com.leo.vetfind.dto.user.CreateUserRequest;
import com.leo.vetfind.dto.user.UserResponse;
import com.leo.vetfind.dto.user.UpdateUserRequest;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest dto);

    List<UserResponse> findAllUsers();

    UserResponse findUserById(Long id);

    UserResponse updateUser(Long id, UpdateUserRequest dto);

    void deleteUser(Long id);
}

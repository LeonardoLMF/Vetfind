package com.leo.vetfind.service.usuario;

import com.leo.vetfind.dto.user.CreateUserRequest;
import com.leo.vetfind.dto.user.UserResponse;
import com.leo.vetfind.entity.User;
import com.leo.vetfind.entity.Veterinarian;
import com.leo.vetfind.exception.*;
import com.leo.vetfind.mapper.UserMapper;
import com.leo.vetfind.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void CriarUsuarioComSucesso() {

        CreateUserRequest request =
                CreateUserRequest.builder()
                        .email("teste@email.com")
                        .build();

        User user = User.builder()
                .id(1L)
                .email("teste@email.com")
                .build();

        UserResponse response =
                UserResponse.builder()
                        .id(1L)
                        .email("teste@email.com")
                        .build();

        when(userRepository.existsByEmail("teste@email.com"))
                .thenReturn(false);

        when(userMapper.toEntity(request))
                .thenReturn(user);

        when(userRepository.save(user))
                .thenReturn(user);

        when(userMapper.toResponseDTO(user))
                .thenReturn(response);

        UserResponse result =
                userService.createUser(request);

        assertNotNull(result);
        assertEquals("teste@email.com", result.getEmail());

        verify(userRepository).existsByEmail("teste@email.com");
        verify(userRepository).save(user);
    }

    @Test
    void LancarExcecaoQuandoEmailJaCadastrado() {

        CreateUserRequest request =
                CreateUserRequest.builder()
                        .email("teste@email.com")
                        .build();

        when(userRepository.existsByEmail("teste@email.com"))
                .thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class,
                () -> userService.createUser(request));

        verify(userRepository, never()).save(any());
    }

    @Test
    void BuscarUsuarioPorIdComSucesso() {

        User user = User.builder()
                .id(1L)
                .email("teste@email.com")
                .build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(userMapper.toResponseDTO(user))
                .thenReturn(
                        UserResponse.builder()
                                .id(1L)
                                .email("teste@email.com")
                                .build()
                );

        UserResponse result =
                userService.findUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void LancarExcecaoQuandoUsuarioNaoExistir() {

        when(userRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.findUserById(99L));
    }

    @Test
    void DeletarUsuarioComSucesso() {

        User user = User.builder()
                .id(1L)
                .build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository).delete(user);
    }

    @Test
    void LancarExcecaoAoDeletarUsuarioComVeterinario() {

        User user = User.builder()
                .id(1L)
                .veterinarian(new Veterinarian())
                .build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        assertThrows(UserHasVeterinarianException.class,
                () -> userService.deleteUser(1L));

        verify(userRepository, never()).delete(any());
    }
}

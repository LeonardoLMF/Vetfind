package com.leo.vetfind.service.veterinario;

import com.leo.vetfind.dto.veterinario.CadastroVeterinarioRequestDTO;
import com.leo.vetfind.dto.veterinario.CadastroVeterinarioResponseDTO;
import com.leo.vetfind.entity.TipoUsuario;
import com.leo.vetfind.entity.usuario.Usuario;
import com.leo.vetfind.entity.veterinario.Veterinario;
import com.leo.vetfind.mapper.VeterinarioMapper;
import com.leo.vetfind.repository.UsuarioRepository;
import com.leo.vetfind.repository.VeterinarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VeterinarioServiceImplTest {

    @Mock
    private VeterinarioRepository veterinarioRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private VeterinarioMapper veterinarioMapper;
    @InjectMocks
    private VeterinarioServiceImpl veterinarioService;

    @Test
    void deveCriarVeterinarioComSucesso() {
        //giv
        CadastroVeterinarioRequestDTO request = new CadastroVeterinarioRequestDTO(
                "CRMV123",
                1L
        );

        Usuario usuario = Usuario.builder()
                .id(1L)
                .tipoUsuario(TipoUsuario.VETERINARIO)
                .build();

        Veterinario veterinario = Veterinario.builder()
                .id(10L)
                .crmv("CRMV123")
                .usuario(usuario)
                .build();

        CadastroVeterinarioResponseDTO responseEsperado =
                new CadastroVeterinarioResponseDTO(
                        10L,
                        "CRMV123",
                        1L
                );

        when(veterinarioRepository.existsByCrmv("CRMV123"))
                .thenReturn(false);

        when(usuarioRepository.findById(1L))
                .thenReturn(java.util.Optional.of(usuario));

        when(veterinarioRepository.save(any(Veterinario.class)))
                .thenReturn(veterinario);

        when(veterinarioMapper.toResponseDTO(veterinario))
                .thenReturn(responseEsperado);

        //wh
        CadastroVeterinarioResponseDTO resultado =
                veterinarioService.criarVeterinario(request);

        //th
        assertNotNull(resultado);
        assertEquals(10L, resultado.getId());
        assertEquals("CRMV123", resultado.getCrmv());
        assertEquals(1L, resultado.getUsuarioId());

        verify(veterinarioRepository).existsByCrmv("CRMV123");
        verify(usuarioRepository).findById(1L);
        verify(veterinarioRepository).save(any(Veterinario.class));
        verify(veterinarioMapper).toResponseDTO(veterinario);

}}



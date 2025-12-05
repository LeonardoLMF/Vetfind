package com.leo.vetfind.service;


import com.leo.vetfind.dto.usuario.CadastroUsuarioRequestDTO;
import com.leo.vetfind.dto.usuario.CadastroUsuarioResponseDTO;
import com.leo.vetfind.entity.usuario.Usuario;
import com.leo.vetfind.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public CadastroUsuarioResponseDTO criarUsuario(CadastroUsuarioRequestDTO dto) {
        // Garantir que o email seja unico
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        // cria um usuario
        Usuario usuario = Usuario.builder()
                .email(dto.getEmail())
                .senha(dto.getSenha()) // futuramente será criptografada
                .telefone(dto.getTelefone())
                .tipoUsuario(dto.getTipoUsuario())
                .build();

        // persiste
        Usuario salvar = usuarioRepository.save(usuario);

        // retorna o dto resposta
        return CadastroUsuarioResponseDTO.builder()
                .id(salvar.getId())
                .email(salvar.getEmail())
                .telefone(salvar.getTelefone())
                .tipoUsuario(salvar.getTipoUsuario().name())
                .build();
    }
}

package com.leo.vetfind.service.veterinario;

import com.leo.vetfind.dto.veterinario.CadastroVeterinarioRequestDTO;
import com.leo.vetfind.dto.veterinario.CadastroVeterinarioResponseDTO;
import com.leo.vetfind.dto.veterinario.UpdateVeterinarioRequestDTO;
import org.mapstruct.Mapper;

import java.util.List;

public interface VeterinarioService {

    CadastroVeterinarioResponseDTO criarVeterinario(CadastroVeterinarioRequestDTO dto);

    CadastroVeterinarioResponseDTO getById(Long id);

    List<CadastroVeterinarioResponseDTO> getAll();

    CadastroVeterinarioResponseDTO atualizar(Long id, UpdateVeterinarioRequestDTO dto);

}

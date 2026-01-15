package com.leo.vetfind.controller;

import com.leo.vetfind.dto.veterinarian.CreateVeterinarianRequest;
import com.leo.vetfind.dto.veterinarian.VeterinarianResponse;
import com.leo.vetfind.dto.veterinarian.UpdateVeterinarianRequest;
import com.leo.vetfind.service.veterinario.VeterinarianServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
@RequiredArgsConstructor
public class VeterinarianController {

    private final VeterinarianServiceImpl veterinarioService;

    @PostMapping
    public ResponseEntity<VeterinarianResponse> create(@Valid @RequestBody CreateVeterinarianRequest dto) {
        VeterinarianResponse response = veterinarioService.createVeterinarian(dto);
            return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<VeterinarianResponse>> findAllVeterinarian() {
            return ResponseEntity.ok(veterinarioService.findAllVeterinarians());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarianResponse> findVeterinarianById(@PathVariable Long id) {
        VeterinarianResponse response = veterinarioService.findVeterinarianById(id);
            return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarianResponse> updateVeterinarian (@PathVariable Long id, @Valid @RequestBody UpdateVeterinarianRequest dto) {
        VeterinarianResponse response =
                veterinarioService.updateVeterinarian(id, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeterinarian (@PathVariable Long id) {
        veterinarioService.deleteVeterinarian(id);
        return ResponseEntity.noContent().build();
    }

}

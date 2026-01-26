package com.leo.vetfind.entity;

public enum AppointmentStatus {
    PENDING,      // aguarda confirmação do veterinario
    CONFIRMED,    // veterinário confirmou
    REJECTED,     // veterinário recusou
    CANCELLED,    // cancelado (por qualquer parte)
    COMPLETED     // realizado
}

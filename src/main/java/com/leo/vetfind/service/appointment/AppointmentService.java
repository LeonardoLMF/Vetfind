package com.leo.vetfind.service.appointment;

import com.leo.vetfind.dto.appointment.AppointmentResponse;
import com.leo.vetfind.dto.appointment.CreateAppointmentRequest;
import com.leo.vetfind.entity.AppointmentStatus;

import java.util.List;

public interface AppointmentService {

    AppointmentResponse createAppointment(Long ownerId, CreateAppointmentRequest dto);

    List<AppointmentResponse> findAllAppointments();

    AppointmentResponse findAppointmentById(Long id);

    List<AppointmentResponse> findAppointmentsByOwner(Long ownerId);

    List<AppointmentResponse> findAppointmentsByVeterinarian(Long veterinarianId);

    List<AppointmentResponse> findAppointmentsByStatus(AppointmentStatus status);

    AppointmentResponse confirmAppointment(Long appointmentId, Long veterinarianId);

    AppointmentResponse rejectAppointment(Long appointmentId, Long veterinarianId);

    AppointmentResponse cancelAppointment(Long appointmentId, Long userId);

    AppointmentResponse completeAppointment(Long appointmentId, Long veterinarianId);
}
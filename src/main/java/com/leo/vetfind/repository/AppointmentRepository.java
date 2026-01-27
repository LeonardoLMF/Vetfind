package com.leo.vetfind.repository;

import com.leo.vetfind.entity.Appointment;
import com.leo.vetfind.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByOwnerId(Long ownerId);

    List<Appointment> findByVeterinarianId(Long veterinarianId);

    List<Appointment> findByStatus(AppointmentStatus status);

    // check if veterinarian has conflicting appointment
    @Query("""
        SELECT a FROM Appointment a
        WHERE a.veterinarian.id = :veterinarianId
        AND a.appointmentDate = :date
        AND a.appointmentTime = :time
        AND a.status IN ('PENDING', 'CONFIRMED')
        AND a.id != :excludeId
    """)
    List<Appointment> findConflictingAppointments(
            @Param("veterinarianId") Long veterinarianId,
            @Param("date") LocalDate date,
            @Param("time") LocalTime time,
            @Param("excludeId") Long excludeId
    );

    // check conflicts for new appointments
    @Query("""
        SELECT a FROM Appointment a
        WHERE a.veterinarian.id = :veterinarianId
        AND a.appointmentDate = :date
        AND a.appointmentTime = :time
        AND a.status IN ('PENDING', 'CONFIRMED')
    """)
    List<Appointment> findConflictingAppointmentsForNew(
            @Param("veterinarianId") Long veterinarianId,
            @Param("date") LocalDate date,
            @Param("time") LocalTime time
    );

    // find appointments by veterinarian and status
    List<Appointment> findByVeterinarianIdAndStatus(
            Long veterinarianId,
            AppointmentStatus status
    );

    // find appointments by owner and status
    List<Appointment> findByOwnerIdAndStatus(
            Long ownerId,
            AppointmentStatus status
    );
}
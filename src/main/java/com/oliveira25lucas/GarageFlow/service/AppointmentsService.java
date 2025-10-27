package com.oliveira25lucas.GarageFlow.service;

import com.oliveira25lucas.GarageFlow.domain.Appointment;
import com.oliveira25lucas.GarageFlow.dto.CreateAppointmentRequest;

import java.util.concurrent.atomic.AtomicLong;

public class AppointmentsService {
    private final AtomicLong seq = new AtomicLong(3000);

    public Appointment create(CreateAppointmentRequest in) {
        Long id = seq.incrementAndGet();
        return new Appointment(
                id,
                in.getServiceId(),
                in.getMechanicId(),
                in.getCustomerName(),
                in.getVehiclePlate(),
                in.getScheduledAt(),
                in.getNotes()
        );
    }
}
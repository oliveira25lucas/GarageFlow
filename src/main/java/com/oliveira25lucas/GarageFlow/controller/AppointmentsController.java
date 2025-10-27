package com.oliveira25lucas.GarageFlow.controller;

import com.oliveira25lucas.GarageFlow.domain.Appointment;
import com.oliveira25lucas.GarageFlow.dto.AppointmentResponse;
import com.oliveira25lucas.GarageFlow.dto.CreateAppointmentRequest;
import com.oliveira25lucas.GarageFlow.service.AppointmentsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentsController {

    private final AppointmentsService service = new AppointmentsService();

    @PostMapping
    public ResponseEntity<AppointmentResponse> create(@Valid @RequestBody CreateAppointmentRequest body) {
        Appointment appt = service.create(body);
        AppointmentResponse out = toResponse(appt);
        URI location = URI.create("/api/v1/appointments/" + appt.getId());
        return ResponseEntity.created(location).body(out);
    }

    private static AppointmentResponse toResponse(Appointment a) {
        return new AppointmentResponse(
                a.getId(), a.getServiceId(), a.getMechanicId(),
                a.getCustomerName(), a.getVehiclePlate(),
                a.getScheduledAt(), a.getNotes()
        );
    }
}
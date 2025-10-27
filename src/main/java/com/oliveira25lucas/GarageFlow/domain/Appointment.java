package com.oliveira25lucas.GarageFlow.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Appointment {
    private Long id;
    private Long serviceId;
    private Long mechanicId;
    private String customerName;
    private String vehiclePlate;
    private LocalDateTime scheduledAt;
    private String notes;

    public Appointment() {
    }

    public Appointment(
            Long id, Long serviceId, Long mechanicId,
            String customerName, String vehiclePlate,
            LocalDateTime scheduledAt, String notes
    ) {
        this.id = id;
        this.serviceId = serviceId;
        this.mechanicId = mechanicId;
        this.customerName = customerName;
        this.vehiclePlate = vehiclePlate;
        this.scheduledAt = scheduledAt;
        this.notes = notes;
    }
}

package com.oliveira25lucas.GarageFlow.dto;

import java.time.LocalDateTime;

public class AppointmentResponse {
    private Long id;
    private Long serviceId;
    private Long mechanicId;
    private String customerName;
    private String vehiclePlate;
    private LocalDateTime scheduledAt;
    private String notes;

    public AppointmentResponse() {
    }

    public AppointmentResponse(
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
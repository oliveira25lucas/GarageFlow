package com.oliveira25lucas.GarageFlow.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateAppointmentRequest {

    @NotBlank(message = "customerName is required")
    private String customerName;

    @NotBlank(message = "vehiclePlate is required")
    private String vehiclePlate;

    @NotNull(message = "serviceId is required")
    private Long serviceId;

    @NotNull(message = "mechanicId is required")
    private Long mechanicId;

    @NotNull(message = "scheduledAt is required")
    @Future(message = "scheduledAt must be in the future")
    private LocalDateTime scheduledAt;

    @Size(max = 500, message = "notes max length is 500")
    private String notes;

    public CreateAppointmentRequest() {
    }
}


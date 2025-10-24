package com.oliveira25lucas.GarageFlow.controller;

import com.oliveira25lucas.GarageFlow.domain.ServiceItem;
import com.oliveira25lucas.GarageFlow.dto.ServiceResponse;
import com.oliveira25lucas.GarageFlow.service.ServicesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
public class ServicesController {

    private final ServicesService servicesService = new ServicesService(); // simples por enquanto

    @GetMapping
    public List<ServiceResponse> list() {
        return servicesService.listAll().stream()
                .map(ServicesController::toResponse)
                .toList();
    }

    private static ServiceResponse toResponse(ServiceItem s) {
        return new ServiceResponse(s.id(), s.name(), s.durationMinutes(), s.price());
    }
}
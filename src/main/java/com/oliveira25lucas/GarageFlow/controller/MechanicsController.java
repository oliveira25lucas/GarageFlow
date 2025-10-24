package com.oliveira25lucas.GarageFlow.controller;

import com.oliveira25lucas.GarageFlow.domain.Mechanic;
import com.oliveira25lucas.GarageFlow.dto.MechanicResponse;
import com.oliveira25lucas.GarageFlow.service.MechanicsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mechanics")
public class MechanicsController {

    private final MechanicsService mechanicsService = new MechanicsService();

    @GetMapping
    public List<MechanicResponse> list() {
        return mechanicsService.listAll().stream()
                .map(MechanicsController::toResponse)
                .toList();
    }

    private static MechanicResponse toResponse(Mechanic m) {
        return new MechanicResponse(m.id(), m.name(), m.specialties(), m.active());
    }
}
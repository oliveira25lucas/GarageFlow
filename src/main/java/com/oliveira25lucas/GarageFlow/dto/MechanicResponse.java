package com.oliveira25lucas.GarageFlow.dto;

import java.util.List;

public record MechanicResponse(Long id, String name, List<String> specialties, boolean active) {}

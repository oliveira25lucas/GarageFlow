package com.oliveira25lucas.GarageFlow.dto;

import java.math.BigDecimal;

public record ServiceResponse(Long id, String name, Integer durationMinutes, BigDecimal price) {}
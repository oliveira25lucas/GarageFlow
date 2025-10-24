package com.oliveira25lucas.GarageFlow.domain;

import java.math.BigDecimal;

public record ServiceItem(Long id, String name, Integer durationMinutes, BigDecimal price) {}
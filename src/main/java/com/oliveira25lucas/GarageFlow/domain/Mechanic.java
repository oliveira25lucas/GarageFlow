package com.oliveira25lucas.GarageFlow.domain;

import java.util.List;

public record Mechanic(Long id, String name, List<String> specialties, boolean active) {

}

package com.oliveira25lucas.GarageFlow.service;

import com.oliveira25lucas.GarageFlow.domain.Mechanic;

import java.util.List;

public class MechanicsService {
    public List<Mechanic> listAll() {
        return List.of(
                new Mechanic(2001L, "Ana Souza", List.of("Revisão", "Freios"), true),
                new Mechanic(2002L, "Carlos Lima", List.of("Suspensão", "Direção"), true),
                new Mechanic(2003L, "Beatriz N.", List.of("Elétrica"), false)
        );
    }
}
package com.oliveira25lucas.GarageFlow.service;

import com.oliveira25lucas.GarageFlow.domain.ServiceItem;

import java.math.BigDecimal;
import java.util.List;

public class ServicesService {
    public List<ServiceItem> listAll() {
        return List.of(
                new ServiceItem(1001L, "Troca de Óleo", 45, new BigDecimal("149.90")),
                new ServiceItem(1002L, "Alinhamento e Balanceamento", 60, new BigDecimal("199.00")),
                new ServiceItem(1003L, "Revisão Básica", 120, new BigDecimal("399.00"))
        );
    }
}
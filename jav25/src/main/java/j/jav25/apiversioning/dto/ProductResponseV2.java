package j.jav25.apiversioning.dto;

import java.math.BigDecimal;

public record ProductResponseV2(
        Long id,
        String name,
        BigDecimal price,
        String category,
        Boolean available
)
{}

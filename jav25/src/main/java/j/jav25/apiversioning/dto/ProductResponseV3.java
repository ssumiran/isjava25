package j.jav25.apiversioning.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProductResponseV3(
        Long id,
        String displayName,
        BigDecimal price,
        String category,
        Integer stock,
        List<String> tags,
        LocalDateTime lastUpdated
)
{}

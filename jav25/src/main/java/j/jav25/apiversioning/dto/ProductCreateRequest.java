package j.jav25.apiversioning.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductCreateRequest(
	@NotBlank(message = "Product name is required")
    String name,
    String description,

    @NotNull(message = "Product price is required")
    @Positive(message = "Product price must be greater than zero")
    BigDecimal price,

    @NotNull(message = "Product quantity is required")
    @PositiveOrZero(message = "Product quantity cannot be negative")
    Integer quantity	
)
{}

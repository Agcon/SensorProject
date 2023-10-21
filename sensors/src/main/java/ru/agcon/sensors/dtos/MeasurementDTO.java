package ru.agcon.sensors.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MeasurementDTO {
    @NotNull
    @DecimalMin(message = "Temperature should be in [-100:100]", value = "-100")
    @DecimalMax(message = "Temperature should be in [-100:100]", value = "100")
    private BigDecimal value;

    @NotNull
    private boolean isRaining;

    @NotNull
    private SensorDTO sensor;

    public MeasurementDTO(BigDecimal value, boolean isRaining, SensorDTO sensor) {
        this.value = value;
        this.isRaining = isRaining;
        this.sensor = sensor;
    }
}

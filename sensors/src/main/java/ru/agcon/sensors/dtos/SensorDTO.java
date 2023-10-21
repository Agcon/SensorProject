package ru.agcon.sensors.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
public class SensorDTO {
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 letters")
    private String name;

    public SensorDTO(String name) {
        this.name = name;
    }

}

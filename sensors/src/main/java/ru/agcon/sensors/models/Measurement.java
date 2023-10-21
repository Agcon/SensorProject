package ru.agcon.sensors.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Table(name = "measurements")
@Data
@NoArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    @NotNull
    @DecimalMin(message = "Temperature should be in [-100:100]", value = "-100")
    @DecimalMax(message = "Temperature should be in [-100:100]", value = "100")
    private BigDecimal value;

    @Column(name = "raining")
    @NotNull
    private boolean raining;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;
}

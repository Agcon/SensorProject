package ru.agcon.sensors.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.agcon.sensors.models.Measurement;
import ru.agcon.sensors.repositories.SensorRepository;

@Component
public class MeasurementValidator implements Validator {
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementValidator(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if (!sensorRepository.findByName(measurement.getSensor().getName()).isPresent()){
            errors.rejectValue("name", "", "This name is already taken");
        }
    }
}

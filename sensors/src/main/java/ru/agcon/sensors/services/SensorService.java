package ru.agcon.sensors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agcon.sensors.models.Sensor;
import ru.agcon.sensors.repositories.SensorRepository;

import java.util.Optional;


@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public Sensor register(Sensor sensor){
        return sensorRepository.save(sensor);
    }

    public Sensor findByName(String name){
        Optional<Sensor> sensor = sensorRepository.findByName(name);
        return sensor.orElse(null);
    }
}

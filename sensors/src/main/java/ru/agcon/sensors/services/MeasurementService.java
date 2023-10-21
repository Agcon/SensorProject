package ru.agcon.sensors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agcon.sensors.models.Measurement;
import ru.agcon.sensors.repositories.MeasurementRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional(readOnly = false)
    public Measurement add(Measurement measurement){
        return measurementRepository.save(measurement);
    }

    public List<Measurement> getAllMeasurements(){
        return measurementRepository.findAll();
    }

    public int rainyCount(){
        List<Measurement> measurements = measurementRepository.findByRaining(true);
        return measurements.size();
    }
}

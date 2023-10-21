package ru.agcon.sensors.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.agcon.sensors.dtos.MeasurementDTO;
import ru.agcon.sensors.models.Measurement;
import ru.agcon.sensors.models.Sensor;
import ru.agcon.sensors.services.MeasurementService;
import ru.agcon.sensors.services.SensorService;
import ru.agcon.sensors.utils.MeasurementValidator;

import java.util.List;


@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;
    private final SensorService sensorService;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator, SensorService sensorService) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
        this.sensorService = sensorService;
    }

    @PostMapping("/add")
    public ResponseEntity<Measurement> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        Sensor sensor = sensorService.findByName(measurementDTO.getSensor().getName());
        if (sensor == null) return ResponseEntity.notFound().build();

        Measurement measurement = convertToMeasurement(measurementDTO);
        measurement.setSensor(sensor);

        measurementValidator.validate(measurement, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(measurementService.add(measurement));
    }

    @GetMapping("")
    public ResponseEntity<List<Measurement>> getAll(){
        return ResponseEntity.ok(measurementService.getAllMeasurements());
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Integer> rainyDaysCount(){
        return ResponseEntity.ok(measurementService.rainyCount());
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return this.modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return this.modelMapper.map(measurement, MeasurementDTO.class);
    }
}

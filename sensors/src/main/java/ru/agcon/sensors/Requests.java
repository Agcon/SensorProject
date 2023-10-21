package ru.agcon.sensors;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import ru.agcon.sensors.dtos.MeasurementDTO;
import ru.agcon.sensors.dtos.SensorDTO;

import java.math.BigDecimal;
import java.util.Random;

public class Requests {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        SensorDTO sensorDTO = new SensorDTO("WZ-004");
        HttpEntity<SensorDTO> sensorRequest = new HttpEntity<>(sensorDTO);

        String registrationUrl = "http://localhost:8080/sensors/registration";
        System.out.println(restTemplate.postForObject(registrationUrl, sensorRequest, String.class));

        String measurementsUrl = "http://localhost:8080/measurements";
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            BigDecimal randomTemperature = new BigDecimal("-100.0").add(new BigDecimal("200.0").multiply(new BigDecimal(Math.random())));
            boolean randomRain = random.nextBoolean();
            MeasurementDTO measurementDTO = new MeasurementDTO(randomTemperature, randomRain, sensorDTO);

            HttpEntity<MeasurementDTO> measurementRequest = new HttpEntity<>(measurementDTO);
            restTemplate.postForObject(measurementsUrl + "/add", measurementRequest, String.class);
        }

        System.out.println(restTemplate.getForObject(measurementsUrl, String.class));
    }
}


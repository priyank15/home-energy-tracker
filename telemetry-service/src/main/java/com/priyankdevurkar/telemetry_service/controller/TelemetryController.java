package com.priyankdevurkar.telemetry_service.controller;

import com.priyankdevurkar.telemetry_service.dto.EnergyUsageDto;
import com.priyankdevurkar.telemetry_service.service.TelemetryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/telemetry")
public class TelemetryController {

    private final TelemetryService telemetryService;

    public TelemetryController(TelemetryService telemetryService){
        this.telemetryService = telemetryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void ingestData(@RequestBody EnergyUsageDto usageDto){
        telemetryService.ingestEnergyUsage(usageDto);
    }

}

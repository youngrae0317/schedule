package org.example.schedule.Controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule.DTO.ScheduleRequestDto;
import org.example.schedule.DTO.ScheduleResponseDto;
import org.example.schedule.Service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.createSchedule(scheduleRequestDto);
    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedules(@RequestParam(required = false) String name) {
        return scheduleService.getSchedules(name);
    }


}

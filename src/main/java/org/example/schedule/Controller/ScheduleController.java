package org.example.schedule.Controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule.DTO.*;
import org.example.schedule.Repository.ScheduleRepository;
import org.example.schedule.Service.CommentService;
import org.example.schedule.Service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /** 일정 생성 (CREATE -> POST) **/
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(scheduleRequestDto));
    }

    /** 일정 전체 조회(READ -> GET) **/
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleListResponseDto>> getSchedules(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(scheduleService.getSchedules(name));
    }

    /** 일정 단건 조회 및 댓글 조회 (READ -> GET) **/
    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedulesByID(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    /** 일정 수정 (UPDATE -> PUT) **/
    @PutMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto, @PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, scheduleRequestDto));
    }

    /** 일정 삭제(DELETE) **/
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<String> deleteSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto, @PathVariable Long id) {
        scheduleService.deleteScheduleById(id, scheduleRequestDto);

        return ResponseEntity.ok("일정이 삭제되었습니다.");

    }

}

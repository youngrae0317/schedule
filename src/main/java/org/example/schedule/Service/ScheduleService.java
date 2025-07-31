package org.example.schedule.Service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.DTO.ScheduleRequestDto;
import org.example.schedule.DTO.ScheduleResponseDto;
import org.example.schedule.Entity.Schedule;
import org.example.schedule.Repository.ScheduleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        // 요청 DTO를 Entity로 변환
        Schedule schedule = new Schedule(dto.getTitle(), dto.getContents(), dto.getName(), dto.getPassword());

        // DB에 저장하고 객체로 반환
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // 반환 받은 saveSchedule 객체를 사용하여 응답 DTO 생성
        return new ScheduleResponseDto(saveSchedule);

    }

    public List<ScheduleResponseDto> getSchedules(String name) {
        List<Schedule> schedules;

        if (name == null) {
            schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();
        } else {
            schedules = scheduleRepository.findAllByNameOrderByModifiedAtDesc(name);
        }

        return schedules.stream().map(ScheduleResponseDto::new).collect(Collectors.toList());
    }

    public ScheduleResponseDto getScheduleById(Long id) {
        // ID를 기준으로 일정을 Optional 객체로 받는다.
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        // isPresent 메소드를 통해 존재하는지 확인하고 있으면 응답 Dto로 반환, 아니면 예외 처리.
        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();
            return new ScheduleResponseDto(schedule);
        } else {
            throw new IllegalArgumentException("Schedule with id " + id + " does not exist");
        }


    }

}

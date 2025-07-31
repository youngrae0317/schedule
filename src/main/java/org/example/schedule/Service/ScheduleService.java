package org.example.schedule.Service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.DTO.ScheduleRequestDto;
import org.example.schedule.DTO.ScheduleResponseDto;
import org.example.schedule.Entity.Schedule;
import org.example.schedule.Repository.ScheduleRepository;
import org.springframework.stereotype.Service;

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

}

package org.example.schedule.Service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.DTO.CommentRequestDto;
import org.example.schedule.DTO.CommentResponseDto;
import org.example.schedule.DTO.ScheduleRequestDto;
import org.example.schedule.DTO.ScheduleResponseDto;
import org.example.schedule.Entity.Comment;
import org.example.schedule.Entity.Schedule;
import org.example.schedule.Repository.CommentRepository;
import org.example.schedule.Repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

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

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        // ID로 일정을 Optional 객체로 받기
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        // 해당 id에 대한 일정이 있는지 확인, 없으면 예외 발생
        if (!optionalSchedule.isPresent()) {
            throw new IllegalArgumentException("Schedule with id " + id + " does not exist");
        }

        // 일정이 존재하면 객체를 가져옴
        Schedule schedule = optionalSchedule.get();

        // 비밀번호 확인 로직
        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        schedule.updateSchedule(requestDto);

        return new ScheduleResponseDto(schedule);
    }

    public void deleteScheduleById(Long id, ScheduleRequestDto requestDto) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if (!optionalSchedule.isPresent()) {
            throw new IllegalArgumentException("Schedule with id " + id + " does not exist");
        }

        Schedule schedule = optionalSchedule.get();

        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        scheduleRepository.delete(schedule);
    }

    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto) {
        // ID로 일정을 Optional 객체로 받기
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        // 일정이 있는지 확인
        if (!optionalSchedule.isPresent()) {
            throw new IllegalArgumentException("Schedule with id " + id + " does not exist");
        }

        // 일정 존재하면 optionalSchedule 객체를 가져와 schedule 에 할당
        Schedule schedule = optionalSchedule.get();

        if (schedule.getComments().size() >= 10) {
            throw new IllegalArgumentException("Comment is too large");
        }

        Comment comment = commentRepository.save(new Comment(requestDto, schedule));

        return new CommentResponseDto(comment);

    }
}

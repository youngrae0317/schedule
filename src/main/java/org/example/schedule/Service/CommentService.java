package org.example.schedule.Service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.DTO.CommentRequestDto;
import org.example.schedule.DTO.CommentResponseDto;
import org.example.schedule.Entity.Comment;
import org.example.schedule.Entity.Schedule;
import org.example.schedule.Repository.CommentRepository;
import org.example.schedule.Repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

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

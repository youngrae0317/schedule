package org.example.schedule.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.schedule.Entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private Long id;
    private String title;
    private String contents;
    private String name;
    private String createdAt;
    private String modifiedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.name = schedule.getName();
        this.createdAt = schedule.getCreatedAt().toString();
        this.modifiedAt = schedule.getModifiedAt().toString();

    }

    public ScheduleResponseDto(Long id, String title, String contents, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
    }
}

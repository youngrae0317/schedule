package org.example.schedule.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.schedule.Entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

/** 전체 일정 조회 시 댓글 리스트가 없는 dto **/
@Getter
public class ScheduleListResponseDto {

    private Long id;
    private String title;
    private String contents;
    private String name;
    private String createdAt;
    private String modifiedAt;// 1. 댓글 리스트 필드 추가

    public ScheduleListResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.name = schedule.getName();
        this.createdAt = schedule.getCreatedAt().toString();
        this.modifiedAt = schedule.getModifiedAt().toString();
    }

}

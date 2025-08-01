package org.example.schedule.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule.DTO.ScheduleRequestDto;
import org.example.schedule.Entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String name;

    public Schedule(String title, String contents, String name) {
        this.title = title;
        this.contents = contents;
        this.name = name;
    }

    public Schedule(String title, String contents, String name, String password) {
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.password = password;
    }

    public void updateSchedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
    }
}

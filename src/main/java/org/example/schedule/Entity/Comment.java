package org.example.schedule.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule.DTO.CommentRequestDto;

@Getter
@NoArgsConstructor
@Entity

public class Comment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;


    public Comment(CommentRequestDto requestDto, Schedule schedule) {
        this.contents = requestDto.getContents();
        this.name = requestDto.getName();
        this.password = requestDto.getPassword();
        this.schedule = schedule;
    }

}

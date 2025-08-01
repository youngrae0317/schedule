package org.example.schedule.DTO;

import lombok.Getter;
import org.example.schedule.Entity.Comment;

@Getter
public class CommentResponseDto {
    private Long id;
    private String contents;
    private String name;
    private String createdAt;
    private String modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.name = comment.getName();
        this.createdAt = comment.getCreatedAt().toString();
        this.modifiedAt = comment.getModifiedAt().toString();
    }
}

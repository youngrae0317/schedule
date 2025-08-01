package org.example.schedule.Controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule.DTO.CommentRequestDto;
import org.example.schedule.DTO.CommentResponseDto;
import org.example.schedule.Service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class CommentController {
    private final CommentService commentService;

    @PostMapping("/schedules/{id}/comments")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.createComment(id, commentRequestDto);
    }
}

package org.example.schedule.Repository;

import org.example.schedule.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // 전체 일정 조회 -> 작성자명이 없을 경우에 전체 목록 조회
    List<Schedule> findAllByOrderByModifiedAtDesc();

    // 작성자명이 있을 경우 전체 일정 조회
    List<Schedule> findAllByNameOrderByModifiedAtDesc(String name);
}

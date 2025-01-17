package org.example.expert.domain.todo.repository;

import java.time.LocalDateTime;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {

    @Query("SELECT t FROM Todo t " +
        "WHERE (:weather IS NULL OR t.weather = :weather) " +
        "AND (:startTime IS NULL OR t.createdAt >= :startTime) " +
        "AND (:endTime IS NULL OR t.createdAt <= :endTime)")
    Page<Todo> findTodosByConditions(
        @Param("weather") String weather,
        @Param("startTime") LocalDateTime startTime,
        @Param("endTime") LocalDateTime endTime,
        Pageable pageable
    );
}

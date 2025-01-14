package org.example.expert.domain.todo.repository;

import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.dto.response.TodoSearchResponse;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId) {
        QTodo todo = QTodo.todo;

        Todo result = queryFactory
            .selectFrom(todo)
            .leftJoin(todo.user).fetchJoin()
            .where(todo.id.eq(todoId))
            .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public Page<TodoSearchResponse> findTodosBySearch(String title, String nickname, LocalDateTime startTime, LocalDateTime endTime, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if (title != null && !title.isEmpty()) {
            builder.and(todo.title.containsIgnoreCase(title));
        }

        if (nickname != null && !nickname.isEmpty()) {
            builder.and(todo.user.nickname.containsIgnoreCase(nickname));
        }

        if (startTime != null) {
            builder.and(todo.createdAt.goe(startTime));
        }

        if (endTime != null) {
            builder.and(todo.createdAt.loe(endTime));
        }

        List<TodoSearchResponse> results = queryFactory
            .select(Projections.constructor(
                TodoSearchResponse.class,
                todo.title,
                todo.managers.size(),
                todo.comments.size()
            ))
            .from(todo)
            .leftJoin(todo.user, user)
            .where(builder)
            .orderBy(todo.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(todo.count())
            .from(todo)
            .where(builder)
            .fetchOne();

        long total = (count != null) ? count : 0;

        return PageableExecutionUtils.getPage(results, pageable, () -> total);
    }
}

package com.korit.mybatis_study.mapper;

import com.korit.mybatis_study.entity.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TodoMapper {
    int addTodo(Todo todo);
    Optional<Todo> findTodoByTitle(String title);
    Optional<List<Todo>> findTodoByKeyword(String keyword);
}

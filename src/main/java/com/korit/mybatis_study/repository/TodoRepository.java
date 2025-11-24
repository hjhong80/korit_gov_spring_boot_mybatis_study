package com.korit.mybatis_study.repository;

import com.korit.mybatis_study.entity.Board;
import com.korit.mybatis_study.entity.Todo;
import com.korit.mybatis_study.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository {
    @Autowired
    TodoMapper todoMapper;

    public int addTodo(Todo todo) {
        return todoMapper.addTodo(todo);
    }

    public Optional<Todo> findTodoByTitle(String title) {
        return todoMapper.findTodoByTitle(title);
    }

    public Optional<List<Todo>> findTodoByKeyword(String keyword) {
        return todoMapper.findTodoByKeyword(keyword);
    }
}

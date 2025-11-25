package com.korit.mybatis_study.repository;

import com.korit.mybatis_study.entity.Todo;
import com.korit.mybatis_study.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository {
    @Autowired
    TodoMapper todoMapper;

    public Optional<Todo> addTodo(Todo todo) {
        try {
            todoMapper.addTodo(todo);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        }
        return Optional.of(todo);
    }

    public Optional<Todo> findTodoByTitle(String title) {
        return todoMapper.findTodoByTitle(title);
    }

    public Optional<List<Todo>> findTodoByKeyword(String keyword) {
        System.out.println("repository");
        return todoMapper.findTodoByKeyword(keyword);
    }

    public List<Todo> findAll() {
        return todoMapper.findAll();
    }

    public Optional<Todo> findTodoByTodoId (Integer todoId) {
        return todoMapper.findTodoByTodoId(todoId);
    }

    public int editTodo(Todo todo) {
        return todoMapper.editTodo(todo);
    }

    public int delTodo(Integer todoId) {
        return todoMapper.delTodo(todoId);
    }
}

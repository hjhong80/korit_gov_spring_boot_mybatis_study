package com.korit.mybatis_study.service;

import com.korit.mybatis_study.dto.ApiRespDto;
import com.korit.mybatis_study.dto.TodoAddReqDto;
import com.korit.mybatis_study.entity.Board;
import com.korit.mybatis_study.entity.Todo;
import com.korit.mybatis_study.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public ApiRespDto<?> addTodo(TodoAddReqDto todoAddReqDto) {
        System.out.println(todoAddReqDto);
        Optional<Todo> todo = todoRepository.findTodoByTitle(todoAddReqDto.getTitle());
        if (todo.isPresent()) {
            return ApiRespDto.<String>builder()
                    .status("failed")
                    .message("동일한 타이틀이 존재합니다.")
                    .data(todoAddReqDto.getTitle())
                    .build();
        }

        System.out.println("22222");
        Integer result = todoRepository.addTodo(todoAddReqDto.toEntity());
        if (result != 1) {
            return ApiRespDto.<String>builder()
                    .status("failed")
                    .message("Todo 추가에 실패하였습니다.")
                    .data(todoAddReqDto.getTitle())
                    .build();
        }
        return ApiRespDto.<TodoAddReqDto>builder()
                .status("success")
                .message("Todo 추가에 성공하였습니다.")
                .data(todoAddReqDto)
                .build();
    }

    public ApiRespDto<?> getTodoByKeyword(String keyword) {
        Optional<List<Todo>> foundTodo = todoRepository.findTodoByKeyword(keyword);
        if (foundTodo.isEmpty()) {
            ApiRespDto.<String>
        }
        return null;
    }
}

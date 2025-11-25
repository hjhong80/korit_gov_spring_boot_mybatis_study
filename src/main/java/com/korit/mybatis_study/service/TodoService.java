package com.korit.mybatis_study.service;

import com.korit.mybatis_study.dto.ApiRespDto;
import com.korit.mybatis_study.dto.EditBoardReqDto;
import com.korit.mybatis_study.dto.TodoAddReqDto;
import com.korit.mybatis_study.dto.TodoEditReqDto;
import com.korit.mybatis_study.entity.Board;
import com.korit.mybatis_study.entity.Todo;
import com.korit.mybatis_study.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
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

        Optional<Todo> addTodo = todoRepository.addTodo(todoAddReqDto.toEntity());
        if (addTodo.isEmpty()) {
            return ApiRespDto.<String>builder()
                    .status("failed")
                    .message("Todo 추가에 실패하였습니다.")
                    .data(todoAddReqDto.getTitle())
                    .build();
        }
        return ApiRespDto.<Todo>builder()
                .status("success")
                .message("Todo 추가에 성공하였습니다.")
                .data(addTodo.get())
                .build();
    }

    public ApiRespDto<?> getTodoByKeyword(String keyword) {
        System.out.println("service");
        Optional<List<Todo>> foundTodo = todoRepository.findTodoByKeyword(keyword);
        System.out.println("service_repository");
        if (foundTodo.isEmpty()) {
            ApiRespDto.<String>builder()
                    .status("failed")
                    .message("조회에 실패하였습니다.")
                    .data(keyword)
                    .build();
        } else {
            System.out.println(foundTodo.get());
            ApiRespDto.<List<Todo>>builder()
                    .status("success")
                    .message("조회에 성공하였습니다.")
                    .data(foundTodo.get())
                    .build();
        }
        return null;
    }

    public ApiRespDto<?> getAll() {
        return ApiRespDto.<List<Todo>>builder()
                .status("success")
                .message("조회에 성공하였습니다.")
                .data(todoRepository.findAll())
                .build();
    }

    public ApiRespDto<?> getTodoByTodoId(Integer todoId) {
        if (todoRepository.findTodoByTodoId(todoId).isEmpty()) {
            return ApiRespDto.<Integer>builder()
                    .status("failed")
                    .message("조회에 실패하였습니다.")
                    .data(todoId)
                    .build();
        }
        return ApiRespDto.<Todo>builder()
                .status("success")
                .message("조회에 성공하였습니다.")
                .data(todoRepository.findTodoByTodoId(todoId).get())
                .build();
    }

    public ApiRespDto<?> editTodo(TodoEditReqDto todoEditReqDto) {
        Optional<Todo> foundTodo = todoRepository.findTodoByTodoId(todoEditReqDto.getTodoId());
        if (foundTodo.isEmpty()) {
            return ApiRespDto.<Integer>builder()
                    .status("failed")
                    .message("조회에 실패하였습니다.")
                    .data(todoEditReqDto.getTodoId())
                    .build();
        }
        int result = todoRepository.editTodo(todoEditReqDto.toEntity());
        if (result == 1) {
            return ApiRespDto.<Integer>builder()
                    .status("success")
                    .message("수정에 성공하였습니다.")
                    .data(todoEditReqDto.getTodoId())
                    .build();
        } else {
            return ApiRespDto.<Integer>builder()
                    .status("failed")
                    .message("수정에 실패하였습니다.")
                    .data(todoEditReqDto.getTodoId())
                    .build();
        }
    }

    public ApiRespDto<?> delTodo(Integer todoId) {
        Optional<Todo> foundTodo = todoRepository.findTodoByTodoId(todoId);
        if (foundTodo.isEmpty()) {
            return ApiRespDto.<Integer>builder()
                    .status("failed")
                    .message("조회에 실패하였습니다.")
                    .data(todoId)
                    .build();
        }
        int result = todoRepository.delTodo(todoId);
        if (result == 1) {
            return ApiRespDto.<Integer>builder()
                    .status("success")
                    .message("삭제에 성공하였습니다.")
                    .data(todoId)
                    .build();
        } else {
            return ApiRespDto.<Integer>builder()
                    .status("failed")
                    .message("삭제에 실패하였습니다.")
                    .data(todoId)
                    .build();
        }
    }

}

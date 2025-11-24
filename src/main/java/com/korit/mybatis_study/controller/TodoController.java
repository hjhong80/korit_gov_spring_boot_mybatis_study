package com.korit.mybatis_study.controller;

import com.korit.mybatis_study.dto.TodoAddReqDto;
import com.korit.mybatis_study.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody TodoAddReqDto todoAddReqDto) {
        return ResponseEntity.ok(todoService.addTodo(todoAddReqDto));
    }

    @GetMapping("/{keyword}")
    public ResponseEntity<?> getTodoByKeyword(@RequestParam String title) {

    }
}

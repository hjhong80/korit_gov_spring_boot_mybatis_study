package com.korit.mybatis_study.controller;

import com.korit.mybatis_study.dto.TodoAddReqDto;
import com.korit.mybatis_study.dto.TodoEditReqDto;
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

//    @GetMapping("/search/{keyword}")
//    public ResponseEntity<?> getTodoByKeyword(@PathVariable String keyword) {
//        System.out.println("controller");
//        return ResponseEntity.ok(todoService.getTodoByKeyword(keyword));
//    }

    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodoByTodoId(@PathVariable Integer todoId) {
        System.out.println("controller");
        return ResponseEntity.ok(todoService.getTodoByTodoId(todoId));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(todoService.getAll());
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editTodo(@RequestBody TodoEditReqDto todoEditReqDto) {
        return ResponseEntity.ok(todoService.editTodo(todoEditReqDto));
    }

    @PostMapping("/del")
    public ResponseEntity<?> delTodo(@RequestParam Integer todoId) {
        return ResponseEntity.ok(todoService.delTodo(todoId));
    }
}

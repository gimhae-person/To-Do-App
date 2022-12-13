package com.ToDoApplication.TodoApp.controller;

import com.ToDoApplication.TodoApp.dto.TodoDto;
import com.ToDoApplication.TodoApp.entity.Todos;
import com.ToDoApplication.TodoApp.mapper.TodoMapper;
import com.ToDoApplication.TodoApp.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin
@RestController
@Validated
@Slf4j
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper mapper;

    public TodoController(TodoService todoService, TodoMapper mapper) {
        this.todoService = todoService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoDto.Post requestBody){
        Todos todo = mapper.todoPostToTodo(requestBody);

        Todos createTodo = todoService.createTodo(todo);
        TodoDto.Response response = mapper.todoToTodoResponse(createTodo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity updateTodo(@PathVariable("todoId") @Positive long todoId,
                                     @Valid @RequestBody TodoDto.Patch requestBody){
        requestBody.setTodoId(todoId);

        Todos todo =
                todoService.updateTodo(mapper.todoPatchToTodo(requestBody));

        return new ResponseEntity<>((mapper.todoToTodoResponse(todo)), HttpStatus.OK);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity getTodo(@PathVariable("todoId") @Positive long todoId){
        Todos todo = todoService.findTodo(todoId);
        return new ResponseEntity<>((mapper.todoToTodoResponse(todo)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodos(){
        List<Todos> todos = todoService.findTodos();
        return new ResponseEntity<>(mapper.todosToTodoResponses(todos), HttpStatus.OK);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity deleteTodo(@PathVariable("todoId") @Positive long todoId){
        todoService.deleteTodo(todoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

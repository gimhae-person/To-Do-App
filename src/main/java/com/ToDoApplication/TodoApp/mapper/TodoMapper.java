package com.ToDoApplication.TodoApp.mapper;

import com.ToDoApplication.TodoApp.dto.TodoDto;
import com.ToDoApplication.TodoApp.entity.Todos;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todos todoPostToTodo(TodoDto.Post requestBody);
    Todos todoPatchToTodo(TodoDto.Patch requestBody);
    TodoDto.Response todoToTodoResponse(Todos todos);
    List<TodoDto.Response> todosToTodoResponses(List<Todos> todos);
}

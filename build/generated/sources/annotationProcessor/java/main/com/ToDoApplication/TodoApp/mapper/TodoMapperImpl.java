package com.ToDoApplication.TodoApp.mapper;

import com.ToDoApplication.TodoApp.dto.TodoDto;
import com.ToDoApplication.TodoApp.entity.Todos;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-12T17:21:25+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class TodoMapperImpl implements TodoMapper {

    @Override
    public Todos todoPostToTodo(TodoDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Todos todos = new Todos();

        todos.setTitle( requestBody.getTitle() );
        todos.setTodo_order( requestBody.getTodo_order() );

        return todos;
    }

    @Override
    public Todos todoPatchToTodo(TodoDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Todos todos = new Todos();

        todos.setTodoId( requestBody.getTodoId() );
        todos.setTitle( requestBody.getTitle() );
        todos.setTodo_order( requestBody.getTodo_order() );
        todos.setCompleted( requestBody.isCompleted() );

        return todos;
    }

    @Override
    public TodoDto.Response todoToTodoResponse(Todos todos) {
        if ( todos == null ) {
            return null;
        }

        long todoId = 0L;
        String title = null;
        int todo_order = 0;
        boolean completed = false;

        todoId = todos.getTodoId();
        title = todos.getTitle();
        todo_order = todos.getTodo_order();
        completed = todos.isCompleted();

        TodoDto.Response response = new TodoDto.Response( todoId, title, todo_order, completed );

        return response;
    }

    @Override
    public List<TodoDto.Response> todosToTodoResponses(List<Todos> todos) {
        if ( todos == null ) {
            return null;
        }

        List<TodoDto.Response> list = new ArrayList<TodoDto.Response>( todos.size() );
        for ( Todos todos1 : todos ) {
            list.add( todoToTodoResponse( todos1 ) );
        }

        return list;
    }
}

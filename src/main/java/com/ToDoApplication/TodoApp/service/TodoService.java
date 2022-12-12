package com.ToDoApplication.TodoApp.service;

import com.ToDoApplication.TodoApp.entity.Todos;
import com.ToDoApplication.TodoApp.exception.BusinessLogicException;
import com.ToDoApplication.TodoApp.exception.ExceptionCode;
import com.ToDoApplication.TodoApp.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todos createTodo(Todos todo) {
        Todos savedTodo = todoRepository.save(todo);

        return savedTodo;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE) // 이거 뭔지 공부하셈
    public Todos updateTodo(Todos todo) {
        Todos findTodo = findVerifiedTodo(todo.getTodoId());

        Optional.ofNullable(todo.getTitle())
                .ifPresent(findTodo::setTitle);
        Optional.ofNullable(todo.getTodo_order())
                .ifPresent(findTodo::setTodo_order);
        Optional.ofNullable(todo.isCompleted())
                .ifPresent(findTodo::setCompleted);

        findTodo.setTitle(todo.getTitle());

        return todoRepository.save(findTodo);
    }

    @Transactional(readOnly = true)
    public Todos findTodo(long todoId) {
        return findVerifiedTodo(todoId);
    }

    public Page<Todos> findTodos(int page, int size) {
        return todoRepository.findAll(PageRequest.of(page, size, Sort.by("todoId").descending()));
    }

    public void deleteTodo(long todoId) {
        Todos findTodo = findVerifiedTodo(todoId);

        todoRepository.delete(findTodo);
    }

    @Transactional(readOnly = true)
    public Todos findVerifiedTodo(long todoId) {
        Optional<Todos> optionalTodos = todoRepository.findByTodoId(todoId);
        Todos findTodo = optionalTodos.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.TO_DO_NOT_FOUND));
        return findTodo;
    }
    private void verifyExistsTitle(String title) {
        Optional<Todos> todo = todoRepository.findByTitle(title);
        if (todo.isPresent())
            throw new BusinessLogicException(ExceptionCode.TO_DO_TITLE_EXIST);
    }
}

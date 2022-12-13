package com.ToDoApplication.TodoApp.service;

import com.ToDoApplication.TodoApp.entity.Todos;
import com.ToDoApplication.TodoApp.exception.BusinessLogicException;
import com.ToDoApplication.TodoApp.exception.ExceptionCode;
import com.ToDoApplication.TodoApp.repository.TodoRepository;
import com.ToDoApplication.TodoApp.utils.CustomBeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final CustomBeanUtils<Todos> beanUtils;

    public TodoService(TodoRepository todoRepository, CustomBeanUtils<Todos> beanUtils) {
        this.todoRepository = todoRepository;
        this.beanUtils = beanUtils;
    }

    public Todos createTodo(Todos todo) {
        Todos savedTodo = todoRepository.save(todo);

        return savedTodo;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE) // 이거 뭔지 공부하셈
    public Todos updateTodo(Todos todo) {
        Todos findTodo = findVerifiedTodo(todo.getTodoId());

        Todos updateingTodo = beanUtils.copyNonNullProperties(todo, findTodo);

        return todoRepository.save(findTodo);
    }

    @Transactional(readOnly = true)
    public Todos findTodo(long todoId) {
        return findVerifiedTodo(todoId);
    }

    public List<Todos> findTodos() {
        return todoRepository.findAll();
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

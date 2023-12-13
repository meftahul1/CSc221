package com.example.demo.Controller;

import com.example.demo.service.TaskService;
import com.example.demo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String index() throws IOException {
        return "Welcome to the API!";
    }


    @PostMapping("/add")
    public Task create(@RequestParam("id") Long id, @RequestParam("description") String description, @RequestParam("completed") Boolean completed) throws IOException {
        Task newTask = new Task(id, description, completed);
        return taskService.createOrUpdateTask(newTask);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return new ResponseEntity<>("Task with id " + id + " deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    //return json of all tasks
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/find")
    //return json of task with given id
    public ResponseEntity<?> getTaskById(@RequestParam("id") Long id) {
        Task task = taskService.getTaskById(id).orElse(null);
        if (task == null) {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
    }
}

package taskcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskcreator.entites.Task;
import taskcreator.entites.UserAccount;
import taskcreator.service.TaskService;
import taskcreator.service.UserAccountService;
import taskcreator.util.URL;

import java.io.UnsupportedEncodingException;

@RestController
public class UserTaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    UserAccountService userAccountService;

    @RequestMapping(value = "/taskcreator/useraccounts/{id}/tasks", method = RequestMethod.PUT)
    public ResponseEntity<?> createTask(@PathVariable("id") String id, @RequestBody Task task) {
        UserAccount existingUser = userAccountService.findById(id);

        if (existingUser.equals(null)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email Account no exists");
        }
        taskService.updateTask(existingUser,task);
        return ResponseEntity.ok().body("User Account update successfully");
    }

    @GetMapping("/taskcreator/useraccounts/{id}/tasks")
    public Page<Task> findAll(@PathVariable("id") String id, @RequestParam(defaultValue = "0") int pageNumber,
                              @RequestParam(defaultValue = "10") int pageSize) {
        UserAccount existingUser = userAccountService.findById(id);

        if (existingUser.equals(null)) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email Account no exists");
        }
        return taskService.findAllTasks(pageNumber, pageSize,existingUser);
    }

    @GetMapping("/taskcreator/useraccounts/{id}/tasks/findOneTask")
    public ResponseEntity<?> findByName(@PathVariable("id") String id,@RequestParam(value = "nameTask", defaultValue = "") String name) throws UnsupportedEncodingException {
        name = URL.decodeParam(name);

        UserAccount existingUser = userAccountService.findById(id);

        if (existingUser.equals(null)) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email Account no exists");
        }

     String taskName = taskService.findTask(existingUser,name);

        return ResponseEntity.ok().body(taskName);
    }
}

package taskcreator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import taskcreator.entites.Task;
import taskcreator.entites.UserAccount;
import taskcreator.repository.TaskRepository;
import taskcreator.repository.UserAccountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    UserAccountRepository userAccountRepository;



    public void updateTask(UserAccount existingUser, Task task) {
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(existingUser.getTasks());
        tasks.add(task);
        existingUser.setTasks(tasks);
        userAccountRepository.save(existingUser);
    }


    public Page<Task> findAllTasks(int pageNumber, int pageSize, UserAccount existingUserAccount) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Task> page = new PageImpl<>(existingUserAccount.getTasks(), pageable, existingUserAccount.getTasks().size());

        return page;
    }


    public String findTask(UserAccount existingUser, String name) {

        for(Task task : existingUser.getTasks()){
            if(task.getName()==name){
                return task.getName();
            }
        }

        return null;
    }



}

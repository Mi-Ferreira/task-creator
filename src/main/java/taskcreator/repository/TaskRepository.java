package taskcreator.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import taskcreator.entites.Task;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    @Query("{'tasks':{$regex:?0,$options:'i'}}")
    List<Task> searchTitleNameTask(String text);


    Page<Task> findAll(Pageable pageable);
}

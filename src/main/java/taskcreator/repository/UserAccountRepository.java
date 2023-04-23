package taskcreator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import taskcreator.entites.UserAccount;

@Repository
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {


}
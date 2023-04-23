package taskcreator.entites;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@EqualsAndHashCode
@Document(collection = "user_accounts")
public class UserAccount implements Serializable {

    @Id
    private String email;
    private String name;
    private String lastname;
    private String password;
    private List<Task> tasks = new ArrayList<>();

}

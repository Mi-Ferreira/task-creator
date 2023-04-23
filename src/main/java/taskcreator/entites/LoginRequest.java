package taskcreator.entites;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Getter
@EqualsAndHashCode
@Component
public class LoginRequest implements Serializable {

    private String email;
    private String password;

}

package taskcreator.entites;

import lombok.*;
import org.springframework.stereotype.Component;


import java.io.Serializable;

@Data
@Getter
@Component
public class Task implements Serializable {

    private String name;
    private String description;
    private boolean done;
}

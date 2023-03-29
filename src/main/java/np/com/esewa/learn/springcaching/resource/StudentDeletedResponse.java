package np.com.esewa.learn.springcaching.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StudentDeletedResponse implements Serializable {
    @JsonIgnoreProperties
    private Long id;
    private String message;
}

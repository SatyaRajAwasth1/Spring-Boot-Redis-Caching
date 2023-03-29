package np.com.esewa.learn.springcaching.resource;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StudentResponse implements Serializable {
    private String name;

    private Integer age;
}

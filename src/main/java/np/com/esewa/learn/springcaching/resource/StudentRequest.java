package np.com.esewa.learn.springcaching.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentRequest implements Serializable {

    private String name;

    private Integer age;

}

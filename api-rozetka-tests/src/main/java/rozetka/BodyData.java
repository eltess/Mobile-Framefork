package rozetka;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BodyData {

    private String name;
    private Integer age;
    private String hobby;
}

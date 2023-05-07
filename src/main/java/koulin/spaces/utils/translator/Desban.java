package koulin.spaces.utils.translator;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode
public class Desban {
    private Long id;
    private String typeUser;
    private List<String> desban;

}

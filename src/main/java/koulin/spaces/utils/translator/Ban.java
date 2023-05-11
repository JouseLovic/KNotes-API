package koulin.spaces.utils.translator;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode
public class Ban {
    private Long id;
    private List<String> ban;
}

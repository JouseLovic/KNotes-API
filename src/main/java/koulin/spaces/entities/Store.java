package koulin.spaces.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long id_template;

    @Transient
    private boolean existTemplate;

    public Store(Long id_template) {
        this.id_template = id_template;
        this.existTemplate = false;
    }


    public Store(){}

}

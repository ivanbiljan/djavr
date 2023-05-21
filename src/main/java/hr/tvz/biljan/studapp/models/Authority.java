package hr.tvz.biljan.studapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authority")
@NoArgsConstructor
public final class Authority {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;
}

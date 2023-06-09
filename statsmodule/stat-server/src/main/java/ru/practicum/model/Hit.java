package ru.practicum.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "HITS")
public class Hit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "APP", nullable = false, length = 50)
    private String app;

    @Column(name = "URI", nullable = false, length = 50)
    private String uri;

    @Column(name = "IP", nullable = false, length = 19)
    private String ip;

    @Column(name = "CREATED", nullable = false)
    private LocalDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() || getId() == null) {
            return false;
        }

        Hit hit = (Hit) o;

        return getId().equals(hit.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}

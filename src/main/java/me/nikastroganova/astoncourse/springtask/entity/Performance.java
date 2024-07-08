package me.nikastroganova.astoncourse.springtask.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "performances")
@Data
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "actors_performances",
            joinColumns = @JoinColumn(name = "performance_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;

    public Performance() {}

    public Performance(Integer id, String name, String description, Hall hall) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hall = hall;
    }

    public boolean addActorToPerformance(Actor actor) {
        if (actors == null) {
            actors = new ArrayList<>();
        }
        return actors.add(actor);
    }

    public boolean removeActorFromPerformance(Actor actor) {
        if (actors != null) {
            return actors.remove(actor);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Performance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hall=" + hall +
                '}';
    }
}

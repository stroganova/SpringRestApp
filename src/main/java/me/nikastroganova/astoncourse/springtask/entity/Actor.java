package me.nikastroganova.astoncourse.springtask.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actors")
@Data
public class Actor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;

    private String lastname;

    @Column(name = "phone")
    private String phoneNumber;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "actors_performances",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "performance_id")
    )
    private List<Performance> performances;

    public Actor() {
    }

    public Actor(Integer id, String firstname, String lastname, String phoneNumber) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public boolean addPerformanceToActor(Performance performance) {
        if (performances == null) {
            performances = new ArrayList<>();
        }
        return performances.add(performance);
    }

    public boolean removePerformanceFromActor(Performance performance) {
        if (performances != null) {
            return performances.remove(performance);
        }
        return false;
    }
}

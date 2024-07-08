package me.nikastroganova.astoncourse.springtask.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "halls")
@Data
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hall_name")
    private String name;

    @Column(name = "hall_address")
    private String address;

    @Column(name = "hall_phone")
    private String phoneNumber;

    public Hall() {
    }

    public Hall(Integer id, String name, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}


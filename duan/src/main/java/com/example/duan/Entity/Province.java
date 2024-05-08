package com.example.duan.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "provice")
@Data
public class Province implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "province")
    @JsonIgnore
    private Set<District> districts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "province")
    @JsonIgnore
    private Set<User> users;
}

package com.example.duan.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "district")
@Data
public class District implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    @JsonIgnore
    private Province province;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
    @JsonIgnore
    private Set<Ward> wards;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
    @JsonIgnore
    private Set<User> users;
}

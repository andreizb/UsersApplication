package com.test.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "localities")
@Getter
@Setter
public class Locality {

    @Id
    private Long id;

    private String localityName;

    @JoinColumn(name = "countyCode", referencedColumnName = "countyCode")
    @ManyToOne
    @JsonBackReference
    private County county;

    @OneToMany(mappedBy = "locality")
    private Set<User> users = new HashSet<>();

}

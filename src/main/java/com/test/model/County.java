package com.test.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "counties")
@NoArgsConstructor
@Getter
@Setter
public class County implements Serializable {

    @Id
    private Long idCounty;

    private String countyName;

    @NaturalId
    private String countyCode;

    @OneToMany(mappedBy = "county")
    @JsonManagedReference
    private Set<Locality> localities = new HashSet<>();

    @OneToMany(mappedBy = "county")
    private Set<User> users = new HashSet<>();

    public County(Long idCounty, String countyName, String countyCode) {
        this.idCounty = idCounty;
        this.countyName = countyName;
        this.countyCode = countyCode;
    }

}

package com.test.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "users_data")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    @ManyToOne
    @JoinColumn(name = "countyId", referencedColumnName = "idCounty")
    private County county;

    @ManyToOne
    private Locality locality;

    public User(String fullName, String email, County county, Locality locality) {
        this.fullName = fullName;
        this.email = email;
        this.county = county;
        this.locality = locality;
    }

}

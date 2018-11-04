package com.andre.vaulttest.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "VAULT_REGION")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGION_ID")
    private Long id;
    @Column(name = "REGION_NAME")
    private String name;

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<Country> countries;

    public Region() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}

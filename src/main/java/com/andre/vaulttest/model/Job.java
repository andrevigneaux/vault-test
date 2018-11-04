package com.andre.vaulttest.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "VAULT_JOB")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_ID")
    private Long id;
    @Column(name = "JOB_TITLE")
    private String title;
    @Column(name = "MIN_SALARY")
    private Double minSalary;
    @Column(name = "MAX_SALARY")
    private Double maxSalary;

    @OneToMany(mappedBy = "job")
    private List<JobHistory> jobsHistory;
    @OneToMany(mappedBy = "job")
    private List<Employee> employees;

    public Job() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Double minSalary) {
        this.minSalary = minSalary;
    }

    public Double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Double maxSalary) {
        this.maxSalary = maxSalary;
    }
}

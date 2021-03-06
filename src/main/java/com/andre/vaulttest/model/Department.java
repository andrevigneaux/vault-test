package com.andre.vaulttest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "VAULT_DEPARTMENT")
@JsonIgnoreProperties({"employees", "jobsHistory"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID")
    private Long id;
    @Column(name = "DEPARTMENT_NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    @JsonIgnoreProperties({"department", "jobsHistory"})
    private Employee manager;
    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    @NotNull(message = "Location Id must not be null.")
    private Location location;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees;
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<JobHistory> jobsHistory;

    public Department() {}

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

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<JobHistory> getJobsHistory() {
        return jobsHistory;
    }

    public void setJobsHistory(List<JobHistory> jobsHistory) {
        this.jobsHistory = jobsHistory;
    }
}

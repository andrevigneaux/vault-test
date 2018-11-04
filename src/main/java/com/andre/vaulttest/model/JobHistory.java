package com.andre.vaulttest.model;

import com.andre.vaulttest.model.pk.JobHistoryPK;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VAULT_JOB_HISTORY")
public class JobHistory {
    @EmbeddedId
    private JobHistoryPK id;
    @Column(name = "END_DATE")
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "JOB_ID")
    private Job job;
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    public JobHistory() {}

    public JobHistoryPK getId() {
        return id;
    }

    public void setId(JobHistoryPK id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

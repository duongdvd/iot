package com.future.iot.model;

import javax.persistence.*;

@Entity
@Table(name = "role", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Role {



    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, updatable = false, insertable = false)
    private Employee employee;

    public Role(int employeeId){
        this.employeeId = employeeId;
        this.role = "ROLE_USER";
    }

    public Role(){

    }
    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

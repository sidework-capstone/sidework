package com.codeup.sidework.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "department")
public class Department {

    //id
    @Id @GeneratedValue
    private long id;

    //department name
    // FOH / BOH / OPERATORS
    @Column(nullable = false)
    private String departmentName;


    // this creates a department_division pivot table
    // connected to Division class
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Division> divisions;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }



    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }

    public List<Division> getDivisions() {
        return divisions;
    }




    public Department(List<Division> divisions) {
        this.divisions = divisions;
    }


    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department() {

    }
}

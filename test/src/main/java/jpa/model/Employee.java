package jpa.model;

import javax.persistence.*;

@Entity
@Table(name= "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eid;

    private String ename;

    private double salary;

    private String deg;

    public Employee() {
    }

    public Employee(String ename, double salary, String deg) {
        this.ename = ename;
        this.salary = salary;
        this.deg = deg;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public int getEid() {

        return eid;
    }

    public String getEname() {
        return ename;
    }

    public double getSalary() {
        return salary;
    }

    public String getDeg() {
        return deg;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                ", salary=" + salary +
                ", deg='" + deg + '\'' +
                '}';
    }
}


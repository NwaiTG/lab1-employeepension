package model;

import java.time.LocalDate;

public class Employee {
    public long employeeId;
    public String firstName;
    public String lastName;
    public double salary;
    public LocalDate employmentDate;
    public PensionPlan pensionPlan;

    public Employee(long employeeId, String firstName, String lastName, LocalDate employmentDate, double yearlySalary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.salary = yearlySalary;
    }

    public Boolean isEnrolled(){
        return pensionPlan != null;
    }

    public int getYearsOfEmployment(LocalDate date){
        return date.getYear() - employmentDate.getYear();
    }
}

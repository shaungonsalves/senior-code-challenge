package com.mindex.challenge.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Compensation {
    private EmployeeBase employee;
    private BigDecimal salary;
    private LocalDate effectiveDate;

    public Compensation() {
    }

    public EmployeeBase getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeBase employee) {
        this.employee = employee;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    public String getEffectiveDate() {
        return effectiveDate.format(formatter);
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = LocalDate.parse(effectiveDate,formatter);
    }

    @Override
    public String toString() {
        return "Compensation{" +
                "employee=" + employee +
                ", salary=" + salary +
                ", effectiveDate=" + effectiveDate +
                '}';
    }
}

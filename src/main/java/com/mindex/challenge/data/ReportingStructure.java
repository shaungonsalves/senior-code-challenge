package com.mindex.challenge.data;

public class ReportingStructure {
    private EmployeeBase employee;
    private Integer numberOfReports;

    public EmployeeBase getEmployee() {
        return employee;
    }

    public Integer getNumberOfReports() {
        return numberOfReports;
    }

    public void setEmployee(EmployeeBase employee) {
        this.employee = new EmployeeBase();
        this.employee.setEmployeeId(employee.getEmployeeId());
    }

    public void setNumberOfReports(Integer numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}

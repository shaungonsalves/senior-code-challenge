package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);
        Employee es = employeeService.read(id);
        es.setNumberOfReports(es.getDirectReports().size());
        for (Employee e:es.getDirectReports()){
            String sub_id = e.getEmployeeId();
            e.setFirstName(employeeService.read(sub_id).getFirstName());
            e.setLastName(employeeService.read(sub_id).getLastName());
            e.setPosition(employeeService.read(sub_id).getPosition());
            e.setDepartment(employeeService.read(sub_id).getDepartment());
            List<Employee> employeesList = employeeService.read(sub_id).getDirectReports();
            e.setDirectReports(employeesList);
            if (employeesList!=null) {
                e.setNumberOfReports(employeesList.size());
                for (Employee e2 : employeesList) {
                    String sub_id2 = e2.getEmployeeId();
                    e2.setFirstName(employeeService.read(sub_id2).getFirstName());
                    e2.setLastName(employeeService.read(sub_id2).getLastName());
                    e2.setPosition(employeeService.read(sub_id2).getPosition());
                    e2.setDepartment(employeeService.read(sub_id2).getDepartment());
                    List<Employee> employeesList2 = employeeService.read(sub_id2).getDirectReports();
                    e2.setDirectReports(employeesList2);
                    if (employeesList2 != null) {
                        e2.setNumberOfReports(employeesList2.size());
                    }
                }
            }
        }
        return es;
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }
}

package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String employeeUrl;
    private String compensationUrl;
    private String compensationIdUrl;
    private BigDecimal salary;
    private LocalDate effectiveDate;

    @Autowired
    CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCompensation(){
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();

        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(createdEmployee);
        effectiveDate = LocalDate.now();
        testCompensation.setEffectiveDate(effectiveDate.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        salary = BigDecimal.valueOf(new Random().nextInt()/100);
        testCompensation.setSalary(salary);

        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl,testCompensation,Compensation.class)
                .getBody();

//        System.out.println(createdCompensation);
        testCompensationEquivalence(testCompensation,createdCompensation);
    }

    private void testCompensationEquivalence(Compensation testCompensation, Compensation createdCompensation) {
        assertEquals(testCompensation.getEffectiveDate(),createdCompensation.getEffectiveDate());
        assertEquals(testCompensation.getEmployee().getEmployeeId(),createdCompensation.getEmployee().getEmployeeId());
        assertEquals(testCompensation.getSalary(),createdCompensation.getSalary());
    }
}

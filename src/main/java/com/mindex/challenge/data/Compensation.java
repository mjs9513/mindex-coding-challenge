package com.mindex.challenge.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.impl.EmployeeServiceImpl;
import com.mindex.challenge.service.impl.CompensationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mindex.challenge.data.Employee;
import java.util.List;

public class Compensation {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructure.class);

    private Employee employee;
    private float salary;
    private String effectiveDate;

    public Compensation() {
    }

    public void setEmployee(Employee newEmployee)
    {
        this.employee = newEmployee;
        if(employee == null)
        {
            LOG.debug("The employee provided for the report does not exist");
        }
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public String getEmployeeId()
    {
        return employee.getEmployeeId();
    }

    public void setSalary(float newSalary)
    {
        this.salary = newSalary;
    }

    public float getSalary() {
        return salary;
    }

    public void setEffectiveDate(String newDate)
    {
       this.effectiveDate = newDate;
    }

    public String getEffectiveDate()
    {
        return effectiveDate;
    }
}

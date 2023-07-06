package com.mindex.challenge.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.impl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mindex.challenge.data.Employee;
import java.util.List;

public class Compensation {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructure.class);

    @Autowired
    private EmployeeService employeeService;

    private Employee employee;
    private int numberOfReports;

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
}

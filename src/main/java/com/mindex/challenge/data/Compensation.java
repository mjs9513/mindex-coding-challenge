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

//Very similar setup to Employees with set and get methods.
public class Compensation {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructure.class);

    private Employee employee;
    private float salary;
    private String effectiveDate;

    //I had attempted a setup using the @RequestBody way of building a Compensation object, but ran into issues. I landed on this
    //setup with a constructor that took variables as being an easier way to go about it and was able to get it working.
    //Passed in the EmployeeService in order to look up the employee based on the EmployeeID as the way for setting the employee for the compensation object.
    public Compensation(String employeeId, float compensationAmount, String effectiveDate, EmployeeService employeeFunctions)
    {
        Employee compensationEmployee = employeeFunctions.read(employeeId);

        this.employee = compensationEmployee;
        this.salary = compensationAmount;
        this.effectiveDate = effectiveDate;
    }

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

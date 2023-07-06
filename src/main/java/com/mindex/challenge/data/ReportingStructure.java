package com.mindex.challenge.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.impl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mindex.challenge.data.Employee;
import java.util.List;

public class ReportingStructure {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructure.class);

    @Autowired
    private EmployeeService employeeService;

    private Employee employee;
    private int numberOfReports;

    public ReportingStructure() {
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

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(EmployeeServiceImpl employeeFunctions) {
        if(employee == null)
        {
            throw new RuntimeException("No employee for Reporting Structure found");
        }
        LOG.debug("Going to setup the Number of Reports for ReportingStructure");
        List<Employee> directReports = employee.getDirectReports();
        if(directReports.size() > 0)
        {
            LOG.debug("Direct reports for this employee: " + employee.getDirectReports());
            generateReportsNumber(employee.getDirectReports(), employeeFunctions);
        }
        else
        {
            numberOfReports = 0;
        }
        LOG.debug("Set the number of reports to: " + numberOfReports);
        LOG.debug("The main employee for this report is: " + employee);
    }

    private void generateReportsNumber(List<Employee> employeeReports, EmployeeServiceImpl employeeFunctions)
    {
        LOG.debug("Looking at direct reports of size: " + employeeReports.size());
        if(employeeReports.size() > 0)
        {
            numberOfReports += employeeReports.size();
            for(int i = 0; i < employeeReports.size(); i++)
            {
                String nextEmployeeId = employeeReports.get(i).getEmployeeId();
                LOG.debug("Checking Direct reports of the next employee: " + nextEmployeeId);
                
                //Employee nextEmployee = employeeReports.get(i);
                Employee nextEmployee = employeeFunctions.read(nextEmployeeId);

                LOG.debug("Next Employee name: " + nextEmployee.getFirstName() + " " + nextEmployee.getLastName());
                List<Employee> nextReportList = nextEmployee.getDirectReports();
                LOG.debug("Next report list: " + nextReportList);


                if(nextReportList != null)
                {
                    if(nextReportList.size() > 0)
                    {
                        generateReportsNumber(nextReportList, employeeFunctions);
                    }
                }
            }
        }
    }
}

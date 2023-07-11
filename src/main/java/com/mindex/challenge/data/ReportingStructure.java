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

    private Employee employee;
    private int numberOfReports;

    //Base constructor.
    public ReportingStructure() {
    }

    public void setEmployee(Employee newEmployee)
    {
        this.employee = newEmployee;
        if(employee == null)
        {
            LOG.debug("The employee provided for the report does not exist");
            throw new RuntimeException("No employee for Reporting Structure found");
        }
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    //Main method for starting the creation the reporting structure.
    public void setNumberOfReports(EmployeeServiceImpl employeeFunctions) {
        if(employee == null)
        {
            throw new RuntimeException("No employee for Reporting Structure found");
        }

        //LOG.debug("Going to setup the Number of Reports for ReportingStructure");
        //Getting the direct reports from the employee for this Reporting Structure.
        List<Employee> directReports = employee.getDirectReports();
        if(directReports.size() > 0)
        {//As long as the size of the direct reports in greater than 0, proceed
            //LOG.debug("Direct reports for this employee: " + employee.getDirectReports());
            generateReportsNumber(employee.getDirectReports(), employeeFunctions); //Method for actually building out the reporting structure.
        }
        else
        {
            numberOfReports = 0;
        }
        //LOG.debug("Set the number of reports to: " + numberOfReports);
        //LOG.debug("The main employee for this report is: " + employee);
    }

    private void generateReportsNumber(List<Employee> employeeReports, EmployeeServiceImpl employeeFunctions)
    {
        //LOG.debug("Looking at direct reports of size: " + employeeReports.size());
        if(employeeReports.size() > 0)
        {
            numberOfReports += employeeReports.size(); //Increase the size of the numberOfReports for this Reporting Structure based on the employeeReports.size
            for(int i = 0; i < employeeReports.size(); i++)
            {//loop through and make sure we get all the data we need from the employee reports for each employee.   
                //Retrieve the employee information for each employee under the reports, loop through and get all the data we need from them.
                String nextEmployeeId = employeeReports.get(i).getEmployeeId();
                //LOG.debug("Checking Direct reports of the next employee: " + nextEmployeeId);
                
                //Employee nextEmployee = employeeReports.get(i);
                Employee nextEmployee = employeeFunctions.read(nextEmployeeId);

                //LOG.debug("Next Employee name: " + nextEmployee.getFirstName() + " " + nextEmployee.getLastName());
                List<Employee> nextReportList = nextEmployee.getDirectReports();
                //LOG.debug("Next report list: " + nextReportList);


                if(nextReportList != null)
                {
                    if(nextReportList.size() > 0)
                    {
                        generateReportsNumber(nextReportList, employeeFunctions); //Call Generate Reports Number on the next set of direct reports.
                    }
                }
            }
        }
    }
}

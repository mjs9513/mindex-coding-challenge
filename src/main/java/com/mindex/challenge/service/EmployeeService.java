package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);

    ReportingStructure createReportingStructure(String id);
    //ReportingStructure readReportingStructure(String id);
    //ReportingStructure read(String id);
    //List<Employee> findAll(); // <- When I started out I was playing around with trying to get a FindAll function working for employees
}

package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.impl.EmployeeServiceImpl;
import com.mindex.challenge.service.ReportingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

@Service
public class ReportingServiceImpl implements ReportingService {
    //adding in the logger class for debugging.
    private static final Logger LOG = LoggerFactory.getLogger(ReportingServiceImpl.class);
    
    //Autowiring the necessary repository/service classes as I need them to look up employees.
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl employeeService;

    //making the create method for the reporting structure.
    @Override
    public ReportingStructure create(String id)
    {
        //LOG.debug("Reading Reporting Structure for employee [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id); //retrieve the employee from the repository.

        ReportingStructure newReport = new ReportingStructure();
        newReport.setEmployee(employee);

        //LOG.debug("Created a new Reporting Structure");
        //Passing in the Employee Service as I was unable to access it in the ReportingStructure class itself.
        newReport.setNumberOfReports(employeeService); 
        //LOG.debug("Number of reports for the reporting structure: " + newReport.getNumberOfReports());
        return newReport;
    }
    
}
    /*
    /*@Override
    public ReportingStructure read(String id)
    {
        return null;
    }*/

    /*@Override
    public List<Employee> findAll()
    {
        LOG.debug("Calling FindAll employees");
        List<Employee> allEmployees = employeeRepository.findAllEmployees();

        return allEmployees;
    }*/

    /*@Override
    public List<Employee> readReportingStructure(String id)
    {
        LOG.debug("Calling read Reporting Structure for employee with id [{}]", id);
        List<Employee> reportingStructure = employeeRepository.findBydirectReports(id);
        LOG.debug("Got here 1");
        if (reportingStructure == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        LOG.debug("Reporting Structure: " + reportingStructure);
        return reportingStructure;
        //return employee;
    }*/
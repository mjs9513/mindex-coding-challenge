package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Reading employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }
}
    /*Originally I had attempted to mix Reporting Structure in with Employee classes but as my understanding of the structure grew
    I felt the best foot forward with it would be to add it to it's own separate class setup.
        @Override
    public ReportingStructure createReportingStructure(String id)
    {
        LOG.debug("Reading Reporting Structure for employee [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        ReportingStructure newReport = new ReportingStructure();
        newReport.setEmployee(employee);

        LOG.debug("Created a new Reporting Structure");
        newReport.setNumberOfReports(this);
        LOG.debug("Number of reports for the reporting structure: " + newReport.getNumberOfReports());
        return newReport;
    }

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
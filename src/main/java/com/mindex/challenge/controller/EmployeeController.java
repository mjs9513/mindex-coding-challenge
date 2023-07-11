package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
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
        LOG.debug("Received employee read request for id [{}]", id);

        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee update request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }
}
/* Originally I had attempted to mix Reporting Structure in with Employee classes but as my understanding of the structure grew
I felt the best foot forward with it would be to add it to it's own separate class setup.
    @GetMapping("/reportingstructure/{id}")
    public ReportingStructure createReportingStructure(@PathVariable String id)
    {
        LOG.debug("Received employee reporting structure create request for id [{}]", id);
        //return null;
        return employeeService.createReportingStructure(id);
    }
*/


    /*@GetMapping("/employees")
    public List<Employee> all() {
        LOG.debug("Received find all employees request");

        return employeeService.findAll();
    }*/

package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Compensation create(String employeeId, float compensationAmount, String effectiveDate) {
        if(effectiveDate == "today")
        {//if the user enters 'today' as the effective date, set it to today's date.
            LOG.debug("Setting effective date to today");
            LocalDate dateObj = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            effectiveDate = dateObj.format(formatter);
        }

        LOG.debug("Creating Compensation report for: " + employeeId + " " +  compensationAmount + " "  + effectiveDate);
        Compensation compensation = new Compensation(employeeId, compensationAmount, effectiveDate, employeeService);

        compensationRepository.insert(compensation);

        return compensation;
    }

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating Compensation report [{}]", compensation);

        compensationRepository.insert(compensation);

        return compensation;
    }

    @Override
    public Compensation read(String id) {
        LOG.debug("Reading compensation for employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employee, unable to find employee for: " + id);
        }

        Compensation compensation = compensationRepository.findByEmployee(employee);

        if (compensation == null) {
            throw new RuntimeException("Compensation not found for: " + id);
        }

        return compensation;
    }
}
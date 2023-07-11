package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    /*https://stackoverflow.com/questions/33796218/content-type-application-x-www-form-urlencodedcharset-utf-8-not-supported-for
    Originally when trying to make the 'create' endpoint for Compensation, I attempted the following methods below.
    I decided on making it so you provided the employeeId, compensationAmount, and effective date as separate parameters
    in addition to having it possible to feed it a javascript object as in the bottom method. I did this mainly because I was 
    unsure how I would go about writing the request to feed it a @RequestBody Compensation, which I got from referencing
    the setup for employees. The middle method I was running into issues of getting a 415 error back from the server 
    when trying to call the POST method via powershell. After a bit of digging I found the stackoverflow article listed above
    talking about how @Requestbody and Spring don't go well together, and you can use just raw paramter input in place of it.
    
    I ended up using the following in powershell ISE to create the compensation type:
    $postParams = @{employeeId="16a596ae-edd3-4847-99fe-c4518e82c86f";compensationAmount="500.0";effectiveDate="today"}
    Invoke-WebRequest -Uri http://localhost:8080/compensation -Method POST -Body $postParams
    */
    @PostMapping("/compensation")
    public Compensation create(String employeeId,float compensationAmount,String effectiveDate) {
        LOG.debug("Received compensation create request for [{}]", employeeId);

        return compensationService.create(employeeId, compensationAmount, effectiveDate);
    }

    //See above comments for explanation on this.
    /*@PostMapping("/compensation")
    public Compensation create(@Requestbody String employeeId,@Requestbody float compensationAmount,@Requestbody String effectiveDate) {
        LOG.debug("Received compensation create request for [{}]", employeeId);

        return compensationService.create(employeeId, compensationAmount, effectiveDate);
    }*/

    /*@PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);

        return compensationService.create(compensation);
    }*/

    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id) {
        LOG.debug("Received compensation read request for id [{}]", id);

        return compensationService.read(id);
    }
}

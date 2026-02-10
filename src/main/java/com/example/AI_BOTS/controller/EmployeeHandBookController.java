package com.example.AI_BOTS.controller;

import com.example.AI_BOTS.dto.PolicyAnswerDto;
import com.example.AI_BOTS.service.EmployeeHandBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/handbook")
@RequiredArgsConstructor
public class EmployeeHandBookController {
    private final EmployeeHandBookService employeeHandBookService;

    @GetMapping
    public PolicyAnswerDto getPolicy(@RequestParam String question){
        return employeeHandBookService.getPolicy(question);
    }

}

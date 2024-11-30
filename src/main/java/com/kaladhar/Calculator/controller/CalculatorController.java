package com.kaladhar.Calculator.controller;

import com.kaladhar.Calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    // Addition endpoint
    @GetMapping("/add")
    public Result add(@RequestParam double a, @RequestParam double b) {
        return new Result(calculatorService.add(a, b));
    }

    // Subtraction endpoint
    @GetMapping("/subtract")
    public Result subtract(@RequestParam double a, @RequestParam double b) {
        return new Result(calculatorService.subtract(a, b));
    }

    // Multiplication endpoint
    @GetMapping("/multiply")
    public Result multiply(@RequestParam double a, @RequestParam double b) {
        return new Result(calculatorService.multiply(a, b));
    }

    // Division endpoint
    @GetMapping("/divide")
    public Result divide(@RequestParam double a, @RequestParam double b) {
        try {
            return new Result(calculatorService.divide(a, b));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    // Inner class to return the result as JSON
    public static class Result {
        private double result;

        public Result(double result) {
            this.result = result;
        }

        public double getResult() {
            return result;
        }

        public void setResult(double result) {
            this.result = result;
        }
    }
}

package com.example.demo.dao;


import com.example.demo.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDAO extends CrudRepository<Employee,String> {

}

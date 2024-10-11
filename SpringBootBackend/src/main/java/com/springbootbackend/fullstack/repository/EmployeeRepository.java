package com.springbootbackend.fullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootbackend.fullstack.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	// Now we extends JpaRepository - exposes to code methods like - save, findById, findAll, deleteById, delete.
	// It provides out of the box implementation for all the databases methods . So we no need to write boilerplate code
	// We need to provide the arguments- In our case Employee as JPAEntity and long as type of primary key

}

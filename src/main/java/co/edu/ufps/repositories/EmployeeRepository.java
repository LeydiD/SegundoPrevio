package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

}
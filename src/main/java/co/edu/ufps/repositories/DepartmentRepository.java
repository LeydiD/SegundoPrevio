package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer>{

}
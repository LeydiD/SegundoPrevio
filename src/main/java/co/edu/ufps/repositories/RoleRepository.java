package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{

}
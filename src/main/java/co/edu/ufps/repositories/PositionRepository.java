package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Position;


@Repository
public interface PositionRepository extends JpaRepository<Position,Integer>{

}
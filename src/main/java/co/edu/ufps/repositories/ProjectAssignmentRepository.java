package co.edu.ufps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Project;
import co.edu.ufps.entities.ProjectAssignment;


@Repository
public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment,Integer>{
	List<ProjectAssignment> findByProject(Project project);
}
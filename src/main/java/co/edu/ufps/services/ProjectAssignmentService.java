package co.edu.ufps.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.ufps.entities.Employee;
import co.edu.ufps.entities.Project;
import co.edu.ufps.entities.ProjectAssignment;
import co.edu.ufps.entities.Role;
import co.edu.ufps.repositories.EmployeeRepository;
import co.edu.ufps.repositories.ProjectAssignmentRepository;
import co.edu.ufps.repositories.ProjectRepository;
import co.edu.ufps.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectAssignmentService {
	
	@Autowired
	private ProjectAssignmentRepository projectAssignmentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	 public List<ProjectAssignment> listarEmpleadosDeProyecto(Integer projectId) {
	        Optional<Project> optionalProject = projectRepository.findById(projectId);
	        if (!optionalProject.isPresent()) {
	            return null;  }
	        
	        Project project = optionalProject.get();
	        return projectAssignmentRepository.findByProject(project);
	    }
	 
	public Optional<ProjectAssignment> asignarEmpleadoAProyecto(Integer employeeId, Integer projectId, Integer roleId) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
		Optional<Project> optionalProject = projectRepository.findById(projectId);
		Optional<Role> optionalRole = roleRepository.findById(roleId);

		if (!optionalEmployee.isPresent() || !optionalProject.isPresent() || !optionalRole.isPresent()) {
			return Optional.empty();
		}

		Employee employee = optionalEmployee.get();
		Project project = optionalProject.get();
		Role role = optionalRole.get();

		ProjectAssignment projectAssignment = new ProjectAssignment();
		projectAssignment.setEmployee(employee);
		projectAssignment.setProject(project);
		projectAssignment.setRole(role);

		return Optional.of(projectAssignmentRepository.save(projectAssignment));
	}
}

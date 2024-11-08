package co.edu.ufps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.entities.ProjectAssignment;
import co.edu.ufps.services.ProjectAssignmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project-assignments")
public class ProjectAssignmentController {

    @Autowired
    private ProjectAssignmentService projectAssignmentService;

    @GetMapping("/employees/{projectId}")
    public ResponseEntity<List<ProjectAssignment>> listarEmpleadosDeProyecto(@PathVariable Integer projectId) {
        List<ProjectAssignment> projectAssignments = projectAssignmentService.listarEmpleadosDeProyecto(projectId);
        
        if (projectAssignments == null || projectAssignments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(projectAssignments);
    }
    
    @PostMapping("/assign")
    public ResponseEntity<ProjectAssignment> asignarEmpleadoAProyecto( @PathVariable Integer employeeId,@PathVariable Integer projectId,
    		@PathVariable Integer roleId) {
        
        Optional<ProjectAssignment> projectAssignment = projectAssignmentService.asignarEmpleadoAProyecto(employeeId, projectId, roleId);
        return projectAssignment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.entities.Employee;
import co.edu.ufps.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public List<Employee> list() {
		return employeeService.list();
	}

	@PostMapping
	public Employee create(@RequestBody Employee Employee) {
		return employeeService.create(Employee);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getById(@PathVariable Integer id) {
		Optional<Employee> employee = employeeService.getById(id);
		return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody Employee EmployeeDetails) {
		Optional<Employee> updatedEmployee = employeeService.update(id, EmployeeDetails);
		return updatedEmployee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = employeeService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/salario")
	public ResponseEntity<Employee> getEmpleadoConSalario(@PathVariable Integer id) {
		Optional<Employee> employee = employeeService.getEmpleadoConSalario(id);
		return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/{id}/departments/{departmentId}")
	public ResponseEntity<Employee> addDepartment(@PathVariable Integer id, @PathVariable Integer departmentId) {
		Employee updatedEmployee = employeeService.addDepartment(id, departmentId);
		return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}/departments/{departmentId}")
	public ResponseEntity<Employee> desasociarDepartamento(@PathVariable Integer id, @PathVariable Integer departmentId) {
		Optional<Employee> updatedEmployee = employeeService.desasociarDepartamento(id, departmentId);
		return updatedEmployee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}


}

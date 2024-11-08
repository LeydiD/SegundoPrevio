package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Department;
import co.edu.ufps.entities.Employee;
import co.edu.ufps.repositories.DepartmentRepository;
import co.edu.ufps.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;

	public List<Employee> list() {
		return employeeRepository.findAll();
	}

	public Employee create(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Optional<Employee> update(Integer id, Employee employeeDetails) {
		Optional<Employee> optionalemployee = employeeRepository.findById(id);
		if (!optionalemployee.isPresent()) {
			return Optional.empty();
		}

		Employee employee = optionalemployee.get();
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setBirthdate(employeeDetails.getBirthdate());
		employee.setEntryDate(employeeDetails.getEntryDate());
		return Optional.of(employeeRepository.save(employee));
	}

	public Optional<Employee> getById(Integer id) {
		return employeeRepository.findById(id);
	}

	public boolean delete(Integer id) {
		if (!employeeRepository.existsById(id)) {
			return false;
		}

		employeeRepository.deleteById(id);
		return true;
	}
	
	public Optional<Employee> getEmpleadoConSalario(Integer id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			Double salary = employee.getPosition() != null ? employee.getPosition().getSalary() : null;
			return Optional.of(employee);
		}
		return Optional.empty();
	}
	
	public Employee addDepartment(Integer id, Integer departmentId) {
		Optional<Employee> employeeOpt = employeeRepository.findById(id);
		if (employeeOpt.isPresent()) {
			Employee employee = employeeOpt.get();
			Optional<Department> departmentOpt = departmentRepository.findById(departmentId);
			if (departmentOpt.isPresent()) {
				employee.addDepartment(departmentOpt.get());
			}
			return employeeRepository.save(employee);
		}
		return null;
	}
	
	public Optional<Employee> desasociarDepartamento(Integer employeeId, Integer departmentId) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
		Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);

		if (!optionalEmployee.isPresent() || !optionalDepartment.isPresent()) {
			return Optional.empty();
		}

		Employee employee = optionalEmployee.get();
		Department department = optionalDepartment.get();

		if (employee.getDepartments1().contains(department)) {
			employee.getDepartments1().remove(department);
			department.getEmployees1().remove(employee);
			employeeRepository.save(employee);
		}

		return Optional.of(employee);
	}
}
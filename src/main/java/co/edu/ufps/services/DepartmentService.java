package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Department;
import co.edu.ufps.repositories.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	public List<Department> list() {
		return departmentRepository.findAll();
	}

	public Department create(Department department) {
		return departmentRepository.save(department);
	}

	public Optional<Department> update(Integer id, Department departmentDetails) {
		Optional<Department> optionaldepartment = departmentRepository.findById(id);
		if (!optionaldepartment.isPresent()) {
			return Optional.empty();
		}

		Department department = optionaldepartment.get();
		department.setName(departmentDetails.getName());
		return Optional.of(departmentRepository.save(department));
	}

	public Optional<Department> getById(Integer id) {
		return departmentRepository.findById(id);
	}

	public boolean delete(Integer id) {
		if (!departmentRepository.existsById(id)) {
			return false;
		}

		departmentRepository.deleteById(id);
		return true;
	}
}
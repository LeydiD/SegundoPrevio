package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Project;
import co.edu.ufps.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	public List<Project> list() {
		return projectRepository.findAll();
	}

	public Project create(Project project) {
		return projectRepository.save(project);
	}

	public Optional<Project> update(Integer id, Project projectDetails) {
		Optional<Project> optionalproject = projectRepository.findById(id);
		if (!optionalproject.isPresent()) {
			return Optional.empty();
		}

		Project project = optionalproject.get();
		project.setName(projectDetails.getName());
		project.setDescripcion(projectDetails.getDescripcion());
		project.setStartDate(projectDetails.getStartDate());
		project.setEndDate(projectDetails.getEndDate());
		return Optional.of(projectRepository.save(project));
	}

	public Optional<Project> getById(Integer id) {
		return projectRepository.findById(id);
	}

	public boolean delete(Integer id) {
		if (!projectRepository.existsById(id)) {
			return false;
		}

		projectRepository.deleteById(id);
		return true;
	}
}
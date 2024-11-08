package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Position;
import co.edu.ufps.repositories.PositionRepository;

@Service
public class PositionService {

	@Autowired
	PositionRepository positionRepository;

	public List<Position> list() {
		return positionRepository.findAll();
	}

	public Position create(Position position) {
		return positionRepository.save(position);
	}

	public Optional<Position> update(Integer id, Position positionDetails) {
		Optional<Position> optionalposition = positionRepository.findById(id);
		if (!optionalposition.isPresent()) {
			return Optional.empty();
		}

		Position position = optionalposition.get();
		position.setName(positionDetails.getName());
		position.setSalary(positionDetails.getSalary());
		return Optional.of(positionRepository.save(position));
	}

	public Optional<Position> getById(Integer id) {
		return positionRepository.findById(id);
	}

	public boolean delete(Integer id) {
		if (!positionRepository.existsById(id)) {
			return false;
		}

		positionRepository.deleteById(id);
		return true;
	}
}
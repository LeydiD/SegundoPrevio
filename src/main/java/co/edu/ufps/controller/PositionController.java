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

import co.edu.ufps.entities.Position;
import co.edu.ufps.services.PositionService;

@RestController
@RequestMapping("/positions")
public class PositionController {

	@Autowired
	private PositionService positionService;

	@GetMapping
	public List<Position> list() {
		return positionService.list();
	}

	@PostMapping
	public Position create(@RequestBody Position Position) {
		return positionService.create(Position);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Position> getById(@PathVariable Integer id) {
		Optional<Position> position = positionService.getById(id);
		return position.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Position> update(@PathVariable Integer id, @RequestBody Position PositionDetails) {
		Optional<Position> updatedPosition = positionService.update(id, PositionDetails);
		return updatedPosition.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = positionService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}


}

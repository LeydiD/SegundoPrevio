package co.edu.ufps.entities;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="position")
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name", length =100)
	private String name;
	
	@Column(name="salary")
	private Double salary;
	
	@OneToMany(mappedBy="position", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Employee> employees;
	
}

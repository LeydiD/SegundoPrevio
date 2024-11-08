package co.edu.ufps.entities;



import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="first_name", length =50)
	private String firstName;
	
	@Column(name="last_name", length =50)
	private String lastName;
	
	@Column(name="birthdate")
	private Date birthdate;
	
	@Column(name="entry_date")
	private Date entryDate;
	
	@ManyToOne
	@JoinColumn(name="position_id")
	private Position position;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;
	
	@OneToMany(mappedBy="employee", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Department> departments;
	
	@OneToMany(mappedBy="employee", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<ProjectAssignment> projectAssignments;
	
	
	@ManyToMany
	@JoinTable(
			name ="visit",
			joinColumns = @JoinColumn(name="employee_id"),
			inverseJoinColumns = @JoinColumn(name="department_id")
			)
	List<Department> departments1;
	
	
	public void addDepartment(Department department) {
		this.departments.add(department);
	}

	public void removeEmpleado(Department department) {
		this.departments.remove(department);
		
	}
}

package org.iesvdm.employee;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.iesvdm.employee.Employee;
import org.iesvdm.employee.EmployeeInMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeInMemoryRepositoryTest {

	private EmployeeInMemoryRepository employeeRepository;

	private List<Employee> employees;

	@BeforeEach
	public void setup() {
		employees = spy(new ArrayList<>()); // Creamos un spy de la lista para poder verificar los métodos llamados
		employeeRepository = new EmployeeInMemoryRepository(employees);
	}

	/**
	 * Descripción del test:
	 * Crea 2 Employee diferentes
	 * Añádelos a la colección de empleados
	 * Comprueba que cuando llamas a employeeRepository.findAll
	 * Obtienes los empleados añadidos en el paso anterior
	 */
	@Test
	public void testEmployeeRepositoryFindAll() {
		// Given
		Employee employee1 = new Employee("1", 1000);
		Employee employee2 = new Employee("2", 1200);
		employees.addAll(asList(employee1, employee2));

		// When
		List<Employee> result = employeeRepository.findAll();

		// Then
		assertThat(result).containsExactly(employee1, employee2);
	}

	/**
	 * Descripción del test:
	 * Salva un Employee mediante el método
	 * employeeRepository.save y comprueba que la colección
	 * employees contiene solo ese Employee
	 */
	@Test
	public void testEmployeeRepositorySaveNewEmployee() {
		// Given
		Employee employee = new Employee("1", 1000);

		// When
		employeeRepository.save(employee);

		// Then
		assertThat(employees).containsOnly(employee);
	}

	/**
	 * Descripción del test:
	 * Crea un par de Employee diferentes
	 * Añádelos a la colección de empleados.
	 * A continuación, mediante employeeRepository.save
	 * Salva los Employee anteriores (mismo id) con cambios
	 * en el salario y comprueba que la colección employees
	 * los contiene actualizados.
	 */
	@Test
	public void testEmployeeRepositorySaveExistingEmployee() {
		// Given
		Employee employee1 = new Employee("1", 1000);
		Employee employee2 = new Employee("1", 1200); // Mismo id que el anterior
		employees.add(employee1);

		// When
		employeeRepository.save(employee2);

		// Then
		assertThat(employees).containsOnly(employee2);
	}
}


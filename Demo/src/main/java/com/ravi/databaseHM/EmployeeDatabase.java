package com.ravi.databaseHM;

import java.util.*;

import com.ravi.entity.Employee;

public class EmployeeDatabase {
	private static Map<Integer, Employee> employees = new HashMap<>();

	static {
		employees.put(1, new Employee(1, "Ravi", "Software"));
		employees.put(2, new Employee(2, "Sakuru", "HR"));
	}

	public static List<Employee> getAllEmployees() {
		return new ArrayList<>(employees.values());
	}

	public static Employee getEmployee(int id) {
		return employees.get(id);
	}

	public static void addEmployee(Employee emp) {
		employees.put(emp.getId(), emp);
	}

	public static void updateEmployee(int id, Employee emp) {
		employees.put(id, emp);
	}

	public static void deleteEmployee(int id) {
		employees.remove(id);
	}
}

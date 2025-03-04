package com.ravi.demo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import com.ravi.databaseHM.EmployeeDatabase;
import com.ravi.entity.Employee;

@Path("/employees")
public class EmployeeResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees() {
		return EmployeeDatabase.getAllEmployees();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployee(@PathParam("id") int id) {
		Employee emp = EmployeeDatabase.getEmployee(id);
		if (emp == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Employee not found for ID: " + id).build();
		}
		return Response.ok(emp).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEmployee(Employee emp) {
		EmployeeDatabase.addEmployee(emp);
		return Response.status(Response.Status.CREATED).entity("Employee added successfully").build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployee(@PathParam("id") int id, Employee emp) {
		if (EmployeeDatabase.getEmployee(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Employee ID not found").build();
		}
		EmployeeDatabase.updateEmployee(id, emp);
		return Response.ok("Employee updated successfully").build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteEmployee(@PathParam("id") int id) {
		if (EmployeeDatabase.getEmployee(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Employee ID not found").build();
		}
		EmployeeDatabase.deleteEmployee(id);
		return Response.ok("Employee deleted successfully").build();
	}
}

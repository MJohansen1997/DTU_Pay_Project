package adapter.rest;

import javax.ws.rs.*;

import studentregistration.service.Student;
import studentregistration.service.StudentRegistrationService;

@Path("/students")
public class StudentResource {

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Student registerStudent(Student student) {
		StudentRegistrationService service = new StudentRegistrationFactory().getService();
		return service.register(student);
	}

	@GET
	@Produces("application/json")
	public Student studentRet() {
		Student student = new Student();
		student.setId("123");
		student.setName("mikkel");
		return student;
	}

}

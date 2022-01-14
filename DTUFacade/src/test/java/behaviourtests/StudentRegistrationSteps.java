package behaviourtests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.concurrent.CompletableFuture;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import messaging.Event;
import messaging.MessageQueue;
import studentregistration.service.Student;
import studentregistration.service.StudentRegistrationService;

public class StudentRegistrationSteps {
	private MessageQueue q = mock(MessageQueue.class);
	private StudentRegistrationService service = new StudentRegistrationService(q);
	private CompletableFuture<Student> registeredStudent = new CompletableFuture<>();
	private Student student;

	public StudentRegistrationSteps() {
	}

	@Given("there is a student with empty id")
	public void thereIsAStudentWithEmptyId() {
		student = new Student();
		student.setName("James");
		assertNull(student.getId());
	}

	@When("the student is being registered")
	public void theStudentIsBeingRegistered() {
		// We have to run the registration in a thread, because
		// the register method will only finish after the next @When
		// step is executed.
		new Thread(() -> {
			var result = service.register(student);
			registeredStudent.complete(result);
		}).start();
	}

	@Then("the {string} event is sent")
	public void theEventIsSent(String string) {
		Event event = new Event(string, new Object[] { student });
		verify(q).publish(event);
	}

	@When("the {string} event is sent with non-empty id")
	public void theEventIsSentWithNonEmptyId(String string) {
		// This step simulate the event created by a downstream service.
		var c = new Student();
		c.setName(student.getName());
		c.setId("123");
		service.handleStudentIdAssigned(new Event("..",new Object[] {c}));
	}

	@Then("the student is registered and his id is set")
	public void theStudentIsRegisteredAndHisIdIsSet() {
		// Our logic is very simple at the moment; we don't
		// remember that the student is registered.
		assertNotNull(registeredStudent.join().getId());
	}
}

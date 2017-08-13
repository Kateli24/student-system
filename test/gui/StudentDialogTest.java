package gui;

import dao.StudentDAO;
import domain.Student;
import java.util.Collection;
import java.util.TreeSet;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentDialogTest {

	private StudentDAO dao;
	private DialogFixture fixture;
	private Robot robot;

	@Before
	public void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();

		// Slow down the robot a bit - default is 30 (milliseconds).
		// Do not make it less than 5 or you will have thread-race problems.
		robot.settings().delayBetweenEvents(75);

		// add some majors for testing with
		Collection<String> majors = new TreeSet<>();
		majors.add("Knitting");
		majors.add("Ninjitsu");

		// create a mock for the DAO
		dao = mock(StudentDAO.class);

		// stub the getMajors method to return the test majors
		when(dao.getMajors()).thenReturn(majors);
	}

	@After
	public void tearDown() {
		// clean up fixture so that it is ready for the next test
		fixture.cleanUp();
	}

	@Test
	public void testEdit() {
		// a student to edit
		Student jack = new Student(1234, "Jack", "Knitting");

		// create dialog passing in student and mocked DAO
		StudentDialog dialog = new StudentDialog(null, true, jack, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);

		// show the dialog on the screen, and ensure it is visible
		fixture.show().requireVisible();

		// verify that the UI componenents contains the student's details
		fixture.textBox("txtId").requireText("1234");
		fixture.textBox("txtName").requireText("Jack");
		fixture.comboBox("cmbMajor").requireSelection("Knitting");

		// edit the name and major
		fixture.textBox("txtName").selectAll().deleteText().enterText("Jim");
		fixture.comboBox("cmbMajor").selectItem("Ninjitsu");

		// click the save button
		fixture.button("btnSave").click();

		// create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
		ArgumentCaptor<Student> argument = ArgumentCaptor.forClass(Student.class);

		// verify that the DAO.save method was called, and capture the passed student
		verify(dao).save(argument.capture());

		// retrieve the passed student from the captor
		Student editedStudent = argument.getValue();

		// check that the changes were saved
		assertEquals("Ensure the name was changed", "Jim", editedStudent.getName());
		assertEquals("Ensure the major was changed", "Ninjitsu", editedStudent.getMajor());
	}

	@Test
	public void testSave() {
		// create the dialog passing in the mocked DAO
		StudentDialog dialog = new StudentDialog(null, true, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);
		fixture.show().requireVisible();

		// enter some details into the UI components
		fixture.textBox("txtId").enterText("1234");
		fixture.textBox("txtName").enterText("Jack");
		fixture.comboBox("cmbMajor").selectItem("Knitting");

		// click the save button
		fixture.button("btnSave").click();

		// create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
		ArgumentCaptor<Student> argument = ArgumentCaptor.forClass(Student.class);

		// verify that the DAO.save method was called, and capture the passed student
		verify(dao).save(argument.capture());

		// retrieve the passed student from the captor
		Student savedStudent = argument.getValue();

		// test that the student's details were properly saved
		assertEquals("Ensure the ID was saved", new Integer(1234), savedStudent.getId());
		assertEquals("Ensure the name was saved", "Jack", savedStudent.getName());
		assertEquals("Ensure the major was saved", "Knitting", savedStudent.getMajor());
	}

}

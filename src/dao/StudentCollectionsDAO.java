package dao;

import domain.Student;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * A DAO that uses collections for storing and managing student objects.
 */
public class StudentCollectionsDAO implements StudentDAO {

	private static final SortedMap<Integer, Student> students = new TreeMap<>();
	private static final MultiMap<String, Student> majors = new MultiMap<>();

	@Override
	public void save(Student aStudent) {
		students.put(aStudent.getId(), aStudent);
		majors.put(aStudent.getMajor(), aStudent);
	}

	@Override
	public void delete(Student aStudent) {
		students.remove(aStudent.getId());
		majors.remove(aStudent.getMajor(), aStudent);
	}

	@Override
	public Collection<Student> getAll() {
		return students.values();
	}

	@Override
	public Collection<String> getMajors() {
		return majors.keySet();
	}

	@Override
	public Student getByID(Integer studentID) {
		return students.get(studentID);
	}

	@Override
	public Collection<Student> getByMajor(String major) {
		return majors.get(major);
	}

}

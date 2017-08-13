package domain;

import java.util.Objects;

public class Student implements Comparable<Student> {

	private Integer id;
	private String name;
	private String major;

	public Student() {
	}

	public Student(Integer id, String name, String major) {
		this.id = id;
		this.name = name;
		this.major = major;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return this.id + ", " + this.name + ", " + this.major;
	}

	@Override
	public int compareTo(Student other) {
		return this.getId().compareTo(other.getId());
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 59 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Student other = (Student) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

}

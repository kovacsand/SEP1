package module;

import java.io.Serializable;

/**
 * A class containing all the methods and the constructor for the classes
 *
 * @author Hi-Phi
 * @version 1.0
 */
public class Class implements Serializable
{
  private int semester;
  private String group;
  private StudentList studentList;
  private CourseList courseList;
  private int studentCount;

  /**
   * Two-argument constructor initializing the semester, group, studentList and courseList.
   *
   * @param semester semester of the class
   * @param group    name of the group
   */
  public Class(int semester, String group)
  {
    this.semester = semester;
    this.group = group;
    studentList = new StudentList();
    courseList = new CourseList();
    this.studentCount = 0;
  }

  /**
   * Get student count of the class
   *
   * @return amount of student objects in class
   */
  public int getStudentCount()
  {
    studentCount = studentList.getSize();
    return studentCount;
  }

  /**
   * Returns the semester number of the class.
   *
   * @return semester of the class you are looking for
   */
  public int getSemester()
  {
    return semester;
  }

  /**
   * Returns the group name of the class.
   *
   * @return group of the class you are looking for
   */
  public String getGroup()
  {
    return group;
  }

  /**
   * Returns the ID of the class (Ex. 1X)
   *
   * @return the semester and the group of the class added together
   */
  public String getId()
  {
    return semester + group;
  }

  /**
   * Returns the list of students of the class.
   *
   * @return studentList containing all the student objects that are within the list
   */
  public StudentList getAllStudents()
  {
    return studentList;
  }

  /**
   * Returns the student with the given id from the studentList.
   *
   * @param id ID of the student object to look for
   * @return found Student object that is found that matches the ID.
   */
  public Student getStudent(String id)
  {
    Student found = null;
    for (int i = 0; i < studentList.getSize(); i++)
    {
      if (studentList.getAllStudents().get(i).getId().equals(id))
      {
        found = studentList.getAllStudents().get(i);
      }
    }
    return found;
  }

  /**
   * Returns the list of courses of the class.
   *
   * @return courseList all the course objects that are within the courseList
   */
  public CourseList getAllCourses()
  {
    return courseList;
  }

  /**
   * Returns the course with the given name from the courseList.
   *
   * @param id of the course the user is looking for
   * @return course object that is found that matches the given id
   */
  public Course getCourse(String id)
  {
    Course found = null;
    for (int i = 0; i < courseList.getSize(); i++)
    {
      if (courseList.getAllCourses().get(i).getId().equals(id))
      {
        found = courseList.getAllCourses().get(i);
      }
    }
    return found;
  }

  /**
   * Getting all the courses for GUI inside of classes tab
   *
   * @return String that contains all the courses
   */
  public String getCourses()
  {
    String temp = "";
    for (int i = 0; i < courseList.getSize(); i++)
    {
      if (i == courseList.getSize())
        temp += courseList.getAllCourses().get(i).getId();
      else
        temp += courseList.getAllCourses().get(i).getId() + ", ";
    }
    return temp;
  }

  /**
   * Adds the given student to the studentList of the class.
   *
   * @param student student object that must be added to the studentList for the class
   */
  public void addStudent(Student student)
  {
    studentList.getAllStudents().add(student);
  }

  /**
   * Adds the given course to the courseList of the class.
   *
   * @param course course object that must be added to the courseList for the class
   */
  public void addCourse(Course course)
  {
    courseList.getAllCourses().add(course);
  }

  /**
   * Removes a student from the studentList of the class.
   *
   * @param student student object that must be removed
   */
  public void removeStudent(Student student)
  {
    studentList.removeStudent(student.getId());
  }

  /**
   * Checks if two classes are the same.
   * @param obj the Class we want to compare with
   * @return true if they are equal, otherwise false
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Class))
    {
      return false;
    }
    Class other = (Class) obj;
    if (studentList.getSize() != other.studentList.getSize())
      return false;
    if (courseList.getSize() != other.courseList.getSize())
      return false;
    return semester == other.semester && group.equals(other.group)
        && studentList.equals(other.studentList) && courseList.equals(
        other.courseList);
  }

  /**
   * Displays all the data stored in the class.
   *
   * @return String that contains all the information about the class
   */
  public String toString()
  {
    String studentsSTR = "";
    String coursesSTR = "";
    for (int i = 0; i < studentList.getSize(); i++)
    {
      studentsSTR = studentsSTR + studentList.getAllStudents().get(i) + "\n";
    }
    for (int i = 0; i < courseList.getSize(); i++)
    {
      coursesSTR = coursesSTR + courseList.getAllCourses().get(i) + "\n";
    }
    return "Semester: " + semester + "\nGroup: " + group + "\nStudents: "
        + studentsSTR + "\nCourses: " + coursesSTR;
  }

  /**
   * Creates a new Class object with the same values.
   *
   * @return newly created Class object
   */
  public Class copy()
  {
    Class temp = new Class(semester, group);
    for (int i = 0; i < studentList.getSize(); i++)
    {
      temp.addStudent(studentList.getAllStudents().get(i));
    }
    for (int i = 0; i < courseList.getSize(); i++)
    {
      temp.addCourse(courseList.getAllCourses().get(i));
    }
    return temp;
  }
}


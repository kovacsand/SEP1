package module;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class for storing all the Student objects in ArrayList
 *
 * @author Hi-Phi
 * @version 1.0
 */
public class StudentList implements Serializable
{
  private ArrayList<Student> students;

  /**
   * Zero-argument constructor initializing the student list and ArrayList
   */
  public StudentList()
  {
    students = new ArrayList<Student>();
  }

  /**
   * Adding a student to the ArrayList
   *
   * @param student object that must be added to the ArrayList
   */
  public void addStudent(Student student)
  {
    students.add(student);
  }

  /**
   * Removing the student object from ArrayList by its ID
   *
   * @param id of the student object that must be removed if its found
   */
  public void removeStudent(String id)
  {
    for (int i = 0; i < students.size(); i++)
    {
      Student student = students.get(i);
      if (student.getId().equals(id))
      {
        students.remove(student);
        break;
      }
    }
  }

  /**
   * Getting all the students from ArrayList
   *
   * @return ArrayList containing all the Student objects
   */
  public ArrayList<Student> getAllStudents()
  {
    return students;
  }

  /**
   * Getting all the students by their class
   *
   * @param semester of the student that it is enrolled in
   * @param group    of the student that it is enrolled in
   * @returne ArrayList of student objects that are enrolled in the given semester and the group (E.g. 1X)
   */
  public ArrayList<Student> getAllStudentsByClass(int semester, String group)
  {
    ArrayList<Student> studentsFromClass = new ArrayList<Student>();
    for (int i = 0; i < students.size(); i++)
    {
      Student student = students.get(i);
      if (student.getSemester() == semester && student.getGroup().equals(group))
      {
        studentsFromClass.add(student);
      }
    }
    return studentsFromClass;
  }

  /**
   * Getting all student IDs to display on XML file
   *
   * @return String that contains all the IDs of the students
   */
  public String getAllStudentIds()
  {
    String str = "";
    for (int i = 0; i < students.size(); i++)
    {
      if (i == students.size() - 1)
        str += students.get(i).getId();
      else
        str += students.get(i).getId() + ", ";
    }
    return str;
  }

  /**
   * Getting all the students by its semester
   *
   * @param semester of the students they are enrolled in
   * @return ArrayList that are enrolled in the semester that has been given as the argument
   */
  public ArrayList<Student> getAllStudentsBySemester(int semester)
  {
    ArrayList<Student> studentsFromSemester = new ArrayList<Student>();
    for (int i = 0; i < students.size(); i++)
    {
      Student student = students.get(i);
      if (student.getSemester() == semester)
      {
        studentsFromSemester.add(student);
      }
    }
    return studentsFromSemester;
  }

  /**
   * Getting the student object by its ID
   *
   * @param id of the student object we are looking for
   * @return the student object if it matches the ID of the given one
   */
  public Student getStudent(String id)
  {
    Student student = null;
    for (int i = 0; i < students.size(); i++)
    {

      if (students.get(i).getId().equals(id))
      {
        student = students.get(i);
        break;
      }
    }
    return student;
  }

  /**
   * Getting the size of the Arraylist
   *
   * @return the size of the ArrayList
   */
  public int getSize()
  {
    return getAllStudents().size();
  }

  /**
   * Comparing two StudentList objects
   *
   * @param obj of the StudentList object that we are comparing with
   * @return true if they are equal, otherwise false
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof StudentList))
    {
      return false;
    }
    StudentList other = (StudentList) obj;
    return students.equals(other.students);
  }

  /**
   * Converts StudentList into a String
   *
   * @return String that contains the information about the StudentList
   */
  public String toString()
  {
    String allStudents = "";
    for (int i = 0; i < students.size(); i++)
    {
      allStudents += students.get(i) + "\n";
    }
    return allStudents;
  }

  /**
   * Creating a copy of the object with the same values
   *
   * @return newly created StudentList object
   */
  public StudentList copy()
  {
    StudentList studentList = new StudentList();
    for (int i = 0; i < students.size(); i++)
    {
      studentList.addStudent(students.get(i));
    }
    return studentList;
  }
}

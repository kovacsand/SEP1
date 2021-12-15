package module;

import java.io.Serializable;

/**
 * Class for storing data about courses, including all the functionality
 *
 * @author Hi-Phi
 * @version 1.0
 */
public class Course implements Serializable
{
  private String name;
  private int semester;
  private String group;
  private int ects;
  private TeacherList teacherList;
  private StudentList studentList;
  private String teachersId;

  /**
   * Five-argument constructor initializing the name, semester, group, ects, studentList and teacherList. Adds the teacher to the teacherList.
   *
   * @param name     The name of the course
   * @param semester The number of the semester of the course
   * @param group    The group name of the course
   * @param ects     The number of ECTS of the course
   * @param teacher  The name of the teacher of the course
   */
  public Course(String name, int semester, String group, int ects,
      Teacher teacher)
  {
    this.name = name;
    this.semester = semester;
    this.group = group;
    this.ects = ects;
    teacherList = new TeacherList();
    studentList = new StudentList();
    teacherList.addTeacher(teacher);
    this.teachersId = teacher.getId();
  }

  /**
   * Returns the name of the course.
   *
   * @return name of the course
   */
  public String getName()
  {
    return name;
  }

  /**
   * Returns the semester number of the course.
   *
   * @return semester of the course
   */
  public int getSemester()
  {
    return semester;
  }

  /**
   * Returns the group of the course.
   *
   * @return group of the course
   */
  public String getGroup()
  {
    return group;
  }

  /**
   * Returns the number of ECTS of the course.
   *
   * @return ECTS given for the completion of the course
   */
  public int getEcts()
  {
    return ects;
  }

  /**
   * Getting a string of the teachers ID for the GUI
   *
   * @return Teachers ID
   */
  public String getTeachersId()
  {
    return teachersId;
  }

  /**
   * Returns the list of teachers of the course.
   *
   * @return teacherList object containing all the teacher objects
   */
  public TeacherList getAllTeachers()
  {
    return teacherList;
  }

  /**
   * Returns the teacher with the given id from the teacherList.
   *
   * @param id of the teacher object looking for
   * @return found teacher object that matches the given ID
   */
  public Teacher getTeacher(String id)
  {
    Teacher found = null;
    for (int i = 0; i < teacherList.getSize(); i++)
    {
      if (teacherList.getAllTeachers().get(i).getId().equals(id))
      {
        found = teacherList.getAllTeachers().get(i);
      }
    }
    return found;
  }

  /**
   * Returns the list of students of the course.
   *
   * @return studentList object that contains all student objects
   */
  public StudentList getAllStudents()
  {
    return studentList;
  }

  /**
   * Returns the student with the given id from the studentList.
   *
   * @param id Student objects' ID enrolled in the course
   * @return Student object that matches the given ID
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
   * Returns the ID of the course.
   *
   * @return ID of the course that contains name, semester and group (E.g. SDJ1X)
   */
  public String getId()
  {
    return name + semester + group;
  }

  /**
   * Adds the given student to the studentList of the course.
   *
   * @param student object that must be added to the studentList
   */
  public void addStudent(Student student)
  {
    studentList.addStudent(student);
  }

  /**
   * Adds the given teacher to the teacherList of the class.
   * If course previously had 'placeholder' (no teacher assigned to the course), then replaces the placeholder.
   *
   * @param teacher object that must be added to the teacherList
   */
  public void addTeacher(Teacher teacher)
  {
    teacherList.addTeacher(teacher);
    teachersId = "";
    for (int i = 0; i < teacherList.getSize(); i++)
    {
      teachersId += teacherList.getAllTeachers().get(i).getId() + " ";
    }
    if (teacherList.getAllTeachers().get(0).getId().equals("-1"))
      removeTeacher(teacherList.getAllTeachers().get(0));
  }

  /**
   * Removes a student from the studentList of the course.
   *
   * @param student object that must be removed from the course
   */
  public void removeStudent(Student student)
  {
    studentList.removeStudent(student.getId());
  }

  /**
   * Removes a teacher from the teacherList of the course. If there is no teacher a placeholder teacher is given.
   *
   * @param teacher object that must be removed from the course
   */
  public void removeTeacher(Teacher teacher)
  {
    if (teacherList.getSize() == 1)
    {
      teacherList.removeTeacher(teacher.getId());
      teacherList.addTeacher(new Teacher("-1", "NO_TEACHER"));
    }
    else if (teacherList.getSize() > 1)
    {
      teacherList.removeTeacher(teacher.getId());
    }
    teachersId = "";
    for (int i = 0; i < teacherList.getSize(); i++)
    {
      teachersId += teacherList.getAllTeachers().get(i).getId() + " ";
    }
  }

  /**
   * Checks if two courses are the same.
   * Returns true if every field and data of the given class is the same as every field and data of the original course.
   * Returns false if the given object is an instance of the original course.
   * Returns false if the size of the studentList of the given course does not equal to the size of the studentList of the original course.
   * Returns false if the size of the teacherList of the given course does not equal to the size of the teacherList of the original course.
   * Returns false if the semester of the given course does not equal to the semester of the original course.
   * Returns false if the group of the given course does not equal to the group of the original course.
   * Returns false if the name of the given course does not equal to the name of the original course.
   * Returns false if the number of ects of the given course does not equal to the number of ects of the original course.
   *
   * @param obj of the Course class that we want to compare with
   * @return true if they are equal, otherwise false
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Course))
    {
      return false;
    }
    Course other = (Course) obj;
    if (studentList.getSize() != other.studentList.getSize())
      return false;
    if (teacherList.getSize() != other.teacherList.getSize())
      return false;
    return semester == other.semester && group.equals(other.group)
        && studentList.equals(other.studentList) && teacherList.equals(
        other.teacherList) && name.equals(other.name) && ects == other.ects;
  }

  /**
   * Displays all the data stored in the course.
   *
   * @return String containing all the infromation about the course (name, ECTS, semester, group, teachers, students)
   */
  public String toString()
  {
    String studentsSTR = "";
    String teachersSTR = "";
    for (int i = 0; i < studentList.getSize(); i++)
    {
      studentsSTR += studentList.getAllStudents().get(i) + "\n";
    }
    for (int i = 0; i < teacherList.getSize(); i++)
    {
      teachersSTR += teacherList.getAllTeachers().get(i) + "\n";
    }
    return "Name: " + name + "\nECTS: " + ects + "\nSemester: " + semester
        + "\nGroup: " + group + "\nStudents: " + studentsSTR + "\nTeachers: "
        + teachersSTR;
  }

  /**
   * Creates a copy object of the Course object.
   *
   * @return new object that contains the same values
   */
  public Course copy()
  {
    Course temp = new Course(name, semester, group, ects,
        teacherList.getAllTeachers().get(0));
    if (teacherList.getSize() > 1)
      for (int i = 1; i < teacherList.getSize(); i++)
        temp.addTeacher(teacherList.getAllTeachers().get(i));
    return temp;
  }

}

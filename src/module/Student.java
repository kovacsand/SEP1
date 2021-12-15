package module;

import java.io.Serializable;

/**
 * Class that contains all the data for Student. Extends Person class
 *
 * @author Hi-Phi
 * @version 1.0
 */
public class Student extends Person implements Serializable
{
  private int semester;
  private String group;

  /**
   * Initializing four-argument constructor
   *
   * @param id       of the student (person)
   * @param name     of the student (person)
   * @param semester of the student
   * @param group    of the student
   */
  public Student(String id, String name, int semester, String group)
  {
    super(id, name);
    this.semester = semester;
    this.group = group;
  }

  /**
   * Getting the semester of the student is attending
   *
   * @return semester of the student
   */
  public int getSemester()
  {
    return semester;
  }

  /**
   * Getting the group of the student is assigned to
   *
   * @return group of the student
   */
  public String getGroup()
  {
    return group;
  }

  /**
   * Setting the group of the student, in case they change the class
   *
   * @param group that the student needs to be assigned to
   */
  public void setGroup(String group)
  {
    this.group = group;
  }

  /**
   * Setting the semester of the student
   * @param semester the student must be assigned to
   */
  public void setSemester(int semester)
  {
    this.semester = semester;
  }

  /**
   * Checks if the objects are equal
   * @param obj of the Student that we want to compare with
   * @return true if they are equal, otherwise false
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Student))
    {
      return false;
    }
    Student other = (Student) obj;
    return super.equals(other) && semester == other.semester && group.equals(
        other.group);
  }

  /**
   * Creating a copy of the object with the same values
   * @return newly created Student object with the same values
   */
  public Student copy()
  {
    return new Student(getId(), getName(), semester, group);
  }

  /**
   * Writing all the information that the Student contains
   * @return super.toString() that contains the name and the ID of the student, and adding class with semester and group added up (E.g. 1X)
   */
  public String toString()
  {
    return super.toString() + " Class: " + semester + group;
  }

}

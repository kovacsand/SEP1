package module;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for storing all the Teacher objects within a ArrayList
 */
public class TeacherList implements Serializable
{
  private ArrayList<Teacher> teachers;

  /**
   * Zero-argument constructor initializing teacherList object and ArrayList
   */
  public TeacherList()
  {
    teachers = new ArrayList<Teacher>();
  }

  /**
   * Adding a teacher object to the ArrayList
   *
   * @param teacher object that must be added to the ArrayList
   */
  public void addTeacher(Teacher teacher)
  {
    teachers.add(teacher);
  }

  /**
   * Removing the teacher object from the ArrayList by its ID
   *
   * @param id of the teacher object that must be removed
   */
  public void removeTeacher(String id)
  {
    for (int i = 0; i < teachers.size(); i++)
    {
      Teacher teacher = teachers.get(i);
      if (teacher.getId().equals(id))
      {
        teachers.remove(teacher);
        break;
      }
    }
  }

  /**
   * Getting all the teachers within the ArrayList
   *
   * @return all the teachers within the ArrayList
   */
  public ArrayList<Teacher> getAllTeachers()
  {
    return teachers;
  }

  /**
   * Getting a teacher object by its ID
   *
   * @param id of the teacher that must be found
   * @return the teacher object if the given ID matches anyone within the list
   */
  public Teacher getTeacher(String id)
  {
    Teacher teacher = null;
    for (int i = 0; i < teachers.size(); i++)
    {
      if (teachers.get(i).getId().equals(id))
      {
        teacher = teachers.get(i);
        break;
      }
    }
    return teacher;
  }

  /**
   * Getting the size of the ArrayList
   *
   * @return the size of the ArrayList
   */
  public int getSize()
  {
    return getAllTeachers().size();
  }

  /**
   * Getting all teachers by their IDs and convert to String for XML file
   *
   * @return String that contains all the teachers IDs
   */
  public String getAllTeachersId()
  {
    String str = "";
    for (int i = 0; i < teachers.size(); i++)
    {
      if (i == teachers.size() - 1)
        str += teachers.get(i).getId();
      else
        str += teachers.get(i).getId() + ", ";
    }
    return str;
  }

  /**
   * Comparing the given TeacherList object
   *
   * @param obj of the Teacherlist we want to compare with
   * @return true if equal, otherwise false
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof TeacherList))
    {
      return false;
    }
    TeacherList other = (TeacherList) obj;
    return teachers.equals(other.teachers);
  }

  /**
   * Returns TeacherList in a String
   *
   * @return String that contains all the information about the ArrayList of teacher objects
   */
  public String toString()
  {
    String allTeachers = "";
    for (int i = 0; i < teachers.size(); i++)
    {
      allTeachers += teachers.get(i) + "\n";
    }
    return allTeachers;
  }

  /**
   * Creating a copy of the TeacherList object with the same values
   *
   * @return newly created TeacherList object with the same values
   */
  public TeacherList copy()
  {
    TeacherList teacherList = new TeacherList();
    for (int i = 0; i < teachers.size(); i++)
    {
      teacherList.addTeacher(teachers.get(i));
    }
    return teacherList;
  }

}

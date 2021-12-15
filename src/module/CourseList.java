package module;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class to contain all the Course objects in one ArrayList
 *
 * @author Hi-Phi
 * @version 1.0
 */
public class CourseList implements Serializable
{
  private ArrayList<Course> courses;

  /**
   * No-argument constructor initializing the courses ArrayList.
   */
  public CourseList()
  {
    courses = new ArrayList<>();
  }

  /**
   * Adds the course given as a parameter to the courses list.
   *
   * @param course object that must be added to the ArrayList
   */
  public void addCourse(Course course)
  {
    courses.add(course);
  }

  /**
   * Removes the course which has the name, semester, group given as a parameter from the classes list.
   *
   * @param semester of the class
   * @param group    of the class
   * @param name     of the course
   */
  public void removeCourse(String name, int semester, String group)
  {
    for (int i = 0; i < courses.size(); i++)
    {
      if (courses.get(i).getName().equals(name)
          && courses.get(i).getSemester() == semester && courses.get(i)
          .getGroup().equals(group))
      {
        courses.remove(courses.get(i));
      }
    }
  }

  /**
   * Returns all elements of the courses list.
   *
   * @return ArrayList containing all courses
   */
  public ArrayList<Course> getAllCourses()
  {
    return courses;
  }

  /**
   * Returns a new list of courses of the course list which has the semester given as a parameter.
   *
   * @param semester to find all courses for given semester
   * @return ArrayList that contains all the courses for the semester given
   */
  public ArrayList<Course> getAllCoursesBySemester(int semester)
  {
    ArrayList<Course> semesterCourses = new ArrayList<>();
    for (int i = 0; i < courses.size(); i++)
    {
      if (courses.get(i).getSemester() == semester)
      {
        semesterCourses.add(courses.get(i));
      }
    }
    return semesterCourses;
  }

  /**
   * Getting the size of the course list
   *
   * @return size of the ArrayList
   */
  public int getSize()
  {
    return courses.size();
  }

  /**
   * Checks if two courses lists are equal.
   *
   * @param obj to compare CourseList object we want to compare with
   * @return true if they are equal, otherwise false
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof CourseList))
    {
      return false;
    }
    CourseList other = (CourseList) obj;
    return courses.equals(other);
  }

  /**
   * Displays all the data stored in the course list.
   * Displays the name,semester, group, ects and teacher of each course in the course list.
   *
   * @return String that contains all the information abotu the coruse
   */
  public String toString()
  {
    String str = "";
    for (int i = 0; i < courses.size(); i++)
    {
      str += courses.get(i);
    }
    return str;
  }

  /**
   * Creates a new object with the same values.
   *
   * @return newly created CourseList object
   */
  public CourseList copy()
  {
    CourseList temp = new CourseList();
    for (int i = 0; i < courses.size(); i++)
    {
      temp.addCourse(courses.get(i));
    }
    return temp;
  }
}

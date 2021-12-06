package module;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseList implements Serializable
{
  private ArrayList<Course> courses;

  /**
   * No-argument constructor initializing the courses ArrayList.
   */
  public CourseList(){
    courses=new ArrayList<>();
  }

  /**
   * Adds the course given as a parameter to the courses list.
   * @param course
   */
  public void addCourse(Course course){
    courses.add(course);
  }

  /**
   * Removes the course which has the name, semester, group given as a parameter from the classes list.
   * @param semester
   * @param group
   * @param name
   */
  public void removeCourse(String name,int semester,String group){
    for(int i=0;i<courses.size();i++){
      if(courses.get(i).getName().equals(name) && courses.get(i).getSemester()==semester && courses.get(i).getGroup().equals(group)){
        courses.remove(courses.get(i));
      }
    }
  }

  /**
   * Returns all elements of the courses list.
   * @return courses
   */
  public ArrayList<Course> getAllCourses(){
    return courses;
  }

  /**
   * Returns a new list of courses of the course list which has the semester given as a parameter.
   * @param semester
   * @return semesterClasses
   */
  public ArrayList<Course> getAllCoursesBySemester(int semester){
    ArrayList<Course> semesterCourses=new ArrayList<>();
    for(int i=0;i<courses.size();i++){
      if(courses.get(i).getSemester()==semester){
        semesterCourses.add(courses.get(i));
      }
    }
    return semesterCourses;
  }

  /**
   * Returns the size of the course list
   * @return courses.size()
   */
  public int getSize(){
    return courses.size();
  }

  /**
   * Checks if two courses lists are equal.
   * Returns true if both class lists has the same elements, values and size.
   * @param obj
   * @return true or false
   */
  public boolean equals(Object obj){
    if(!(obj instanceof CourseList)){
      return false;
    }
    CourseList other=(CourseList) obj;
    return courses.equals(other);
  }

  /**
   * Displays all the data stored in the course list.
   * Displays the name,semester, group, ects and teacher of each course in the course list.
   * @return courses.get(i).getName(), courses.get(i).getSemester(), courses.get(i).getGroup(), courses.get(i).getEcts(), courses.get(i).getTeacher("")
   */
  public String toString(){
    String str="";
    for(int i=0;i<courses.size();i++){
      str=str+courses.get(i).getName()+" "+courses.get(i).getSemester()+" "+courses.get(i).getGroup()+courses.get(i).getEcts()+" "+courses.get(i).getTeacher("")+" ";
    }
    return str;
  }

  /**
   * Creates a copy object of the CourseList object.
   * Creates a new object with the same values.
   * @return temp
   */
  public CourseList copy(){
    CourseList temp=new CourseList();
    for(int i=0;i<courses.size();i++)
    {
      temp.addCourse(courses.get(i));
    }
      return temp;
  }

}

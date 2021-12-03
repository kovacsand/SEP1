package module;

import java.io.Serializable;

public class Class implements Serializable
{
  private int semester;
  private String group;
  private StudentList studentList;
  private CourseList courseList;
  /**
   * Two-argument constructor initializing the semester, group, studentList and courseList.
   * @author Klaudia Fanni Balog
   * @param semester,group
   */
  public Class(int semester,String group){
    this.semester=semester;
    this.group=group;
    studentList=new StudentList();
    courseList=new CourseList();
  }

  /**
   * Returns the semester number of the class.
   * @return semester
   */
  public int getSemester(){
    return semester;
  }

  /**
   * Returns the group name of the class.
   * @return group
   */
  public String getGroup(){
    return group;
  }

  /**
   * Returns the list of students of the class.
   * @return studentList
   */
  public StudentList getAllStudents(){
    return studentList;
  }

  /**
   * Returns the student with the given id from the studentList.
   * @param id
   * @return found
   */
  public Student getStudent(String id){
    Student found=null;
    for(int i=0;i<studentList.size();i++){
      if(studentList.students.get(i).id==id){
        found=studentList.students.get(i);
      }
    }
    return found;
  }

  /**
   * Returns the list of courses of the class.
   * @return courseList
   */
  public CourseList getAllCourses(){
    return courseList;
  }

  /**
   * Adds the given student to the studentList of the class.
   * @param student
   */
  public void addStudent(Student student){
    studentList.add(student);
  }

  /**
   * Adds the given list of students to the studentList of the class.
   * @param students
   */
  public void addStudents(StudentList students){
    for(int i=0;i<students.size();i++){
      studentList.add(students.get(i));
    }
  }

  /**
   * Adds the given course to the courseList of the class.
   * @param course
   */
  public void addCourse(Course course){
    studentList.add(course);
  }

  /**
   * Adds the given list of courses to the courseList of the class.
   * @param courses
   */
  public void addCourses(CourseList courses){
    for(int i=0;i<courses.size();i++){
      courseList.add(courses.get(i));
    }
  }

  /**
   * Removes a student from the studentList of the class.
   * @param student
   */
  public void removeStudent(Student student){
    studentList.remove(student);
  }

  /**
   * Checks if two classes are the same.
   * Returns true if every field and data of the given class is the same as every field and data of the original class.
   * Returns false if the given object is an instance of the original class.
   * Returns false if the size of the studentList of the given class does not equal to the size of the studentList of the original class.
   * Returns false if the size of the courseList of the given class does not equal to the size of the courseList of the original class.
   * Returns false if the semester of the given class does not equal to the semester of the original class.
   * Returns false if the group of the given class does not equal to the group of the original class.
   * @param obj
   * @return true or false
   */
  public boolean equals(Object obj){
    if(!(obj instanceof Class)){
      return false;
    }
    Class other=(Class) obj;
    if(studentList.size()!=other.studentList.size()) return false;
    if(courseList.size()!=other.courseList.size()) return false;
    return semester==other.semester && group.equals(other.group) &&
        studentList.equals(other.studentList) && courseList.equals(other.courseList);
  }

  /**
   * Displays all the data stored in the class.
   * @return semester, group, studentList, courseList
   */
  public String toString(){
    String studentsSTR="";
    String coursesSTR="";
    for(int i=0;i<studentList.size();i++){
      studentsSTR=studentsSTR+studentList.get(i)+"\n";
    }
    for(int i=0;i<courseList.size();i++){
      coursesSTR=coursesSTR+courseList.get(i)+"\n";
    }
    return "Semester: "+semester+"\nGroup: "+group+"\nStudents: "+studentsSTR
        +"\nCourses: "+coursesSTR;
  }

  /**
   * Creates a new Class object.
   * @return new Class
   */
  public Class copy(){
    return new Class(semester,group);
  }

}


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
    for(int i=0;i<studentList.getSize();i++){
      if(studentList.getAllStudents().get(i).getId().equals(id)){
        found=studentList.getAllStudents().get(i);
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
   * Returns the course with the given name from the courseList.
   * @param id
   * @return found
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
   * Adds the given student to the studentList of the class.
   * @param student
   */
  public void addStudent(Student student){
    studentList.getAllStudents().add(student);
  }

  /**
   * Adds the given list of students to the studentList of the class.
   * @param students
   */
  public void addStudents(StudentList students){
    for(int i=0;i<students.getSize();i++){
      studentList.getAllStudents().add(students.getAllStudents().get(i));
    }
  }

  /**
   * Adds the given course to the courseList of the class.
   * @param course
   */
  public void addCourse(Course course){
    courseList.getAllCourses().add(course);
  }

  /**
   * Adds the given list of courses to the courseList of the class.
   * @param courses
   */
  public void addCourses(CourseList courses){
    for(int i=0;i<courses.getSize();i++){
      courseList.getAllCourses().add(courses.getAllCourses().get(i));
    }
  }

  /**
   * Removes a student from the studentList of the class.
   * @param student
   */
  public void removeStudent(Student student){
    studentList.getAllStudents().remove(student);
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
    if(studentList.getSize()!=other.studentList.getSize()) return false;
    if(courseList.getSize()!=other.courseList.getSize()) return false;
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
    for(int i=0;i<studentList.getSize();i++){
      studentsSTR=studentsSTR+studentList.getAllStudents().get(i)+"\n";
    }
    for(int i=0;i<courseList.getSize();i++){
      coursesSTR=coursesSTR+courseList.getAllCourses().get(i)+"\n";
    }
    return "Semester: "+semester+"\nGroup: "+group+"\nStudents: "+studentsSTR
        +"\nCourses: "+coursesSTR;
  }

  /**
   * Creates a copy object of the Class object.
   * Creates a new object with the same values.
   * @return new Class(semester, group)
   */
  public Class copy(){
    Class temp = new Class(semester,group);
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


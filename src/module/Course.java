package module;

import java.io.Serializable;

public class Course implements Serializable
{
  private String name;
  private int semester;
  private String group;
  private int ects;
  private TeacherList teacherList;
  private StudentList studentList;
  private Teacher teacher;

  /**
   * Two-argument constructor initializing the name, semester, group, ects, studentList and teacherList.
   * @author Klaudia Fanni Balog
   * @param semester,group
   */
  public Course(String name,int semester,String group, int ects,Teacher teacher){
    this.name=name;
    this.semester=semester;
    this.group=group;
    this.ects=ects;
    this.teacher=teacher;
    teacherList=new TeacherList();
    studentList=new StudentList();
  }

  /**
   * Returns the name of the course.
   * @return name
   */
  public String getName(){
    return name;
  }

  /**
   * Returns the semester number of the course.
   * @return semester
   */
  public int getSemester(){
    return semester;
  }

  /**
   * Returns the group of the course.
   * @return group
   */
  public String getGroup(){
    return group;
  }

  /**
   * Returns the number of ECTS of the course.
   * @return ects
   */
  public int getEcts(){
    return ects;
  }

  /**
   * Returns the list of teachers of the course.
   * @return teacherList
   */
  public TeacherList getAllTeachers(){
    return teacherList;
  }

  /**
   * Returns the teacher with the given id from the teacherList.
   * @param id
   * @return found
   */
  public Teacher getTeacher(String id){
    Teacher found=null;
    for(int i=0;i<teacherList.size();i++){
      if(teacherList.teachers.get(i).id==id){
        found=teacherList.teachers.get(i);
      }
    }
    return found;
  }

  /**
   * Returns the list of students of the course.
   * @return studentList
   */
  public StudentList getAllStudents(){
    return studentList;
  }

  /**
   * Returns the student with the given id from the studentList.
   * @param id
   * @return
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
   * Returns the ID of the course.
   * @return name, semester, group
   */
  public String getId(){
    return name+semester+group;
  }

  /**
   * Adds the given student to the studentList of the class.
   * @param student
   */
  public void addStudent(Student student){
    studentList.add(student);
  }

  /**
   * Adds the given teacher to the teacherList of the class.
   * @param student
   */
  public void addTeacher(Teacher teacher){
    teacherList.add(teacher);
  }

  /**
   * Removes a student from the studentList of the class.
   * @param student
   */
  public void removeStudent(Student student){
    studentList.remove(student);
  }

  /**
   * Removes a teacher from the teacherList of the class.
   * @param student
   */
  public void removeTeacher(Teacher teacher){
    teacherList.remove(teacher);
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
   * @param obj
   * @return true or false
   */
  public boolean equals(Object obj){
    if(!(obj instanceof Course)){
      return false;
    }
    Course other=(Course) obj;
    if(studentList.size()!=other.studentList.size()) return false;
    if(teacherList.size()!=other.teacherList.size()) return false;
    return semester==other.semester && group.equals(other.group) &&
        studentList.equals(other.studentList) && teacherList.equals(other.teacherList)
        && name.equals(other.name) && ects==other.ects;
  }

  /**
   * Displays all the data stored in the course.
   * @return name, ects, semester, group, studentList, teacherList
   */
  public String toString(){
    String studentsSTR="";
    String teachersSTR="";
    for(int i=0;i<studentList.size();i++){
      studentsSTR=studentsSTR+studentList.get(i)+"\n";
    }
    for(int i=0;i<teacherList.size();i++){
      teachersSTR=teachersSTR+teacherList.get(i)+"\n";
    }
    return "Name: "+name+"\nECTS: "+ects+"\nSemester: "+semester+"\nGroup: "+group+"\nStudents: "+studentsSTR
        +"\nTeachers: "+teachersSTR;
  }

  /**
   * Creates a new Course object.
   * @return new Course
   */
  public Course copy(){
    return new Course(name,semester,group,ects,teacher);
  }

}

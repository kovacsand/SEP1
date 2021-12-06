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

  /**
   * Five-argument constructor initializing the name, semester, group, ects, studentList and teacherList. Adds the teacher to the teacherList.
   * @param name
   */
  public Course(String name,int semester,String group, int ects,Teacher teacher){
    this.name=name;
    this.semester=semester;
    this.group=group;
    this.ects=ects;
    teacherList=new TeacherList();
    studentList=new StudentList();
    teacherList.addTeacher(teacher);
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
    for(int i=0;i<teacherList.getSize();i++){
      if(teacherList.getAllTeachers().get(i).getId().equals(id)){
        found=teacherList.getAllTeachers().get(i);
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
    for(int i=0;i<studentList.getSize();i++){
      if(studentList.getAllStudents().get(i).getId().equals(id)){
        found=studentList.getAllStudents().get(i);
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
   * Adds the given student to the studentList of the course.
   * @param student
   */
  public void addStudent(Student student){
    studentList.addStudent(student);
  }

  /**
   * Adds the given teacher to the teacherList of the class.
   * @param teacher
   */
  public void addTeacher(Teacher teacher){
    teacherList.addTeacher(teacher);
  }

  /**
   * Removes a student from the studentList of the course.
   * @param student
   */
  public void removeStudent(Student student){
    studentList.removeStudent(student.getId());
  }

  /**
   * Removes a teacher from the teacherList of the course. If there is no teacher a placeholder teacher is given.
   * @param teacher
   */
  public void removeTeacher(Teacher teacher){
    if(teacherList.getSize()==1)
    {
      teacherList.removeTeacher(teacher.getId());
      teacherList.addTeacher(new Teacher("-1", "NO_TEACHER"));
    }
    else if(teacherList.getSize()>1){
      teacherList.removeTeacher(teacher.getId());
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
   * @param obj
   * @return true or false
   */
  public boolean equals(Object obj){
    if(!(obj instanceof Course)){
      return false;
    }
    Course other=(Course) obj;
    if(studentList.getSize()!=other.studentList.getSize()) return false;
    if(teacherList.getSize()!=other.teacherList.getSize()) return false;
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
    for(int i=0;i<studentList.getSize();i++){
      studentsSTR=studentsSTR+studentList.getAllStudents().get(i)+"\n";
    }
    for(int i=0;i<teacherList.getSize();i++){
      teachersSTR=teachersSTR+teacherList.getAllTeachers().get(i)+"\n";
    }
    return "Name: "+name+"\nECTS: "+ects+"\nSemester: "+semester+"\nGroup: "+group+"\nStudents: "+studentsSTR
        +"\nTeachers: "+teachersSTR;
  }

  /**
   * Creates a copy object of the Course object.
   * Creates a new object with the same values.
   * @return new Course(name, semester, group, ects, teacher)
   */
  public Course copy(){
    Course temp = new Course(name,semester,group,ects, teacherList.getAllTeachers().get(0));
    if (teacherList.getSize() > 1)
      for (int i = 1; i < teacherList.getSize(); i++)
        temp.addTeacher(teacherList.getAllTeachers().get(i));
    return temp;
  }

}

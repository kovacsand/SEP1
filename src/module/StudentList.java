package module;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentList implements Serializable
{
  private ArrayList<Student> students;

  public StudentList()
  {
    students = new ArrayList<Student>();
  }

  public void addStudent(Student student)
  {
    students.add(student);
  }

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

  public ArrayList<Student> getAllStudents()
  {
    return students;
  }

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

  public String getAllStudentIds()
  {
    String str = "";
    for (int i = 0; i < students.size(); i++)
    {
      if(i == students.size() - 1)
        str += students.get(i).getId();
      else
        str += students.get(i).getId() + ", ";
    }
    return str;
  }

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

  public Student getStudent(String id)
  {
  Student student = null;
    for (int i = 0; i < students.size(); i++)
    {

      if(students.get(i).getId().equals(id))
      {
        student=students.get(i);
        break;
      }
    }
    return student;
  }

  public int getSize()
  {
    return getAllStudents().size();
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof StudentList))
    {
      return false;
    }
    StudentList other = (StudentList) obj;
    return students.equals(other.students);
  }

  public String toString()
  {
    String allStudents = "";
    for (int i = 0; i < students.size(); i++)
    {
      allStudents += students.get(i) + "\n";
    }
    return allStudents;
  }

  public StudentList copy()
  {
    StudentList studentList=new StudentList();
    for (int i=0;i<students.size();i++)
    {
      studentList.addStudent(students.get(i));
    }
    return studentList;
  }

}

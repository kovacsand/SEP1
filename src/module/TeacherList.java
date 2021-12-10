package module;

import java.io.Serializable;
import java.util.ArrayList;

public class TeacherList implements Serializable
{
  private ArrayList<Teacher> teachers;

  public TeacherList()
  {
    teachers = new ArrayList<Teacher>();
  }

  public void addTeacher(Teacher teacher)
  {
    teachers.add(teacher);
  }

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

  public ArrayList<Teacher> getAllTeachers()
  {
    return teachers;
  }

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

  public int getSize()
  {
    return getAllTeachers().size();
  }

  public String getAllTeachersId()
  {
    String str = "";
    for (int i = 0; i < teachers.size(); i++)
    {
      if(i == teachers.size() - 1)
        str += teachers.get(i).getId();
      else
        str += teachers.get(i).getId() + ", ";
    }
    return str;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof TeacherList))
    {
      return false;
    }
    TeacherList other=(TeacherList) obj;
    return teachers.equals(other.teachers);
  }

  public String toString()
  {
    String allTeachers="";
    for(int i=0;i<teachers.size();i++)
    {
      allTeachers+=teachers.get(i)+"\n";
    }
    return allTeachers;
  }

  public TeacherList copy()
  {
    TeacherList teacherList=new TeacherList();
    for (int i=0;i<teachers.size();i++)
    {
      teacherList.addTeacher(teachers.get(i));
    }
    return teacherList;
  }


}

package module;

import java.io.Serializable;

public class Student extends Person implements Serializable
{
  private int semester;
  private String group;

  public Student(String id,String name,int semester,String group)
  {
    super(id,name);
    this.semester=semester;
    this.group=group;
  }

  public int getSemester()
  {
    return semester;
  }
  public String getGroup()
  {
    return group;
  }

  public void setGroup(String group)
  {
    this.group=group;
  }
  public void setSemester(int semester)
  {
    this.semester=semester;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Student))
    {
      return false;
    }
    Student other=(Student)obj;
    return super.equals(other) && semester==other.semester && group.equals(other.group);
  }

  public Student copy()
  {
    return new Student(getId(),getName(),semester,group);
  }


  public String toString()
  {
    return super.toString() + " Class: " + semester + group;
  }

}

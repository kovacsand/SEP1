package module;

import java.util.ArrayList;

public class ClassroomList
{
  private ArrayList<Classroom> classrooms;

  public ClassroomList()
  {
    classrooms = new ArrayList<>();
  }

  public void addClassroom(Classroom classroom)
  {
    classrooms.add(classroom);
  }

  public void removeClassroom(String name)
  {
    for (int i = 0; i < classrooms.size(); i++)
      if (classrooms.get(i).getName().equals(name))
        classrooms.remove(i);
  }

  public ArrayList<Classroom> getAllClassrooms()
  {
    return classrooms;
  }

  public ArrayList<Classroom> getClassrooms(int capacity)
  {
    ArrayList<Classroom> temp = new ArrayList<>();
    for (int i = 0; i < classrooms.size(); i++)
      if (classrooms.get(i).getCapacity() >= capacity)
        temp.add(classrooms.get(i));
    return temp;
  }

  public Classroom getClassroom(String name)
  {
    for (int i = 0; i < classrooms.size(); i++)
      if (classrooms.get(i).getName().equals(name))
        return classrooms.get(i);
    return null;
  }

  public int getSize()
  {
    return classrooms.size();
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof ClassroomList))
      return false;
    ClassroomList other = (ClassroomList) obj;
    return classrooms.equals(other.classrooms);
  }

  public String toString()
  {
    String temp = "";
    for (int i = 0; i < classrooms.size(); i++)
      temp += classrooms.get(i) + "\n";
    return temp;
  }

  public ClassroomList copy()
  {
    ClassroomList temp = new ClassroomList();
    for (int i = 0; i < classrooms.size(); i++)
      temp.addClassroom(classrooms.get(i));
    return temp;
  }
}

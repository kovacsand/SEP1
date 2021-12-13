package module;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassroomList implements Serializable
{
  /** A class for storing all the classrooms.
   * @param classrooms An ArrayList containing all the classrooms.
   */
  private ArrayList<Classroom> classrooms;

  /**
   * No-argument constructor initializing an empty ArrayList
   */
  public ClassroomList()
  {
    classrooms = new ArrayList<>();
  }

  /**
   * Adds a classroom to the ArrayList.
   * @param classroom the classroom to be added.
   */
  public void addClassroom(Classroom classroom)
  {
    classrooms.add(classroom);
  }

  /**
   * Removes a classroom from the ArrayList.
   * @param name the name of the classroom to be removed.
   */
  public void removeClassroom(String name)
  {
    for (int i = 0; i < classrooms.size(); i++)
      if (classrooms.get(i).getName().equals(name))
        classrooms.remove(i);
  }

  /**
   * Gets all the classrooms from the ArrayList.
   * @return the ArrayList containing all the classrooms.
   */
  public ArrayList<Classroom> getAllClassrooms()
  {
    return classrooms;
  }

  /**
   * Gets all the classroom that have at least the specified capacity.
   * @param capacity the minimum capacity of the wanted classrooms.
   * @return All the classrooms that have at least the specified capacity.
   */
  public ArrayList<Classroom> getClassrooms(int capacity)
  {
    ArrayList<Classroom> temp = new ArrayList<>();
    for (int i = 0; i < classrooms.size(); i++)
      if (classrooms.get(i).getCapacity() >= capacity)
        temp.add(classrooms.get(i));
    return temp;
  }

  /**
   * Gets the classroom by name from the classrooms.
   * @param name the name of the wanted classroom.
   * @return the wanted Classroom object from the ArrayList.
   */
  public Classroom getClassroom(String name)
  {
    for (int i = 0; i < classrooms.size(); i++)
      if (classrooms.get(i).getName().equals(name))
        return classrooms.get(i);
    return null;
  }

  /**
   * Gets the number of classrooms.
   * @return the size of the ArrayList.
   */
  public int getSize()
  {
    return classrooms.size();
  }

  /**
   * Compares two Classroom objects.
   * @param obj the Classroom we want to compare with.
   * @return true if they are equal, false otherwise.
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof ClassroomList))
      return false;
    ClassroomList other = (ClassroomList) obj;
    return classrooms.equals(other.classrooms);
  }

  /**
   * Converts Classroom into a String.
   * @return the String format
   */
  public String toString()
  {
    String temp = "";
    for (int i = 0; i < classrooms.size(); i++)
      temp += classrooms.get(i) + "\n";
    return temp;
  }

  /**
   * Copies a ClassroomList object.
   * @return the newly created ClassroomList object, which has the same values
   */
  public ClassroomList copy()
  {
    ClassroomList temp = new ClassroomList();
    for (int i = 0; i < classrooms.size(); i++)
      temp.addClassroom(classrooms.get(i));
    return temp;
  }
}

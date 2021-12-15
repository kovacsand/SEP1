package module;

import java.io.Serializable;

/**
 * Class for storing the data for Teacher objects
 *
 * @author Hi-Phi
 * @version 1.0
 */
public class Teacher extends Person implements Serializable
{
  /**
   * Initializing two-argument constructor to create a new Teacher object
   *
   * @param id   of the teacher (person)
   * @param name of the teacher (person)
   */
  public Teacher(String id, String name)
  {
    super(id, name);
  }

  /**
   * Comparing the given Teacher object if they are equal
   *
   * @param obj of the Teacher that we want to compare with
   * @return true if equal, otherwise false
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Teacher))
    {
      return false;
    }
    Teacher other = (Teacher) obj;
    return super.equals(other);
  }

  /**
   * Creating a copy of Teacher object with the same values
   *
   * @return newly created Teacher object
   */
  public Teacher copy()
  {
    return new Teacher(getId(), getName());
  }
}


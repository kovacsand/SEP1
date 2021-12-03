package module;

/**
 * A class for storing data about classrooms.
 * @author Hi-Phi
 * @version 1.0
 */


import java.util.ArrayList;

public class Classroom
{

  /**
   * @param name The name of the classroom (example: C05.15)
   * @param capacity The maximum capacity of the classroom (example: 40)
   * @param occupiedHours The hours when the classroom is occupied. It is stored as an ArrayList of Strings, every element is 14 characters long,
   *                      first 4 is starting time (0820), second 4 is ending time (1145), the next 6 are the date (010921)
   */
  private String name;
  private int capacity;
  private ArrayList<String> occupiedHours;

  /**
   * Two-argument constructor initializing the MyDate object, it is always free by default.
   * @param name the name of the classroom.
   * @param capacity the maximum capacity of the classroom.
   */
  public Classroom(String name, int capacity)
  {
    this.name = name;
    this.capacity = capacity;
    occupiedHours = new ArrayList<>();
  }

  /**
   * Gets the name of the classroom.
   * @return the name of the Classroom.
   */
  public String getName()
  {
    return name;
  }

  /**
   * Gets the capacity of the classroom.
   * @return the capacity of the Classroom.
   */
  public int getCapacity()
  {
    return capacity;
  }

  /**
   * Checks if the classroom is free on the given date in the given period of time.
   * @param date the given date.
   * @param interval the given time period.
   * @return true if the classroom is free, false otherwise.
   */
  public boolean isFree(MyDate date, TimeInterval interval)
  {
    String time = String.format("%02d%02d%02d%04d%04d", date.getDay(), date.getMonth(), date.getYear(), interval.getStartTime(), interval.getEndTime());
    for (int i = 0; i < occupiedHours.size(); i++)
    {
      if (occupiedHours.get(i).equals(time))
        return false;
    }
    return true;
  }

  /**
   * Sets the classroom occupied for the given period of time.
   * @param date the given date.
   * @param interval the given time period.
   */
  public void addOccupiedHours(MyDate date, TimeInterval interval)
  {
    String time = String.format("%02d%02d%02d%04d%04d", date.getDay(), date.getMonth(), date.getYear(), interval.getStartTime(), interval.getEndTime());
    occupiedHours.add(time);
  }

  //This is so that we can use the formatted String
  private void addOccupiedHours(String time)
  {
    occupiedHours.add(time);
  }

  /**
   * Sets the classroom free for the given period of time.
   * @param date the given date.
   * @param interval the given time period.
   */
  public void removeOccupiedHours(MyDate date, TimeInterval interval)
  {
    String time = String.format("%02d%02d%02d%04d%04d", date.getDay(), date.getMonth(), date.getYear(), interval.getStartTime(), interval.getEndTime());
    occupiedHours.remove(time);
  }

  /**
   * Compares two Classroom objects.
   * @param obj the Classroom we want to compare with.
   * @return true if they are equal, false otherwise.
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Classroom))
      return false;
    Classroom other = (Classroom) obj;
    return name.equals(other.name) && capacity == other.capacity && occupiedHours.equals(other.occupiedHours);
  }

  /**
   * Converts Classroom into a String.
   * @return the String format (example: C05.15 - 40)
   */
  public String toString()
  {
    return String.format("%s - Capacity: %d", name, capacity);
  }

  /**
   * Copies a Classroom object.
   * @return the newly created Classroom object, which has the same values
   */
  public Classroom copy()
  {
    Classroom classroom = new Classroom(name, capacity);
    for (int i = 0; i < occupiedHours.size(); i++)
      classroom.addOccupiedHours(occupiedHours.get(i));
    return classroom;
  }
}

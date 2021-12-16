package module;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class for storing data about classrooms.
 *
 * @author Hi-Phi
 * @version 1.0
 */
public class Classroom implements Serializable
{

  private String name;
  private int capacity;
  private ArrayList<String> occupiedHours;

  /**
   * Two-argument constructor initializing the MyDate object, it is always free by default.
   *
   * @param name     the name of the classroom.
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
   *
   * @return the name of the Classroom.
   */
  public String getName()
  {
    return name;
  }

  /**
   * Gets the capacity of the classroom.
   *
   * @return the capacity of the Classroom.
   */
  public int getCapacity()
  {
    return capacity;
  }

  /**
   * Checks if the classroom is free on the given date in the given period of time.
   *
   * @param date     the given date.
   * @param interval the given time period.
   * @return true if the classroom is free, false otherwise.
   */
  public boolean isFree(MyDate date, TimeInterval interval)
  {
    for (int i = 0; i < occupiedHours.size(); i++)
    {
      String tempTime = occupiedHours.get(i);
      String tempDateString = tempTime.substring(0, 6);
      String tempTimeIntervalString = tempTime.substring(6, 14);

      MyDate tempDate = new MyDate(Integer.parseInt(tempDateString.substring(0, 2)),
          Integer.parseInt(tempDateString.substring(2, 4)), Integer.parseInt(tempDateString.substring(4, 6)));
      TimeInterval tempTimeInterval = new TimeInterval(
          Integer.parseInt(tempTimeIntervalString.substring(0, 4)), Integer.parseInt(tempTimeIntervalString.substring(4, 8)));

      if (date.equals(tempDate))
      {
        //They are on the same day
        if (tempTimeInterval.getStartTime() <= interval.getStartTime()
            && interval.getStartTime() <= tempTimeInterval.getEndTime())
        {
          //New one starts during old one
          return false;
        }
        if (tempTimeInterval.getStartTime() <= interval.getEndTime()
            && interval.getEndTime() <= tempTimeInterval.getEndTime())
        {
          //New one ends during old one
          return false;
        }
        if (((interval.getStartTime() <= tempTimeInterval.getStartTime()) && (
            tempTimeInterval.getStartTime() <= interval.getEndTime())) &&
            //start of new <= end of old <= end of new
            ((interval.getStartTime() <= tempTimeInterval.getEndTime()) && (
                tempTimeInterval.getEndTime() <= interval.getEndTime())))
        {
          //Old one starts and ends during new one
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Sets the classroom occupied for the given period of time.
   *
   * @param date     the given date.
   * @param interval the given time period.
   */
  public void addOccupiedHours(MyDate date, TimeInterval interval)
  {
    String time = String.format("%02d%02d%02d%04d%04d", date.getDay(),
        date.getMonth(), date.getYear() % 100, interval.getStartTime(),
        interval.getEndTime());
    occupiedHours.add(time);
  }

  //This is so that we can use the formatted String
  private void addOccupiedHours(String time)
  {
    occupiedHours.add(time);
  }

  /**
   * Sets the classroom free for the given period of time.
   *
   * @param date     the given date.
   * @param interval the given time period.
   */
  public void removeOccupiedHours(MyDate date, TimeInterval interval)
  {
    String time = String.format("%02d%02d%02d%04d%04d", date.getDay(),
        date.getMonth(), date.getYear() % 100, interval.getStartTime(),
        interval.getEndTime());
    occupiedHours.remove(time);
  }

  /**
   * Compares two Classroom objects.
   *
   * @param obj the Classroom we want to compare with.
   * @return true if they are equal, false otherwise.
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Classroom))
      return false;
    Classroom other = (Classroom) obj;
    return name.equals(other.name) && capacity == other.capacity
        && occupiedHours.equals(other.occupiedHours);
  }

  /**
   * Converts Classroom into a String.
   *
   * @return the String format (example: C05.15 - 40)
   */
  public String toString()
  {
    return String.format("%s - Capacity: %d", name, capacity);
  }

  /**
   * Copies a Classroom object.
   *
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

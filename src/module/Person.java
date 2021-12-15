package module;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class containing all the information about Person (Superclass to Student and Teacher classes)
 *
 * @author Hi-Phi
 * @version 1.0
 */
public abstract class Person implements Serializable
{
  private String name;
  private String id;
  private ArrayList<String> workingHours;

  /**
   * Two-argument constructor that also initializes ArrayList for working hours (for students it is meant as learning hours)
   *
   * @param id   of the person
   * @param name of the person
   */
  public Person(String id, String name)
  {
    this.name = name;
    this.id = id;
    workingHours = new ArrayList<>();
  }

  /**
   * Getting an ID of the person
   *
   * @return the ID of the person
   */
  public String getId()
  {
    return id;
  }

  /**
   * Getting the name of the person
   *
   * @return the name of the person
   */
  public String getName()
  {
    return name;
  }

  /**
   * Setting the name of the person
   *
   * @param name that we want to change to
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Checks if the person is free at the given date and time interval
   *
   * @param date     date object
   * @param interval time interval of the given date
   * @return true if the person is free at the given timeframe, otherwise false
   */
  public boolean isFree(MyDate date, TimeInterval interval)
  {
    for (int i = 0; i < workingHours.size(); i++)
    {
      String tempTime = workingHours.get(i);
      String tempDateString = tempTime.substring(0, 6);
      String tempTimeIntervalString = tempTime.substring(6, 14);

      MyDate tempDate = new MyDate(
          Integer.parseInt(tempDateString.substring(0, 2)),
          Integer.parseInt(tempDateString.substring(2, 4)),
          Integer.parseInt(tempDateString.substring(4, 6)));
      TimeInterval tempTimeInterval = new TimeInterval(
          Integer.parseInt(tempTimeIntervalString.substring(0, 4)),
          Integer.parseInt(tempTimeIntervalString.substring(4, 8)));

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
   * Adding working/studying hours to the person object
   *
   * @param date         date object when the hours are needed to be added
   * @param timeInterval time frame within the given date
   */

  public void addWorkingHours(MyDate date, TimeInterval timeInterval)
  {
    String time = String.format("%02d%02d%02d%04d%04d", date.getDay(),
        date.getMonth(), date.getYear() % 100, timeInterval.getStartTime(),
        timeInterval.getEndTime());
    workingHours.add(time);
  }

  /**
   * Removing working/studying hours from the person object
   *
   * @param date         date object when the hours are needed to be removed
   * @param timeInterval time frame within the given date
   */
  public void removeWorkingHours(MyDate date, TimeInterval timeInterval)
  {
    for (int i = 0; i < workingHours.size(); i++)
    {
      String time = String.format("%02d%02d%02d%04d%04d", date.getDay(),
          date.getMonth(), date.getYear() % 100, timeInterval.getStartTime(),
          timeInterval.getEndTime());
      if (workingHours.get(i).equals(time))
      {
        workingHours.remove(time);
      }
    }
  }

  /**
   * Comparing two objects if they are the same
   *
   * @param obj of the Person that we want to compare with
   * @return true if they are equal, otherwise false
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Person))
    {
      return false;
    }
    Person other = (Person) obj;
    return id.equals(other.id) && name.equals(other.name)
        && workingHours.equals(other.workingHours);
  }

  /**
   * Returning information about the person
   *
   * @return String that contains the name and the ID of the person
   */
  public String toString()
  {
    return "Name: " + name + " ID: " + id;
  }

  public abstract Person copy();

}

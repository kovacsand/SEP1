package module;

import java.io.Serializable;

/**
 * A class for storing time intervals.
 *
 * @author Hi-Phi
 * @version 1.0
 */

public class TimeInterval implements Serializable
{
  private int startTime;
  private int endTime;

  /**
   * Two-argument constructor initializing the MyDate object.
   */
  public TimeInterval(int startTime, int endTime)
  {
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Gets the starting time from the interval.
   *
   * @return the startTime of the TimeInterval.
   */
  public int getStartTime()
  {
    return startTime;
  }

  /**
   * Gets the ending time from the interval.
   *
   * @return the endTime of the TimeInterval.
   */
  public int getEndTime()
  {
    return endTime;
  }

  /**
   * Compares two TimeInterval objects.
   *
   * @param obj the MyDate we want to compare with.
   * @return true if they are equal, false otherwise.
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof TimeInterval))
      return false;
    TimeInterval other = (TimeInterval) obj;
    return startTime == other.startTime && endTime == other.endTime;
  }

  /**
   * Converts TimeInterval into a String.
   *
   * @return the String format (example: 08:00 - 09:20, or: 16:30 - 17:45)
   */
  public String toString()
  {
    int startMinute = startTime % 100;
    int startHour = (startTime - startMinute) / 100;
    int endMinute = endTime % 100;
    int endHour = (endTime - endMinute) / 100;
    return String.format("%02d:%02d - %02d:%02d", startHour, startMinute,
        endHour, endMinute);
  }

  /**
   * Copies a TimeInterval object.
   *
   * @return the newly created TimeInterval object, which has the same values
   */
  public TimeInterval copy()
  {
    return new TimeInterval(startTime, endTime);
  }
}
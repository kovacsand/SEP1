package module;

import java.io.Serializable;

/**
 * A class for storing dates.
 *
 * @author Hi-Phi
 * @version 1.0
 */

public class MyDate implements Serializable
{
  private int day;
  private int month;
  private int year;

  /**
   * Three-argument constructor initializing the MyDate object.
   *  @param day The day of the date.
   *  @param month The month of the date.
   *  @param year The last two digits of the year of the date.
   */
  public MyDate(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year % 100;
  }

  /**
   * Gets the day from the date.
   *
   * @return the day of the MyDate.
   */
  public int getDay()
  {
    return day;
  }

  /**
   * Sets the day to the date.
   *
   * @param day the day to be set
   */
  public void setDay(int day)
  {
    this.day = day;
  }

  /**
   * Gets the month from the date.
   *
   * @return the month of the MyDate.
   */
  public int getMonth()
  {
    return month;
  }

  /**
   * Sets the month to the date.
   *
   * @param month the month to be set
   */
  public void setMonth(int month)
  {
    this.month = month;
  }

  /**
   * Gets the year from the date.
   *
   * @return the year of the MyDate.
   */
  public int getYear()
  {
    return year;
  }

  /**
   * Sets the year to the date.
   *
   * @param year the day to be set
   */
  public void setYear(int year)
  {
    this.year = year % 100;
  }

  /**
   * Compares two MyDate objects.
   *
   * @param obj the MyDate we want to compare with.
   * @return true if they are equal, false otherwise.
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof MyDate))
      return false;
    MyDate other = (MyDate) obj;
    return day == other.day && month == other.month && year == other.year;
  }

  /**
   * Converts MyDate into a String
   *
   * @return the String format (example: 24/12/21, or: 03/04/05)
   */
  public String toString()
  {
    return String.format("%02d/%02d/%02d", day, month, year % 100);
  }

  /**
   * Copies a MyDate object
   *
   * @return the newly created MyDate object, which has the same values
   */
  public MyDate copy()
  {
    return new MyDate(day, month, year);
  }
}

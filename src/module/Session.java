package module;

import java.io.Serializable;

/**
 * A class for storing data about sessions, which are the individual lessons.
 * @author Hi-Phi
 * @version 1.0
 */


public class Session implements Serializable
{
  /**
   * @param date The date of the session (example: 06/12/21)
   * @param interval The exact starting and ending time of the session
   * @param room The classroom where the session is held
   * @param course The course that is thought during the session
   */
  private MyDate date;
  private TimeInterval interval;
  private Classroom room;
  private Course course;

  /**
   * Four-argument constructor initializing the Session object with the given values.
   * @param date the date of the session.
   * @param interval the exact starting and ending time of the session.
   * @param room the classroom where the session is held.
   * @param course the course that is thought during the session.
   */
  public Session(MyDate date, TimeInterval interval, Classroom room, Course course)
  {
    this.date = date;
    this.interval = interval;
    this.room = room;
    this.course = course;
  }

  /**
   * The only way to change the fields of a Session. It takes all four arguments.
   * @param date the date of the session.
   * @param interval the exact starting and ending time of the session.
   * @param room the classroom where the session is held.
   * @param course the course that is thought during the session.
   */
  public void setSession(MyDate date, TimeInterval interval, Classroom room, Course course)
  {
    this.date = date;
    this.interval = interval;
    this.room = room;
    this.course = course;
  }

  /**
   * Gets the date of the session.
   * @return the date of the Session.
   */
  public MyDate getDate()
  {
    return date;
  }

  /**
   * Gets the exact start and end time of the session.
   * @return the interval os the Session.
   */
  public TimeInterval getInterval()
  {
    return interval;
  }

  /**
   * Gets the classroom of the session.
   * @return the classroom of the Session.
   */
  public Classroom getRoom()
  {
    return room;
  }

  /**
   * Gets the course of the session.
   * @return the course of the Session.
   */
  public Course getCourse()
  {
    return course;
  }

  /**
   * Gets the id of the session, that is its date, interval, course's name, the class' semester and group together (example: 06122108001200SDJ1X)
   * @return a String that identifies the Session.
   */
  public String getId()
  {
    return String.format("%02d%02d%02d%04d%04d%s%d%s",
        date.getDay(), date.getMonth(), date.getYear() % 100, interval.getStartTime(), interval.getEndTime(),
        course.getName(), course.getSemester(), course.getGroup());
  }

  /**
   * Compares two Session objects.
   * @param obj the Session we want to compare with.
   * @return true if they are equal, false otherwise.
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Session))
      return false;
    Session other = (Session) obj;
    return date.equals(other.date) && interval.equals(other.interval) && room.equals(other.room) && course.equals(other.course);
  }

  /**
   * Converts Session into a String.
   * @return the String format
   */
  public String toString()
  {
    return String.format("%s %s %s %s", date, interval, course, room.getName());
  }

  /**
   * Copies a Session object.
   * @return the newly created Session object, which has the same values
   */
  public Session copy()
  {
    return new Session(date, interval, room, course);
  }
}

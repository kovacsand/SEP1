package module;

/**
 * A class for storing data about sessions.
 * @author Hi-Phi
 * @version 1.0
 */


public class Session
{
  private MyDate date;
  private TimeInterval interval;
  private Classroom room;
  private Course course;

  public Session(MyDate date, TimeInterval interval, Classroom room, Course course)
  {
    this.date = date;
    this.interval = interval;
    this.room = room;
    this.course = course;
  }

  public void setSession(MyDate date, TimeInterval interval, Classroom room, Course course)
  {
    this.date = date;
    this.interval = interval;
    this.room = room;
    this.course = course;
  }

  public MyDate getDate()
  {
    return date;
  }

  public TimeInterval getInterval()
  {
    return interval;
  }

  public Classroom getRoom()
  {
    return room;
  }

  public Course getCourse()
  {
    return course;
  }

  public String getId()
  {
    return String.format("%02d%02d%02d%04d%04d%s%d%s",
        date.getDay(), date.getMonth(), date.getYear(), interval.getStartTime(), interval.getEndTime(),
        course.getName(), course.getSemester(), course.getGroup());
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Session))
      return false;
    Session other = (Session) obj;
    return date.equals(other.date) && interval.equals(other.interval) && room.equals(other.room) && course.equals(other.course);
  }

  public String toString()
  {
    return String.format("%s %s %s %s", date, interval, course, room.getName());
  }

  public Session copy()
  {
    return new Session(date, interval, room, course);
  }
}

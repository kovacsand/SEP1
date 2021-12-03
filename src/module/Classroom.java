package module;

import java.util.ArrayList;

public class Classroom
{
  private String name;
  private int capacity;
  private ArrayList<String> occupiedHours;

  public Classroom(String name, int capacity)
  {
    this.name = name;
    this.capacity = capacity;
    occupiedHours = new ArrayList<>();
  }

  public String getName()
  {
    return name;
  }

  public int getCapacity()
  {
    return capacity;
  }

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

  public void addOccupiedHours(MyDate date, TimeInterval interval)
  {
    String time = String.format("%02d%02d%02d%04d%04d", date.getDay(), date.getMonth(), date.getYear(), interval.getStartTime(), interval.getEndTime());
    occupiedHours.add(time);
  }

  private void addOccupiedHours(String time)
  {
    occupiedHours.add(time);
  }

  public void removeOccupiedHours(MyDate date, TimeInterval interval)
  {
    String time = String.format("%02d%02d%02d%04d%04d", date.getDay(), date.getMonth(), date.getYear(), interval.getStartTime(), interval.getEndTime());
    occupiedHours.remove(time);
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Classroom))
      return false;
    Classroom other = (Classroom) obj;
    return name.equals(other.name) && capacity == other.capacity && occupiedHours.equals(other.occupiedHours);
  }

  public String toString()
  {
    return String.format("%s - Capacity: %d", name, capacity);
  }

  public Classroom copy()
  {
    Classroom classroom = new Classroom(name, capacity);
    for (int i = 0; i < occupiedHours.size(); i++)
      classroom.addOccupiedHours(occupiedHours.get(i));
    return classroom;
  }
}

package module;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Person implements Serializable
{
  private String name;
  private String id;
  private ArrayList<String> workingHours;

  public Person(String id, String name)
  {
    this.name = name;
    this.id = id;
    workingHours = new ArrayList<>();
  }

  public String getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public boolean isFree(MyDate date, TimeInterval interval)
  {
    //String time = String.format("%02d%02d%02d%04d%04d", date.getDay(), date.getMonth(), date.getYear() % 100, interval.getStartTime(), interval.getEndTime());
    for (int i = 0; i < workingHours.size(); i++)
    {
      String tempTime = workingHours.get(i);
      String tempDateString = tempTime.substring(0, 6);
      String tempTimeIntervalString = tempTime.substring(6, 14);

      MyDate tempDate = new MyDate(Integer.parseInt(tempDateString.substring(0, 2)),
          Integer.parseInt(tempDateString.substring(2, 4)), Integer.parseInt(tempDateString.substring(4, 6)));
      TimeInterval tempTimeInterval = new TimeInterval(Integer.parseInt(tempTimeIntervalString.substring(0, 4)),
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


  public void addWorkingHours(MyDate date,TimeInterval timeInterval)
  {
    String time=String.format("%02d%02d%02d%04d%04d",date.getDay(),date.getMonth(),date.getYear()%100,timeInterval.getStartTime(),timeInterval.getEndTime());
    workingHours.add(time);
  }

  public void removeWorkingHours(MyDate date,TimeInterval timeInterval)
  {
    for (int i=0;i<workingHours.size();i++)
    {
      String time=String.format("%02d%02d%02d%04d%04d",date.getDay(),date.getMonth(),date.getYear()%100,timeInterval.getStartTime(),timeInterval.getEndTime());
      if(workingHours.get(i).equals(time))
      {
        workingHours.remove(time);
      }
    }
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Person))
    {
      return false;
    }
    Person other=(Person)obj;
    return id.equals(other.id) && name.equals(other.name) && workingHours.equals(other.workingHours);
  }

  public String toString()
  {
    return "Name: "+name+" ID: "+id;
  }

  public abstract Person copy();




}

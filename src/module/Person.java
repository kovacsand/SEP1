package module;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Person implements Serializable
{
  private String name;
  private String id;
  private ArrayList workingHours;


  public Person(String id, String name)
  {
    this.name=name;
    this.id=id;
    ArrayList<String> workingHours = new ArrayList<>();
  }

  public String getId()
  {
    return id;
  }
  public String getName()
  {
    return name;
  }

  public abstract void setName(String name);

  public boolean isFree(MyDate date, TimeInterval timeInterval)
  { boolean isFree=false;
    for(int i=0;i<workingHours.size();i++)
    {
      String time=String.format("%02d%02d%02d%04d%04d",date.getDay(),date.getMonth(),date.getYear()%100,timeInterval.getStartTime(),timeInterval.getEndTime());
      if(workingHours.get(i).equals(time))
      {
        isFree=false;
      }
      else
      {
        isFree=true;
      }
    }
    return isFree;
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

package module;

import java.io.Serializable;

public class Teacher extends Person implements Serializable
{
  public Teacher(String id, String name)
  {
    super(id,name);
  }
  public void setName(String name)
  {
    name=name;
  }
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Teacher))
    {
      return false;
    }
    Teacher other=(Teacher)obj;
    return super.equals(other);
  }


public Teacher copy()
{
  return new Teacher(getId(),getName());
}
}


package module;

public class Teacher extends Person
{
  public Teacher(String id, String name)
  {
    super(id,name);
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


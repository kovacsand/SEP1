package module;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassList implements Serializable
{
  private ArrayList<Class> aClasses;

  /**
   * No-argument constructor initializing the classes ArrayList.
   */
  public ClassList(){
    aClasses =new ArrayList<>();
  }

  /**
   * Adds the class given as a parameter to the classes list.
   * @param Class
   */
  public void addClass(Class Class){
    aClasses.add(Class);
  }

  /**
   * Removes the class which has the semester and group given as a parameter from the classes list.
   * @param semester
   * @param group
   */
  public void removeClass(int semester,String group){
    for(int i = 0; i< aClasses.size(); i++){
      if(aClasses.get(i).getSemester()==semester && aClasses.get(i).getGroup().equals(group)){
        aClasses.remove(aClasses.get(i));
      }
    }
  }

  /**
   * Returns all elements of the classes list.
   * @return classes
   */
  public ArrayList<Class> getAllClasses()
  {
    return aClasses;
  }

  /**
   * Returns a specific class the user is looking for
   * @param classId ID of the class object you are looking for (Ex. 1X)
   * @return the class object that matches the given classId
   */
  public Class getAClass(String classId)
  {
    Class temp = null;
    for (int i = 0; i < aClasses.size(); i++)
    {
      if(aClasses.get(i).getId().equals(classId))
      {
        temp = aClasses.get(i);
      }
    }
    return temp;
  }
  /**
   * Returns a new list of classes of the class list which has the semester given as a parameter.
   * @param semester
   * @return semesterClasses
   */
  public ArrayList<Class> getAllClassesBySemester(int semester){
    ArrayList<Class> semesterClasses=new ArrayList<>();
    for(int i = 0; i< aClasses.size(); i++){
      if(aClasses.get(i).getSemester()==semester){
        semesterClasses.add(aClasses.get(i));
      }
    }
    return semesterClasses;
  }

  /**
   * Returns the size of the class list
   * @return classes.size()
   */
  public int getSize()
  {
    return aClasses.size();
  }

  /**
   * Checks if two class lists are equal.
   * Returns true if both class lists has the same elements, values and size.
   * @param obj
   * @return true or false
   */
  public boolean equals(Object obj){
    if(!(obj instanceof ClassList)){
      return false;
    }
    ClassList other=(ClassList) obj;
    return aClasses.equals(other);
  }

  /**
   * Displays all the data stored in the class list.
   * Displays the semester and group of each class in the class list.
   * @return classes.get(i).getSemester()
   * @return classes.get(i).getGroup()
   */
  public String toString(){
    String str="";
    for(int i = 0; i< aClasses.size(); i++){
      str=str+ aClasses.get(i).getSemester()+ aClasses.get(i).getGroup() + " ";
    }
    return str;
  }

  /**
   * Creates a copy object of the ClassList object.
   * Creates a new object with the same values.
   * @return temp
   */
  public ClassList copy(){
    ClassList temp=new ClassList();
    for(int i = 0; i< aClasses.size(); i++)
    {
      temp.addClass(aClasses.get(i));
    }
    return temp;
  }


}

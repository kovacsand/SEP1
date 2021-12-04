package module;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassList implements Serializable
{
  private ArrayList<Class> classes;

  /**
   * No-argument constructor initializing the classes ArrayList.
   */
  public ClassList(){
    classes=new ArrayList<>();
  }
  /**
   * One-argument constructor initializing the classes ArrayList with the given ArrayList.
   * @param courses
   */
  public ClassList(ArrayList<Class> classes){
    this.classes=classes;
  }
  /**
   * Adds the class given as a parameter to the classes list.
   * @param Class
   */
  public void addClass(Class Class){
    classes.add(Class);
  }

  /**
   * Removes the class which has the semester and group given as a parameter from the classes list.
   * @param semester
   * @param group
   */
  public void removeClass(int semester,String group){
    for(int i=0;i<classes.size();i++){
      if(classes.get(i).getSemester()==semester && classes.get(i).getGroup().equals(group)){
        classes.remove(classes.get(i));
      }
    }
  }

  /**
   * Returns all elements of the classes list.
   * @return classes
   */
  public ArrayList<Class> getAllClasses(){
    return classes;
  }

  /**
   * Returns a new list of classes of the class list which has the semester given as a parameter.
   * @param semester
   * @return semesterClasses
   */
  public ArrayList<Class> getAllClassesBySemester(int semester){
    ArrayList<Class> semesterClasses=new ArrayList<>();
    for(int i=0;i<classes.size();i++){
      if(classes.get(i).getSemester()==semester){
        semesterClasses.add(classes.get(i));
      }
    }
    return semesterClasses;
  }

  /**
   * Returns the size of the class list
   * @return classes.size()
   */
  public int getSize(){
    return classes.size();
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
    return classes.equals(other);
  }

  /**
   * Displays all the data stored in the class list.
   * Displays the semester and group of each class in the class list.
   * @return classes.get(i).getSemester(), classes.get(i).getGroup()
   */
  public String toString(){
    String str="";
    for(int i=0;i<classes.size();i++){
      str=str+classes.get(i).getSemester()+" "+classes.get(i).getGroup();
    }
    return str;
  }

  /**
   * Creates a copy object of the ClassList object.
   * Creates a new object with the same values.
   * @return new ClassList()
   */
  public ClassList copy(){
    return new ClassList(classes);
  }


}

package module;

import java.util.ArrayList;

public class ClassList
{
  private ArrayList<Class> classes;

  public ClassList(){
    classes=new ArrayList<>();
  }
  public void addClass(Class aClass){
    classes.add(aClass);
  }
  public void removeClass(int semester,String group){
    for(int i=0;i<classes.size();i++){
      if(classes.get(i).getSemester()==semester && classes.get(i).getGroup().equals(group)){
        classes.remove(classes.get(i));
      }
    }
  }

  public ArrayList<Class> getAllClasses(){
    return classes;
  }

  public ArrayList<Class> getAllClassesBySemester(int semester){
    ArrayList<Class> semesterClasses=new ArrayList<>();
    for(int i=0;i<classes.size();i++){
      if(classes.get(i).getSemester()==semester){
        semesterClasses.add(classes.get(i));
      }
    }
    return semesterClasses;
  }

  public int getSize(){
    return classes.size();
  }

  public boolean equals(Object obj){
    if(!(obj instanceof ClassList)){
      return false;
    }
    ClassList other=(ClassList) obj;
    return classes.equals(other);
  }

  public String toString(){
    String str="";
    for(int i=0;i<classes.size();i++){
      str=str+classes.get(i).getSemester()+" "+classes.get(i).getGroup();
    }
    return str;
  }


}

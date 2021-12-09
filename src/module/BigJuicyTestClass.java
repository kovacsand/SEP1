package module;

public class BigJuicyTestClass
{
  public static void main(String[] args)
  {
    ScheduleModelManager modelManager = new ScheduleModelManager();
    modelManager.importData();
    Student student = new Student("123451", "Alley Heya", 1, "Y");
//    System.out.println(modelManager.getAllCourses());
    System.out.println(modelManager.getAllClasses());
    System.out.println(modelManager.getClassById("1Y"));
    modelManager.removeStudentFromClass("123456");
    System.out.println(modelManager.getClassById("1Y"));
//    System.out.println(modelManager.getAllStudents());
//    System.out.println(modelManager.getAllTeachers());
//    System.out.println(modelManager.getAllClassrooms());


  }
}

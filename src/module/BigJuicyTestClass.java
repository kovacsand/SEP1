package module;

public class BigJuicyTestClass
{
  public static void main(String[] args)
  {
    ScheduleModelManager modelManager = new ScheduleModelManager();
    modelManager.importData();

    System.out.println(modelManager.getAllTeachers());
    modelManager.addTeacher(new Teacher("AK", "Andras Kovacs"));
    System.out.println(modelManager.getAllTeachers());
    modelManager.removeTeacher(modelManager.getTeacher("AK"));
    System.out.println(modelManager.getAllTeachers());
//    System.out.println(modelManager.getCourse("SDJ1X"));

  }
}

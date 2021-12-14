package module;

public class BigJuicyTestClass
{
  public static void main(String[] args)
  {
    ScheduleModelManager modelManager = new ScheduleModelManager();
    modelManager.importData();
    modelManager.assignStudentsToCourses();
    Student student = new Student("123451", "Alley Heya", 1, "Y");
    Student student2 = new Student("531552", "PPPP", 4, "X");
    modelManager.addStudentToClass("531552", "4X");
    System.out.println(modelManager.getClassById("4X"));
    modelManager.export();
  }
}

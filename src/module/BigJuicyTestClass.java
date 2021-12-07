package module;

public class BigJuicyTestClass
{
  public static void main(String[] args)
  {
    ScheduleModelManager modelManager = new ScheduleModelManager();
    modelManager.importData();
    Student student = new Student("123451", "Alley Heya", 1, "Y");

    System.out.println(modelManager.getAllClasses());
    System.out.println(modelManager.getClassById("1Y"));
    modelManager.addStudent(student);
    System.out.println(modelManager.getClassById("1Y"));
    System.out.println(modelManager.getAllStudents());
    modelManager.removeStudentFromClass(student.getId());
    System.out.println(modelManager.getClassById("1Y"));
    System.out.println(modelManager.getAllStudents());
  }
}

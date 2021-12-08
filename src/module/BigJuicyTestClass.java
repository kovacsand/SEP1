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
    modelManager.addStudentToCourse("123451", "SDJ1X");
    System.out.println(modelManager.getClassById("1Y"));
    System.out.println(modelManager.getAllStudents());
    System.out.println(modelManager.getCourse("SDJ1X"));
    modelManager.removeStudent(student);
    modelManager.removeStudentFromCourse("123451", "SDJ1X");
    //modelManager.removeStudentFromClass(student.getId());
    System.out.println(modelManager.getCourse("SDJ1X"));
    System.out.println(modelManager.getClassById("1Y"));
    System.out.println(modelManager.getAllStudents());
  }
}

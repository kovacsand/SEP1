package module;

public class BigJuicyTestClass
{
  public static void main(String[] args)
  {
    ScheduleModelManager modelManager = new ScheduleModelManager();
    modelManager.importData();
    modelManager.assignStudentsToCourses();
    Student student = new Student("123451", "Alley Heya", 1, "Y");
    modelManager.addStudentToCourse("123451", "SDJ1X");
    modelManager.addTeacherToCourse("RIB", "SDJ1X");
    System.out.println(modelManager.getCourse("SDJ1X"));

    modelManager.addSession(new Session(new MyDate(10, 12, 2021),
        new TimeInterval(820, 1150), modelManager.getAllClassrooms().getClassroom("C05.15"),
        modelManager.getCourse("SDJ1X")));

    modelManager.export();
  }
}

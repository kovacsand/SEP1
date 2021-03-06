package module;

public class BigJuicyTestClass
{
  public static void main(String[] args)
  {
    ScheduleModelManager modelManager = new ScheduleModelManager();
    modelManager.importData();
    Student student = new Student("123451", "Alley Heya", 1, "Y");
    Student student2 = new Student("531552", "PPPP", 4, "X");
    modelManager.addSession(new Session(new MyDate(17, 12, 2021), new TimeInterval(820, 1150), modelManager.getAllClassrooms()
        .getClassroom("C05.15"), modelManager.getCourse("SEP1X")));

    System.out.println(modelManager.getAllClassrooms().getClassroom("C05.15").isFree(new MyDate(17, 12, 2021), new TimeInterval(1151, 1822)));

    modelManager.export();
  }
}

package module;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * A class containing the data handler for the scheduling program.
 * @author Hi-Phi
 * Version 1.0
 */

public class ScheduleModelManager
{
  /**
   * No argument constructor for model manager.
   */
  public ScheduleModelManager()
  {
  }

  /**
   * Method to import the student, classroom, teacher, and course data from .txt files.
   * Saves to .bin files.
   */
  public void importData()
  {
    StudentList students = new StudentList();
    ClassroomList classrooms = new ClassroomList();
    TeacherList teachers = new TeacherList();
    CourseList courses = new Courselist();

    String[] studentArray = null;
    String[] classroomArray = null;
    String[] teacherArray = null;
    String[] courseArray = null;

    try
    {
      studentArray = MyFileHandler.readArrayFromTextFile("students.txt");

      for (int i = 0; i < studentArray.length; i++)
      {
        String temp = studentArray[i];
        String[] tempArr = temp.split(",");
        String semester = tempArr[0];
        String group = tempArr[1];
        String id = tempArr[2];
        String name = tempArr[3];

        students.add(new Student(semester, group, id, name));
      }
    }

    catch (FileNotFoundException e)
    {
      System.out.println("File not found.");
    }

    catch (NullPointerException e)
    {
      System.out.println("Data incompatible.");
    }

    try
    {
      MyFileHandler.writeToBinaryFile("students.bin", students);
    }

    catch (FileNotFoundException e)
    {
      System.out.println("File not found.");
    }

    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }

    try
    {
      teacherArray = MyFileHandler.readArrayFromTextFile("teachers.txt");

      for (int i = 0; i < teacherArray.length; i++)
      {
        String temp = teacherArray[i];
        String[] tempArr = temp.split(",");
        String id = tempArr[0];
        String name = tempArr[1];

        teachers.add(new Teacher(id, name));
      }
    }

    catch (FileNotFoundException e)
    {
      System.out.println("File not found.");
    }

    catch (NullPointerException e)
    {
      System.out.println("Data incompatible.");
    }

    try
    {
      MyFileHandler.writeToBinaryFile("teachers.bin", teachers);
    }

    catch (FileNotFoundException e)
    {
      System.out.println("File not found.");
    }

    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }

    try
    {
      classroomArray = MyFileHandler.readArrayFromTextFile("classrooms.txt");

      for (int i = 0; i < classroomArray.length; i++)
      {
        String temp = classroomArray[i];
        String[] tempArr = temp.split(",");
        String room = tempArr[0];
        String capacity = tempArr[1];

        classrooms.add(new Classroom(room, capacity));
      }
    }

    catch (FileNotFoundException e)
    {
      System.out.println("File not found.");
    }

    catch (NullPointerException e)
    {
      System.out.println("Data incompatible.");
    }

    try
    {
      MyFileHandler.writeToBinaryFile("classrooms.bin", classrooms);
    }

    catch (FileNotFoundException e)
    {
      System.out.println("File not found.");
    }

    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }


    try
    {
      courseArray = MyFileHandler.readArrayFromTextFile("courses.txt");

      for (int i = 0; i < courseArray.length; i++)
      {
        String temp = courseArray[i];
        String[] tempArr = temp.split(",");
        String semester = tempArr[0];
        String group = tempArr[1];
        String name = tempArr[2];
        String teacherId = tempArr[3];
        String ects = tempArr[4];

        courses.add(new Course(semester, group, name, teacherId, ects));
      }
    }

    catch (FileNotFoundException e)
    {
      System.out.println("File not found.");
    }

    catch (NullPointerException e)
    {
      System.out.println("Data incompatible.");
    }

    try
    {
      MyFileHandler.writeToBinaryFile("courses.bin", courses);
    }

    catch (FileNotFoundException e)
    {
      System.out.println("File not found.");
    }

    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }

    System.out.println("Data imported.");

  }

  /**
   *  Export data as xml file.
   */
  public void export()
  {

  }

  /**
   * Get all sessions from .bin file.
   * @return the SessionList containing all the sessions
   */
  public SessionList getAllSessions(){
    SessionList allSessions = new SessionList();

    try{
      allSessions = (SessionList)MyFileHandler.readFromBinaryFile("sessions.bin");
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found.");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file.");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class not found.");
    }

    return allSessions;
  }

  /**
   * Get one session
   */

  public Session getSession(String id)
  {
    Session session = new Session();

    SessionList allSessions = getAllSessions();

    for (int i = 0; i < allSessions.size(); i++)
    {
      if(allSessions.get(i).getSession.getId.equals(id);
      {
        session = allSessions.get(i);
      }
    }
    return session;
  }

}



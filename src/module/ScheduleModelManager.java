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

    //Importing data for the students

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

    //Importing data for the teachers

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

    //Importing data for the classrooms

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

    //Importing data for the courses

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
   * Get one specific session that matches the id (Ex: 03122110001100SDJ1X)
   * @param id the id of the session
   * @return the Session that matches the id given
   */

  public Session getSession(String id)
  {
    Session session = new Session();

    SessionList allSessions = getAllSessions();

    for (int i = 0; i < allSessions.size(); i++)
    {
      if(allSessions.getSession(i).getId.equals(id);
      {
        session = allSessions.get(i);
      }
    }
    return session;
  }

  public void removeSession(Session session)
  {
    SessionList allSessions = getAllSessions();
    SessionList newList = new SessionList();

    for (int i = 0; i < allSessions.size(); i++)
    {
      if(!(allSessions.getSession(i).getId().equals(session)))
      {
        newList.addSession(allSessions.getSession(i));
      }
    }
    try
    {
      MyFileHandler.writeToBinaryFile("sessions.bin", newList);
    }

    catch(FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch(IOException e)
    {
      System.out.println("IO Error writing to a file");
    }
  }

  /**
   * Removing all sessions from the list
   * @param sessionList matching the sessions that will be deleted
   */
  public void removeAllSessions(SessionList sessionList)
  {
    SessionList allSessions = getAllSessions();
    allSessions.remove(sessionList);
  }

  /**
   * Adding one session to the SessionList
   * @param session object added to the SessionList
   */
  public void addSession(Session session)
  {
    SessionList allSessions = getAllSessions();
    allSessions.add(session);

    try
    {
      MyFileHandler.writeToBinaryFile("sessions.bin", allSessions);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to a file");
    }
  }

  /**
   * Getting a classroom list
   * @return ClassroomList containing all classrooms
   */
  public ClassroomList getAllClassrooms()
  {
    ClassroomList allClassrooms = new ClassroomList();

    try
    {
      allClassrooms = (ClassroomList)MyFileHandler.readFromBinaryFile("classrooms.bin");
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading a file");
    }
    return allClassrooms;
  }

  public ClassroomList getAllClassroomsByCapacity(int capacity)
  {
//  !!!!come back to this later (capacity)
    ClassroomList allClassrooms = getAllClassrooms();
    ClassroomList newList = new ClassroomList();

    for (int i = 0; i < allClassrooms.size(); i++)
    {
      if(allClassrooms.get(i).getCapacity() >= capacity)
      {
        newList.add(allClassrooms.get(i));
      }
    }
    return newList;
  }

  public void removeClassroom(String name)
  {
    ClassroomList allClassrooms = getAllClassrooms();
    ClassroomList newList = new ClassroomList();

    for (int i = 0; i < allClassrooms.size(); i++)
    {
      if(!(allClassrooms.get(i).getName().equals(name)))
      {
        newList.add(allClassrooms.get(i));
      }
    }
    try
    {
      MyFileHandler.writeToBinaryFile("classrooms.bin", newList);
    }
    catch(FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to a file");
    }
  }
}



package module;
import utils.MyFileHandler;

import java.io.File;
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
    CourseList courses = new CourseList();

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

        students.addStudent(new Student(id, name, Integer.parseInt(semester), group));
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

        teachers.addTeacher(new Teacher(id, name));
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

        classrooms.addClassroom(new Classroom(room, Integer.parseInt(capacity)));
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

      Teacher teacher = null;

      for (int i = 0; i < courseArray.length; i++)
      {
        String temp = courseArray[i];
        String[] tempArr = temp.split(",");
        String semester = tempArr[0];
        String group = tempArr[1];
        String name = tempArr[2];
        String teacherId = tempArr[3];
          for (int j = 0; j < teachers.getSize(); j++)
          {
            if(teachers.getAllTeachers().get(j).getId().equals(teacherId))
            {
              teacher = new Teacher(teachers.getAllTeachers().get(j).getName(), teacherId);
            }
          }
        String ects = tempArr[4];

        courses.addCourse(new Course(name, Integer.parseInt(semester), group, Integer.parseInt(ects), teacher));
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
//TODO!!!!!!!!!!!!!!!!!!!
  }

  /**
   * Get all sessions from .bin file.
   * @return the SessionList containing all the sessions
   */
  public SessionList getAllSessions()
  {
    SessionList allSessions = new SessionList();

    try
    {
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
    SessionList allSessions = getAllSessions();

    return allSessions.getSession(id);
  }

  /**
   * Removing a session from the list
   * @param session matching the session that will be removed
   */

  public void removeSession(Session session)
  {
    SessionList allSessions = getAllSessions();

    allSessions.removeSession(session.getId());

    try
    {
      MyFileHandler.writeToBinaryFile("sessions.bin", allSessions);
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
    SessionList allSessions = new SessionList();

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
   * Adding one session to the SessionList
   * @param session object added to the SessionList
   */
  public void addSession(Session session)
  {
    SessionList allSessions = getAllSessions();
    allSessions.addSession(session);

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

  /**
   * Get all classrooms by its capacity
   * @param capacity takes into account the size of the people that must be assigned for the session
   * @return ClassroomList that contains all the classrooms that are larger or equal in size than the size of the students that are assigned to the course which is assigned to the classroom
   */
  public ClassroomList getAllClassroomsByCapacity(int capacity)
  {
//  !!!!come back to this later (capacity) TODO!!!!!!!!
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

  /**
   * Removing the classroom from the ClassroomList
   * @param name removing the given classroom by its name
   */
  public void removeClassroom(String name)
  {
    ClassroomList allClassrooms = getAllClassrooms();
    allClassrooms.removeClassroom(name);

    try
    {
      MyFileHandler.writeToBinaryFile("classrooms.bin", allClassrooms);
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

  /**
   * Reading the courses from the file and storing them
   * @return CourseList object containing all the courses
   */
  public CourseList getAllCourses()
  {
    CourseList allCourses = new CourseList();

    try
    {
      allCourses = (CourseList)MyFileHandler.readFromBinaryFile("courses.bin");
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
    return allCourses;
  }

  /**
   * Get singular course by its id
   * @param id that matches the course you are looking for
   * @return Course object that contains the course the user searched for it
   */
  public Course getCourse(String id)
  {
    CourseList allCourses = getAllCourses();
    Course course = null;
    for (int i = 0; i < allCourses.getSize(); i++)
    {
      if(allCourses.getAllCourses().get(i).equals(id))
      {
        course = allCourses.getAllCourses().get(i);
        break;
      }
    }
    return course;
  }

  /**
   * Removes specific course from the CourseList
   * @param course name of the course that is being removed
   */
  public void removeCourse(Course course)
  {
    CourseList allCourses = getAllCourses();
    allCourses.removeCourse(course.getName(), course.getSemester(), course.getGroup());
    try
    {
      MyFileHandler.writeToBinaryFile("courses.bin", allCourses;
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
   * Adding a course to the CourseList
   * @param course which course is going to be added
   */
  public void addCourse(Course course)
  {
    CourseList allCourses = getAllCourses();
    allCourses.addCourse(course);

    try
    {
      MyFileHandler.writeToBinaryFile("courses.bin", allCourses);
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
   * Get ClassList of all classes
   * @return ClassList object of all classes
   */
  public ClassList getAllClasses()
  {
    ClassList allClasses = new ClassList();

    try
    {
      allClasses = (ClassList)MyFileHandler.readFromBinaryFile("classes.bin");
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
      System.out.println("IO Error reading from a file");
    }
    return allClasses;
  }

  /**
   * Get a ClassList classes by semester
   * @param semester which semester classes would like to be found
   * @return ClassList containing the classes of the given semester
   */
  public ClassList getAllClassBySemester(int semester)
  {
    ClassList allClasses = getAllClasses();
    ArrayList<Class> classes = new ArrayList<>();
    classes = allClasses.getAllClassesBySemester(semester);
    ClassList newList = new ClassList();

    for (int i = 0; i < classes.size(); i++)
    {
      newList.addClass(classes.get(i));
    }
    return newList;
  }

  /**
   * Remove the class from the ClassList
   * @param aClass remove the given class from the ClassList
   */
  public void removeClass(Class aClass)
  {
    ClassList allClasses = getAllClasses();
    String group = aClass.getGroup();
    int semester = aClass.getSemester();
    allClasses.removeClass(semester, group);

    try
    {
      MyFileHandler.writeToBinaryFile("classes.bin", allClasses);
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
   * Adding a class to the ClassList
   * @param aClass adding the specified class to the ClassList
   */
  public void addClass(Class aClass)
  {
    ClassList allClasses = getAllClasses();
    allClasses.addClass(aClass);

    try
    {
      MyFileHandler.writeToBinaryFile("classes.bin", allClasses);
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
   * Get a StudentList of all students
   * @return StudentList of the students
   */
  public StudentList getAllStudents()
  {
    StudentList allStudents = new StudentList();

    try
    {
      allStudents = (StudentList)MyFileHandler.readFromBinaryFile("students.bin");
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
    return allStudents;
  }

  /**
   * Get student by searching its ID
   * @param id of the student that needs to be found
   * @return student object that matches the ID
   */
  public Student getStudent(String id)
  {
    StudentList allStudents = getAllStudents();
    return allStudents.getStudent(id);
  }

  /**
   * Removing the student from the StudentList
   * @param student object that needs to be removed
   */
  public void removeStudent(Student student)
  {
    StudentList allStudents = getAllStudents();
    allStudents.removeStudent(student.getId());

    try
    {
      MyFileHandler.writeToBinaryFile("students.bin", allStudents);
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
   * Adding the student to the StudentList
   * @param student student object that needs to be added
   */
  public void addStudent(Student student)
  {
    StudentList allStudents = getAllStudents();
    allStudents.addStudent(student);

    try
    {
      MyFileHandler.writeToBinaryFile("students.bin", allStudents);
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
   * Getting all the teachers in a TeacherList
   * @return TeacherList object of all the teachers
   */
  public TeacherList getAllTeachers()
  {
    TeacherList allTeachers = new TeacherList();
    try
    {
      allTeachers= (TeacherList) MyFileHandler.readFromBinaryFile("teachers.bin");
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
    return allTeachers;
  }

  /**
   * Get specific Teacher object from the TeacherList by its id
   * @param id of the Teacher object
   * @return Teacher object that contains the id
   */
  public Teacher getTeacher(String id)
  {
    TeacherList allTeachers = getAllTeachers();
    return allTeachers.getTeacher(id);
  }

  /**
   * Removing the Teacher object from the TeacherList
   * @param teacher object that needs to be removed from the TeacherList
   */
  public void removeTeacher(Teacher teacher)
  {
    TeacherList allTeachers = getAllTeachers();
    allTeachers.removeTeacher(teacher.getId());
    try
    {
      MyFileHandler.writeToBinaryFile("teachers.bin", allTeachers);
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
   * Adding the Teacher object to the TeacherList
   * @param teacher object that needs to be added to the TeacherList
   */
  public void addTeacher(Teacher teacher)
  {
    TeacherList allTeachers = getAllTeachers();
    allTeachers.addTeacher(teacher);

    try
    {
      MyFileHandler.writeToBinaryFile("teachers.bin", allTeachers);
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
}



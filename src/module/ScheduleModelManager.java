package module;

import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * A class containing the data handler for the scheduling program.
 *
 * @author Hi-Phi
 * @version 1.0
 */

public class ScheduleModelManager
{
  /**
   * No-argument constructor to initialize the model manager.
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
    ClassList classes = new ClassList();

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

        students.addStudent(
            new Student(id, name, Integer.parseInt(semester), group));

        //Creating new classes
        boolean exists = false;
        for (int j = 0; j < classes.getSize(); j++)
          if ((classes.getAllClasses().get(j).getSemester() == Integer.parseInt(
              semester) && classes.getAllClasses().get(j).getGroup()
              .equals(group)))
          {
            exists = true;
          }
        if (!exists)
        {
          classes.addClass(new Class(Integer.parseInt(semester), group));
        }
        for (int j = 0; j < classes.getSize(); j++)
        {
          if ((classes.getAllClasses().get(j).getSemester() == Integer.parseInt(
              semester) && classes.getAllClasses().get(j).getGroup()
              .equals(group)))
          {
            classes.getAllClasses().get(j).addStudent(students.getStudent(id));
          }
        }
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
      MyFileHandler.writeToBinaryFile("classes.bin", classes);
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

        classrooms.addClassroom(
            new Classroom(room, Integer.parseInt(capacity)));
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

      boolean found = false;

      for (int i = 0; i < courseArray.length; i++)
      {
        found = false;
        String temp = courseArray[i];
        String[] tempArr = temp.split(",");
        String semester = tempArr[0];
        String group = tempArr[1];
        String name = tempArr[2];
        String teacherId = tempArr[3];

        for (int j = 0; j < courses.getSize(); j++)
        {
          if (courses.getAllCourses().get(j).getId()
              .equals(name + semester + group))
          {
            courses.getAllCourses().get(j).addTeacher(getTeacher(teacherId));
            found = true;
          }
        }

        if (!found)
        {
          for (int j = 0; j < teachers.getSize(); j++)
          {
            if (teachers.getAllTeachers().get(j).getId().equals(teacherId))
            {
              teacher = new Teacher(teacherId,
                  teachers.getAllTeachers().get(j).getName());
            }

          }
          String ects = tempArr[4];

          courses.addCourse(new Course(name, Integer.parseInt(semester), group,
              Integer.parseInt(ects), teacher));

          for (int j = 0; j < classes.getSize(); j++)
          {
            Class tempClass = classes.getAllClasses().get(j);
            if (tempClass.getSemester() == Integer.parseInt(semester)
                && tempClass.getGroup().equals(group))
            {
              tempClass.addCourse(
                  new Course(name, Integer.parseInt(semester), group,
                      Integer.parseInt(ects), teacher));
            }
          }
        }
      }
    }

    catch (FileNotFoundException e)
    {
      System.out.println("File not found. reading courses");
    }

    catch (NullPointerException e)
    {
      System.out.println("Data incompatible.");
    }

    try
    {
      MyFileHandler.writeToBinaryFile("courses.bin", courses);
      MyFileHandler.writeToBinaryFile("classes.bin", classes);
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

    assignStudentsToCourses();
  }

  /**
   * Assigning the students to the courses and writing to .bin file
   */
  public void assignStudentsToCourses()
  {
    CourseList courseList = getAllCourses();

    for (int i = 0; i < courseList.getSize(); i++)
    {
      String tempClassId = courseList.getAllCourses().get(i).getSemester()
          + courseList.getAllCourses().get(i).getGroup();
      for (int j = 0; j < getClassById(tempClassId).getStudentCount(); j++)
      {
        courseList.getAllCourses().get(i).addStudent(
            getClassById(tempClassId).getAllStudents().getAllStudents().get(j));
      }
    }

    try
    {
      MyFileHandler.writeToBinaryFile("courses.bin", courseList);
    }

    catch (FileNotFoundException e)
    {
      System.out.println("File not found.");
    }

    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  /**
   * Export data (schedule) as xml file to display on the website
   */
  public void export()
  {
    SessionList allSessions = getAllSessions();
    PrintWriter write = null;
    try
    {
      FileOutputStream fileOut = new FileOutputStream("sessions.xml");
      write = new PrintWriter(fileOut);
    }
    catch (FileNotFoundException ex)
    {
      System.out.println("File not found, or could not be opened");
    }

    System.out.println("Writing to a file");
    write.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    write.println("<sessions>");

    for (int i = 0; i < allSessions.getSize(); i++)
    {
      write.println("<session>");
      write.println(
          "<course>" + allSessions.getAllSessions().get(i).getCourseString()
              + "</course>");
      write.println(
          "<date>" + allSessions.getAllSessions().get(i).getDate() + "</date>");
      write.println(
          "<time>" + allSessions.getAllSessions().get(i).getTimeString()
              + "</time>");
      write.println(
          "<room>" + allSessions.getAllSessions().get(i).getClassroomString()
              + "</room>");
      write.println(
          "<teacher>" + allSessions.getAllSessions().get(i).getCourse()
              .getAllTeachers().getAllTeachersId() + "</teacher>");
      write.println("<ids>" + allSessions.getAllSessions().get(i).getCourse()
          .getAllStudents().getAllStudentIds() + ", "
          + allSessions.getAllSessions().get(i).getCourse().getAllTeachers()
          .getAllTeachersId() + "</ids>");
      write.println("</session>");
    }

    write.println("</sessions>");

    write.close();

    System.out.println("Done writing");
  }

  /**
   * Get all sessions from .bin file.
   *
   * @return the SessionList containing all the sessions
   */
  public SessionList getAllSessions()
  {
    SessionList allSessions = new SessionList();

    try
    {
      allSessions = (SessionList) MyFileHandler.readFromBinaryFile(
          "sessions.bin");
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found.");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file. (whilst reading sessions)");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class not found.");
    }

    return allSessions;
  }

  /**
   * Get one specific session that matches the id (Ex: 03122110001100SDJ1X)
   *
   * @param id the id of the session
   * @return the Session that matches the id given
   */

  public Session getSession(String id)
  {
    SessionList allSessions = getAllSessions();

    return allSessions.getSession(id);
  }

  /**
   * Removing a session from the list. After it saves to the .bin file
   *
   * @param session matching the session that will be removed
   */

  public void removeSession(Session session)
  {
    SessionList allSessions = getAllSessions();
    ClassroomList allClassrooms = getAllClassrooms();

    allClassrooms.getClassroom(session.getRoom().getName())
        .removeOccupiedHours(session.getDate(), session.getInterval());
    allSessions.removeSession(session.getId());

    try
    {
      MyFileHandler.writeToBinaryFile("sessions.bin", allSessions);
      MyFileHandler.writeToBinaryFile("classrooms.bin", allClassrooms);
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
   * Adding one session to the SessionList and writing to .bin file.
   *
   * @param session object added to the SessionList
   */
  public void addSession(Session session)
  {
    SessionList allSessions = getAllSessions();
    ClassroomList allClassrooms = getAllClassrooms();

    allSessions.addSession(session);
    allClassrooms.getClassroom(session.getRoom().getName())
        .addOccupiedHours(session.getDate(), session.getInterval());

    try
    {
      MyFileHandler.writeToBinaryFile("sessions.bin", allSessions);
      MyFileHandler.writeToBinaryFile("classrooms.bin", allClassrooms);
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
   * Getting a classroom list while reading from .bin file
   *
   * @return ClassroomList containing all classrooms
   */
  public ClassroomList getAllClassrooms()
  {
    ClassroomList allClassrooms = new ClassroomList();

    try
    {
      allClassrooms = (ClassroomList) MyFileHandler.readFromBinaryFile(
          "classrooms.bin");
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
   *
   * @param capacity takes into account the size of the people that must be assigned for the session
   * @return ClassroomList that contains all the classrooms that are larger or equal in size than the size of the students that are assigned to the course which is assigned to the classroom
   */
  public ClassroomList getAllClassroomsByCapacity(int capacity)
  {
    ClassroomList allClassrooms = getAllClassrooms();
    ClassroomList newList = new ClassroomList();
    ArrayList<Classroom> classrooms = allClassrooms.getClassrooms(capacity);
    for (int i = 0; i < classrooms.size(); i++)
    {
      if (classrooms.get(i).getCapacity() >= capacity)
      {
        newList.addClassroom(classrooms.get(i));
      }
    }
    return newList;
  }

  /**
   * Reading the courses from the .bin file and storing them
   *
   * @return CourseList object containing all the courses
   */
  public CourseList getAllCourses()
  {
    CourseList allCourses = new CourseList();

    try
    {
      allCourses = (CourseList) MyFileHandler.readFromBinaryFile("courses.bin");
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
   *
   * @param id that matches the course you are looking for
   * @return Course object that contains the course the user searched for it
   */
  public Course getCourse(String id)
  {
    CourseList allCourses = getAllCourses();
    Course course = null;
    for (int i = 0; i < allCourses.getSize(); i++)
    {
      if (allCourses.getAllCourses().get(i).getId().equals(id))
      {
        course = allCourses.getAllCourses().get(i);
        break;
      }
    }
    return course;
  }

  /**
   * Get ClassList of all classes from .bin file
   *
   * @return ClassList object of all classes
   */
  public ClassList getAllClasses()
  {
    ClassList allClasses = new ClassList();

    try
    {
      allClasses = (ClassList) MyFileHandler.readFromBinaryFile("classes.bin");
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
   * Get a class object by its ID
   *
   * @param id ID of the class that you are looking for
   * @return ID of the found course if it matches the given ID
   */
  public Class getClassById(String id)
  {
    ClassList allClasses = getAllClasses();
    Class aClass = null;
    for (int i = 0; i < allClasses.getSize(); i++)
    {
      String classId = allClasses.getAllClasses().get(i).getSemester()
          + allClasses.getAllClasses().get(i).getGroup();
      if (id.equals(classId))
      {
        aClass = allClasses.getAllClasses().get(i);
      }
    }
    return aClass;
  }

  /**
   * Adding a class to the ClassList
   *
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
   * Get a StudentList of all students from .bin file
   *
   * @return StudentList of the students
   */
  public StudentList getAllStudents()
  {
    StudentList allStudents = new StudentList();

    try
    {
      allStudents = (StudentList) MyFileHandler.readFromBinaryFile(
          "students.bin");
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
   *
   * @param id of the student that needs to be found
   * @return student object that matches the ID
   */
  public Student getStudent(String id)
  {
    StudentList allStudents = getAllStudents();
    return allStudents.getStudent(id);
  }

  /**
   * Removing the student from the StudentList and saving the updated StudentList to .bin file
   *
   * @param student object that needs to be removed
   */
  public void removeStudent(Student student)
  {
    StudentList allStudents = getAllStudents();
    ClassList allClasses = getAllClasses();
    CourseList allCourses = getAllCourses();
    String classId = student.getSemester() + student.getGroup();
    allClasses.getAClass(classId).removeStudent(student);

    for (int i = 0; i < allCourses.getSize(); i++)
      if (allCourses.getAllCourses().get(i).getAllStudents().getStudent(student.getId()) != null)
        removeStudentFromCourse(student.getId(), allCourses.getAllCourses().get(i).getId());

    allStudents.removeStudent(student.getId());
    try
    {
      MyFileHandler.writeToBinaryFile("students.bin", allStudents);
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
   * Adding the student to the class and saving the updated .bin file
   *
   * @param studentId ID of the student that must be added to the class
   * @param group     The name of the group where the student must be added
   */
  public void addStudentToClass(String studentId, String group)
  {
    ClassList allClasses = getAllClasses();
    StudentList allStudents = getAllStudents();

    Student student = null;

    for (int i = 0; i < allStudents.getSize(); i++)
    {
      if (allStudents.getAllStudents().get(i).getId().equals(studentId))
      {
        student = new Student(studentId,
            allStudents.getAllStudents().get(i).getName(),
            allStudents.getAllStudents().get(i).getSemester(),
            allStudents.getAllStudents().get(i).getGroup());
        for (int j = 0; j < allClasses.getSize(); j++)
        {
          if (allClasses.getAllClasses().get(j).getId().equals(group))
          {
            Class aClass = allClasses.getAllClasses().get(j);
            if (aClass.getAllStudents().getStudent(studentId) == null)
            {
              aClass.addStudent(student);
            }
          }
        }
      }
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
  }

  /**
   * Removing the student object from the class that the object is attached to and saving the changes to .bin file
   *
   * @param studentId ID of the student object that must be removed from the class it has been assigned to
   */
  public void removeStudentFromClass(String studentId)
  {
    StudentList allStudents = getAllStudents();
    ClassList allClasses = getAllClasses();
    String temp = allStudents.getStudent(studentId).getSemester()
        + allStudents.getStudent(studentId).getGroup();
    allClasses.getAClass(temp).removeStudent(allStudents.getStudent(studentId));

    try
    {
      MyFileHandler.writeToBinaryFile("classes.bin", allClasses);
      MyFileHandler.writeToBinaryFile("students.bin", allStudents);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  /**
   * Adding the student to the StudentList and saving the updated StudentList and ClassList to .bin file
   *
   * @param student student object that needs to be added
   */
  public void addStudent(Student student)
  {
    ClassList allClasses = getAllClasses();
    StudentList allStudents = getAllStudents();
    for (int i = 0; i < allStudents.getSize(); i++)
    {
      if (allStudents.getStudent(student.getId()) == null)
      {
        allStudents.addStudent(student);
      }
    }
    for (int i = 0; i < allClasses.getSize(); i++)
    {
      if (student.getSemester() == allClasses.getAllClasses().get(i)
          .getSemester() && student.getGroup()
          .equals(allClasses.getAllClasses().get(i).getGroup()))
      {
        Class foundClass = allClasses.getAllClasses().get(i);
        if (foundClass.getStudent(student.getId()) == null)
        {
          foundClass.addStudent(student);
        }
      }
    }
    try
    {
      MyFileHandler.writeToBinaryFile("students.bin", allStudents);
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
   * Adding or editing student object details and saving the changes to .bin file
   *
   * @param id       ID of the student that will be added or matching for it to be edited
   * @param name     Name of the student that will be added or name to be edited
   * @param semester Semester of the student that will be added or semester of the existing student to be edited
   * @param group    Group of the student that will be added or group it will be moved to
   */
  public void editStudent(String id, String name, int semester, String group)
  {
    Student student = new Student(id, name, semester, group);
    ClassList allClasses = getAllClasses();
    StudentList allStudents = getAllStudents();
    CourseList allCourses = getAllCourses();

    //Removing student from the system (also removes from the class), if for some reason they were already there
    //If they are a new student, then we do not remove them, so that is not a problem
    if (allStudents.getStudent(student.getId()) != null)
      removeStudent(allStudents.getStudent(student.getId()));

    //Creating the new class, if necessary
    boolean classAlreadyExists = false;
    for (int i = 0; i < allClasses.getSize(); i++)
      if ((allClasses.getAllClasses().get(i).getSemester() == semester
          && allClasses.getAllClasses().get(i).getGroup().equals(group)))
        classAlreadyExists = true;
    if (!classAlreadyExists)
    {
      allClasses.addClass(new Class(semester, group));
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

    //We edit the student in their courses
    for (int i = 0; i < allCourses.getSize(); i++)
    {
      if (allCourses.getAllCourses().get(i).getAllStudents()
          .getStudent(student.getId()) != null)
      {
        allCourses.getAllCourses().get(i).getAllStudents()
            .getStudent(student.getId()).setName(name);
        allCourses.getAllCourses().get(i).getAllStudents()
            .getStudent(student.getId()).setGroup(group);
        allCourses.getAllCourses().get(i).getAllStudents()
            .getStudent(student.getId()).setSemester(semester);
      }
    }

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

    //We create a new student, they are automatically assigned to their class
    addStudent(student);
  }

  /**
   * Getting all the teachers in a TeacherList from .bin file
   *
   * @return TeacherList object of all the teachers
   */
  public TeacherList getAllTeachers()
  {
    TeacherList allTeachers = new TeacherList();
    try
    {
      allTeachers = (TeacherList) MyFileHandler.readFromBinaryFile(
          "teachers.bin");
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
   *
   * @param id of the Teacher object
   * @return Teacher object that contains the id
   */
  public Teacher getTeacher(String id)
  {
    TeacherList allTeachers = getAllTeachers();
    return allTeachers.getTeacher(id);
  }

  /**
   * Removing the Teacher object from the TeacherList and updating the .bin file
   *
   * @param teacher object that needs to be removed from the TeacherList
   */
  public void removeTeacher(Teacher teacher)
  {
    TeacherList allTeachers = getAllTeachers();
    CourseList allCourses = getAllCourses();
    allTeachers.removeTeacher(teacher.getId());

    for (int i = 0; i < allCourses.getSize(); i++)
      if (allCourses.getAllCourses().get(i).getAllTeachers().getTeacher(teacher.getId()) != null)
        removeTeacherFromCourse(teacher.getId(), allCourses.getAllCourses().get(i).getId());

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
   * Adding or editing a teacher and updating the .bin files
   *
   * @param id   ID of the teacher that will be added or if the ID matches, teacher that will be edited
   * @param name Name of the teacher that will be added or if the ID matches, the name of the teacher gets changed
   */
  public void editTeacher(String id, String name)
  {
    CourseList allCourses = getAllCourses();
    TeacherList allTeachers = getAllTeachers();
    Teacher teacher = new Teacher(id, name);

    boolean temp = false;

    for (int i = 0; i < allTeachers.getSize(); i++)
    {
      if (allTeachers.getAllTeachers().get(i).getId().equals(id))
      {
        allTeachers.getAllTeachers().get(i).setName(name);
        temp = true;
      }
    }
    if (temp == false)
    {
      allTeachers.addTeacher(teacher);
    }

    //We edit the teacher in their courses
    for (int i = 0; i < allCourses.getSize(); i++)
    {
      if (allCourses.getAllCourses().get(i).getAllTeachers()
          .getTeacher(teacher.getId()) != null)
        allCourses.getAllCourses().get(i).getAllTeachers()
            .getTeacher(teacher.getId()).setName(name);
    }

    try
    {
      MyFileHandler.writeToBinaryFile("teachers.bin", allTeachers);
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
   * Adding the Teacher object to the TeacherList and updating the .bin file
   *
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

  /**
   * Adding teacher to the course and updating the .bin file
   *
   * @param teacherId ID of the teacher that needs to be added to the course
   * @param courseId  ID of the course where the teacher is being added
   */
  public void addTeacherToCourse(String teacherId, String courseId)
  {
    //This method is used several times, for example when we add a new teacher to a specific course in our CourseView
    //Unfortunately, we do not have anything too exciting in our code, like divide and conquer algorithms, we mostly used iterative loops
    //This makes our program slower, than it could be, but since we are working with little amount of data, this is considered acceptable
    //We are working with binary files, but when calculating time complexity, we do not take into account the possible exceptions caused by this
    //T(3 + n + 3 + m + 9 * m + 9 * n + 44 + 1 + 3 * m) = T(10 * n + 13 * m + 51)
    //After ignoring constants and coefficients: O(n + m)
    //We would consider this surprising, because at first, two nested loops would imply O(n * m)
    //But the code analysis proves, that our code is in fact not so inefficient, thanks to our carefully designed condition statements

    //The getAllCourses()'s complexity is T(3 + n), where n is the number of Course objects in courses.bin
    CourseList allCourses = getAllCourses();
    //The getAllTeachers()'s complexity is T(3 + m), where m is the number of Teacher objects in teachers.bin
    TeacherList allTeachers = getAllTeachers();

    //This for cycle iterates through all the Teacher objects, so it runs m times
    //Everything in the cycle is T(m * (5 + 4 + 1/m * (4 + 9 * n + 40)) = T(9 * m + 9 * n + 44)
    for (int i = 0; i < allTeachers.getSize(); i++) //T(5)
    {
      //This condition is only true 1/m times at most, because Teacher objects cannot have the same teacherId
      //If no Teacher has the same teacherId as the argument, it will not be true
      if (allTeachers.getAllTeachers().get(i).getId().equals(teacherId)) //T(4)
      {
        Teacher teacher = new Teacher(teacherId, allTeachers.getAllTeachers().get(i).getName()); //T(4)
        //This for cycle iterates through all the Course objects, so it runs n times
        //Everything in the cycle is T(n * (5 + 4 + 1/n * 40) = T(9 * n + 40)
        for (int j = 0; j < allCourses.getSize(); j++) //T(5)
        {
          //This condition is only true 1/n times at most, because Course objects cannot have the same courseId
          //If no Course has the same courseId as the argument, it will not be true
          if (allCourses.getAllCourses().get(j).getId().equals(courseId)) //T(4)
          {
            Course course = allCourses.getAllCourses().get(j); //T(3)
            //This is true is the user wants to add a new teacher to the course
            if (course.getAllTeachers().getTeacher(teacherId) == null) //T(17)
            {
              course.addTeacher(teacher); //T(20)
            }
          }
        }
      }
    }
    try //1 + 3 * m
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
   * Removing the teacher from the course and updating the .bin file
   *
   * @param teacherId ID of the teacher object that must be added to the course object
   * @param courseId  ID of the course object where the teacher object must be removed from
   */
  public void removeTeacherFromCourse(String teacherId, String courseId)
  {
    CourseList allCourses = getAllCourses();
    TeacherList allTeachers = getAllTeachers();

    Teacher teacher = null;

    for (int i = 0; i < allTeachers.getSize(); i++)
    {
      if (allTeachers.getAllTeachers().get(i).getId().equals(teacherId))
      {
        teacher = new Teacher(teacherId,
            allTeachers.getAllTeachers().get(i).getName());
        for (int j = 0; j < allCourses.getSize(); j++)
        {
          if (allCourses.getAllCourses().get(j).getId().equals(courseId))
          {
            Course course = allCourses.getAllCourses().get(j);
            for (int k = 0; k < course.getAllTeachers().getSize(); k++)
            {
              if (course.getAllTeachers().getAllTeachers().get(k)
                  .equals(teacher))
              {
                course.removeTeacher(teacher);
              }
            }
          }
        }
      }
    }
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
   * Adding a student object to course object and updating the .bin file
   *
   * @param studentId ID of the student object which needs to be added to the course object
   * @param courseId  ID of the course object where the student object must be added
   */
  public void addStudentToCourse(String studentId, String courseId)
  {
    CourseList allCourses = getAllCourses();
    StudentList allStudents = getAllStudents();

    Student student = null;

    for (int i = 0; i < allStudents.getSize(); i++)
    {
      if (allStudents.getAllStudents().get(i).getId().equals(studentId))
      {
        student = new Student(studentId,
            allStudents.getAllStudents().get(i).getName(),
            allStudents.getAllStudents().get(i).getSemester(),
            allStudents.getAllStudents().get(i).getGroup());
        for (int j = 0; j < allCourses.getSize(); j++)
        {
          if (allCourses.getAllCourses().get(j).getId().equals(courseId))
          {
            Course course = allCourses.getAllCourses().get(j);
            if (course.getAllStudents().getStudent(studentId) == null)
            {
              course.addStudent(student);
            }
          }
        }
      }
    }
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
   * Removing a student object from course object and updating the .bin file
   *
   * @param studentId ID of the student object that must be removed from the course object
   * @param courseId  ID of the course object where the student object must be removed from
   */
  public void removeStudentFromCourse(String studentId, String courseId)
  {
    //This method is used in CourseView times, when we remove a new student from a specific course
    //Unfortunately, we do not have anything too exciting in our code, like divide and conquer algorithms, we mostly used iterative loops
    //This makes our program slower, than it could be, but since we are working with little amount of data, this is considered acceptable
    //We are working with binary files, but when calculating time complexity, we do not take into account the possible exceptions caused by this
    //T(3 + n + 3 + m + 9 * m + 12 * n + 15 * k + 4 + 1 + 3 * m) = T(10 * n + 13 * m + 15 * k + 11)
    //Worst time complexity case is when all the students are enrolled to the same course, then k = m -> T(10 * n + 28 * m + 11)
    //After ignoring constants and coefficients: O(n + m)
    //We would consider this surprising, because at first, three nested loops would imply O(n * m * k)
    //But the code analysis proves, that our code is in fact not so inefficient, thanks to our carefully designed condition statements

    //The getAllCourses()'s complexity is T(3 + n), where n is the number of Course objects in courses.bin
    CourseList allCourses = getAllCourses();
    //The getAllStudents()'s complexity is T(3 + m), where m is the number of Student objects in students.bin
    StudentList allStudents = getAllStudents();

    //This for cycle iterates through all the Student objects, so it runs m times
    //Everything in the cycle is T(m * (5 + 4 + 1/m * (12 * n + 15 * k + 4)) = T(9 * m + 12 * n + 15 * k + 4)
    for (int i = 0; i < allStudents.getSize(); i++) //T(5)
    {
      //This condition is only true 1/m times at most, because Student objects cannot have the same studentId
      //If no Student has the same studentId as the argument, it will not be true
      if (allStudents.getAllStudents().get(i).getId().equals(studentId)) //T(4)
      {
        Student student = allStudents.getAllStudents().get(i).copy(); //T(4)
        //This for cycle iterates through all the Course objects, so it runs n times
        //Everything in the cycle is T(n * (5 + 4 + 3 + 1/n * (15 * k + 4))) = T(12 * n + 15 * k + 4)
        for (int j = 0; j < allCourses.getSize(); j++) //T(5)
        {
          //This condition is only true 1/n times at most, because Course objects cannot have the same courseId
          //If no Course has the same courseId as the argument, it will not be true
          if (allCourses.getAllCourses().get(j).getId().equals(courseId)) //T(4)
          {
            Course course = allCourses.getAllCourses().get(j); //T(3)

            //This for cycle iterates through all the Student objects, that are in the Course's StudentList
            //It runs n times at most, if all the existing Student objects are assigned to the same Course
            //Everything in the cycle is T(k * (5 + 10 + 1/k * 4)) = T(15 * k + 4)
            for (int k = 0; k < course.getAllStudents().getSize(); k++) //T(5)
            {
              //This is true if the Student is in the StudentList of the Course
              //It is true 1/k times at most, where k is the size of number of Students in StudentList
              if (course.getAllStudents().getAllStudents().get(k).equals(student)) //T(10)
              {
                course.removeStudent(student); //T(4)
              }
            }
          }
        }
      }
    }
    try //T(1 + 3 * m)
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
}
package module;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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

        students.addStudent(new Student(id, name, Integer.parseInt(semester), group));

        //Creating new classes
        boolean exists = false;
        for (int j = 0; j < classes.getSize(); j++)
          if ((classes.getAllClasses().get(j).getSemester() == Integer.parseInt(semester) && classes.getAllClasses().get(j).getGroup().equals(group)))
          {
            exists = true;
          }
        if (!exists)
        {
          classes.addClass(new Class(Integer.parseInt(semester), group));
        }
        for (int j = 0; j < classes.getSize(); j++)
        {
          if ((classes.getAllClasses().get(j).getSemester() == Integer.parseInt(semester) && classes.getAllClasses().get(j).getGroup().equals(group)))
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
          if (courses.getAllCourses().get(j).getId().equals(name + semester + group))
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
              teacher = new Teacher(teacherId, teachers.getAllTeachers().get(j).getName());
            }

          }
          String ects = tempArr[4];

          courses.addCourse(new Course(name, Integer.parseInt(semester), group,
              Integer.parseInt(ects), teacher));

          for (int j = 0; j < classes.getSize(); j++)
          {
            Class tempClass = classes.getAllClasses().get(j);
            if (tempClass.getSemester() == Integer.parseInt(semester) && tempClass.getGroup().equals(group))
            {
              tempClass.addCourse(
                  new Course(name, Integer.parseInt(semester), group, Integer.parseInt(ects), teacher));
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

  }

  public void assignStudentsToCourses()
  {
    CourseList courseList = getAllCourses();

    for (int i = 0; i < courseList.getSize(); i++)
    {
      String tempClassId = courseList.getAllCourses().get(i).getSemester() + courseList.getAllCourses().get(i).getGroup();
      for (int j = 0; j < getClassById(tempClassId).getStudentCount(); j++)
      {
        courseList.getAllCourses().get(i).addStudent(getClassById(tempClassId).getAllStudents().getAllStudents().get(j));
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
   * Export data as xml file.
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
      write.println("<course>" + allSessions.getAllSessions().get(i).getCourseString() + "</course>");
      write.println("<date>" + allSessions.getAllSessions().get(i).getDate() + "</date>");
      write.println("<time>" + allSessions.getAllSessions().get(i).getTimeString() + "</time>");
      write.println("<room>" + allSessions.getAllSessions().get(i).getClassroomString() + "</room>");
      write.println("<teacher>" + allSessions.getAllSessions().get(i).getCourse().getAllTeachers() + "</teacher>");
      write.println("<ids>" + allSessions.getAllSessions().get(i).getCourse().getAllStudents() + " " + allSessions.getAllSessions().get(i).getCourse().getAllTeachers() + "</ids>");
      write.println("</session>");
    }

    write.println("</sessions>");

    write.close();

    System.out.println("Done writing");
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
    ClassroomList classroomList = getAllClassrooms();

    classroomList.getClassroom(session.getClassroomString()).addOccupiedHours(session.getDate(), session.getInterval());

    try
    {
      MyFileHandler.writeToBinaryFile("sessions.bin", allSessions);
      MyFileHandler.writeToBinaryFile("classrooms.bin", classroomList);
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
   * @param capacity takes into account the size of the people that must be assigned for the session
   * @return ClassroomList that contains all the classrooms that are larger or equal in size than the size of the students that are assigned to the course which is assigned to the classroom
   */
  public ClassroomList getAllClassroomsByCapacity(int capacity)
  {
    //  !!!!come back to this later (capacity) TODO!!!!!!!!
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
   * Reading the courses from the file and storing them
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
   * Removes specific course from the CourseList
   * @param course name of the course that is being removed
   */
  public void removeCourse(Course course)
  {
    CourseList allCourses = getAllCourses();
    allCourses.removeCourse(course.getName(), course.getSemester(), course.getGroup());
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

  public Class getClassById(String id)
  {
    ClassList allClasses = getAllClasses();
    Class aClass = null;
    for (int i = 0; i < allClasses.getSize(); i++)
    {
      String classId = allClasses.getAllClasses().get(i).getSemester() + allClasses.getAllClasses().get(i).getGroup();
      if(id.equals(classId))
      {
        aClass = allClasses.getAllClasses().get(i);
      }
    }
    return aClass;
  }

  /**
   * Get a ClassList classes by semester
   * @param semester which semester classes would like to be found
   * @return ClassList containing the classes of the given semester
   */
  public ClassList getAllClassBySemester(int semester)
  {
    ClassList allClasses = getAllClasses();
    ArrayList<Class> aClasses = allClasses.getAllClassesBySemester(semester);
    ClassList newList = new ClassList();

    for (int i = 0; i < aClasses.size(); i++)
    {
      newList.addClass(aClasses.get(i));
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
    ClassList allClasses = getAllClasses();
    String classId = student.getSemester() + student.getGroup();
    allStudents.removeStudent(student.getId());
    allClasses.getAClass(classId).removeStudent(student);

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
   * Removing the student object from the class that the object is attached to
   * @param studentId ID of the student object that must be removed from the class it has been assigned to
   */
  public void removeStudentFromClass(String studentId)
  {
    StudentList allStudents = getAllStudents();
    ClassList allClasses = getAllClasses();
    String temp = allStudents.getStudent(studentId).getSemester() + allStudents.getStudent(studentId).getGroup();
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
   * Adding the student to the StudentList
   * @param student student object that needs to be added
   */
  public void addStudent(Student student)
  {
    ClassList allClasses = getAllClasses();
    StudentList allStudents = getAllStudents();
    for (int i = 0; i < allStudents.getSize(); i++)
    {
      if(allStudents.getStudent(student.getId()) == null)
      {
        allStudents.addStudent(student);
      }
    }

    for (int i = 0; i < allClasses.getSize(); i++)
    {
      if(student.getSemester() == allClasses.getAllClasses().get(i).getSemester() && student.getGroup().equals(allClasses.getAllClasses().get(i).getGroup()))
      {
        Class foundClass = allClasses.getAllClasses().get(i);
        if(foundClass.getStudent(student.getId()) == null)
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
   * Getting all the teachers in a TeacherList
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
   * Removing the Teacher object from the TeacherList
   *
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

  /**
   * Adding teacher to the course
   * @param teacherId ID of the teacher that needs to be added to the course
   * @param courseId  ID of the course where the teacher is being added
   */
  public void addTeacherToCourse(String teacherId, String courseId)
  {
    CourseList allCourses = getAllCourses();
    TeacherList allTeachers = getAllTeachers();

    Teacher teacher = null;

    for (int i = 0; i < allTeachers.getSize(); i++)
    {
      if (allTeachers.getAllTeachers().get(i).getId().equals(teacherId))
      {
        teacher = new Teacher(teacherId, allTeachers.getAllTeachers().get(i).getName());
        for (int j = 0; j < allCourses.getSize(); j++)
        {
          if (allCourses.getAllCourses().get(j).getId().equals(courseId))
          {
            Course course = allCourses.getAllCourses().get(j);
            if (course.getAllTeachers().getTeacher(teacherId) == null)
            {
              course.addTeacher(teacher);
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
   * Removing the teacher from the course
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
        teacher = new Teacher(teacherId, allTeachers.getAllTeachers().get(i).getName());
        for (int j = 0; j < allCourses.getSize(); j++)
        {
          if (allCourses.getAllCourses().get(j).getId().equals(courseId))
          {
            Course course = allCourses.getAllCourses().get(j);
            for (int k = 0; k < course.getAllTeachers().getSize(); k++)
            {
              if (course.getAllTeachers().getAllTeachers().get(k).equals(teacher))
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
   * Adding a student object to course object
   * @param studentId ID of the student object which needs to be added to the course object
   * @param courseId ID of the course object where the student object must be added
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
        student = new Student(studentId, allStudents.getAllStudents().get(i).getName(), allStudents.getAllStudents().get(i).getSemester(), allStudents.getAllStudents().get(i).getGroup());
        for (int j = 0; j < allCourses.getSize(); j++)
        {
          if (allCourses.getAllCourses().get(j).getId().equals(courseId))
          {
            Course course = allCourses.getAllCourses().get(j);
            if(course.getAllStudents().getStudent(studentId) == null)
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
   * Removing a student object from course object
   * @param studentId ID of the student object that must be removed from the course object
   * @param courseId ID of the course object where the student object must be removed from
   */
  public void removeStudentFromCourse(String studentId, String courseId)
  {
    CourseList allCourses = getAllCourses();
    StudentList allStudents = getAllStudents();

    Student student = null;

    for (int i = 0; i < allStudents.getSize(); i++)
    {
      if (allStudents.getAllStudents().get(i).getId().equals(studentId))
      {
        student = allStudents.getAllStudents().get(i).copy();
        for (int j = 0; j < allCourses.getSize(); j++)
        {
          if (allCourses.getAllCourses().get(j).getId().equals(courseId))
          {
            Course course = allCourses.getAllCourses().get(j);
            for (int k = 0; k < course.getAllStudents().getSize(); k++)
            {

              if (course.getAllStudents().getAllStudents().get(k).equals(student))
              {
                course.removeStudent(student);
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
}
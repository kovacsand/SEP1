package view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import module.*;

import java.lang.Class;
import java.util.ArrayList;

public class MainViewController
{
  //GENERAL TABS
  @FXML private TabPane tabPane;
  @FXML private Tab sessionsTab;
  @FXML private Tab classesTab;
  @FXML private Tab classroomsTab;
  @FXML private Tab coursesTab;
  @FXML private Tab studentsTab;
  @FXML private Tab teachersTab;

  //CLASSES TABLE
  @FXML private TableView<Class> classesTable;
  @FXML private TableColumn<Class,String> classesSemesterColumn;
  @FXML private TableColumn<Class,String> classesGroupColumn;
  @FXML private TableColumn<Class,String> classesStudentCountColumn;
  @FXML private TableColumn<Class,String> classesCoursesColumn;

  //CLASSROOMS TABLE
  @FXML private TableView<Classroom> classroomsTable;
  @FXML private TableColumn<Classroom, String> classroomsNameColumn;
  @FXML private TableColumn<Classroom, String> classroomsCapacityColumn;

  //COURSES TABLE
  @FXML private TableView<Course> coursesTable;
  @FXML private TableColumn<Course, String> coursesNameColumn;
  @FXML private TableColumn<Course, String> coursesSemesterColumn;
  @FXML private TableColumn<Course, String> coursesGroupColumn;
  @FXML private TableColumn<Course, String> coursesTeacherColumn;
  @FXML private TableColumn<Course, String> coursesEctsColumn;

  //STUDENTS TABLE
  @FXML private TableView<Student> studentsTable;
  @FXML private TableColumn<Student, String> studentsIdColumn;
  @FXML private TableColumn<Student, String> studentsNameColumn;
  @FXML private TableColumn<Student, String> studentsSemesterColumn;
  @FXML private TableColumn<Student, String> studentsGroupColumn;


  //TEACHERS TABLE
  @FXML private TableView<Teacher> teachersTable;
  @FXML private TableColumn<Teacher, String> teachersIdColumn;
  @FXML private TableColumn<Teacher, String> teachersNameColumn;
  @FXML private TableColumn<Teacher, String> teachersCoursesColumn;

  private ScheduleModelManager scheduleModelManager = new ScheduleModelManager();

  public void tabChanged(Event event)
  {
    if (sessionsTab.isSelected())
    {
      sessionsTab.setText("sad");
    }
    else if (classesTab.isSelected())
    {
      classesSemesterColumn.setCellValueFactory(new PropertyValueFactory<Class,String>("semester"));
      classesGroupColumn.setCellValueFactory(new PropertyValueFactory<Class,String>("group"));
      classesStudentCountColumn.setCellValueFactory(new PropertyValueFactory<Class,String>("studentCount"));
      classesCoursesColumn.setCellValueFactory(new PropertyValueFactory<Class,String>("courses"));
      classesTable.getItems().clear();
      ClassList classList=scheduleModelManager.getAllClasses();
     /*for(int i=0;i<classList.getSize();i++){
        classesTable.getItems().add(classList.getAllClasses().get(i));
      }*/
    }
    else if (classroomsTab.isSelected())
    {
      classroomsNameColumn.setCellValueFactory(new PropertyValueFactory<Classroom, String>("name"));
      classroomsCapacityColumn.setCellValueFactory(new PropertyValueFactory<Classroom, String>("capacity"));
      classroomsTable.getItems().clear();
      ClassroomList classroomList=scheduleModelManager.getAllClassrooms();
      for(int i=0;i<classroomList.getSize();i++){
        classroomsTable.getItems().add(classroomList.getAllClassrooms().get(i));
      }
    }

    else if (coursesTab.isSelected())
    {
      coursesNameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
      coursesSemesterColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("semester"));
      coursesGroupColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("group"));
      coursesTeacherColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("teachersId"));
      coursesEctsColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("ects"));

      coursesTable.getItems().clear();

      CourseList courseList=scheduleModelManager.getAllCourses();
      for(int i=0;i<courseList.getSize();i++)
      {
        coursesTable.getItems().add(courseList.getAllCourses().get(i));
      }

    }
    else if (studentsTab.isSelected())
    {
      studentsIdColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
      studentsNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
      studentsSemesterColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("semester"));
      studentsGroupColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("group"));

      studentsTable.getItems().clear();

      StudentList studentList = scheduleModelManager.getAllStudents();
      for (int i = 0; i < studentList.getSize(); i++)
        studentsTable.getItems().add(studentList.getAllStudents().get(i));
    }
    else if (teachersTab.isSelected())
    {
      //do teachers stuff
      teachersIdColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("id"));
      teachersNameColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("name"));
      //teachersCoursesColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("courses"));
      //getting taught courses later

      teachersTable.getItems().clear();

      TeacherList teacherList = scheduleModelManager.getAllTeachers();
      for (int i = 0; i < teacherList.getSize(); i++)
        teachersTable.getItems().add(teacherList.getAllTeachers().get(i));
    }
  }
}
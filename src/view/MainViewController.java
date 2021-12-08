package view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import module.*;

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
      sessionsTab.setText("happy");
    }
    else if (classroomsTab.isSelected())
    {
      sessionsTab.setText("hungry");
    }
    else if (coursesTab.isSelected())
    {
      sessionsTab.setText("angry");
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
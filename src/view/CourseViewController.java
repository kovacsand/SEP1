package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import module.*;

public class CourseViewController {
  private Region root;
  private ScheduleModelManager scheduleModelManager;
  private ViewHandler viewHandler;

  //BUTTONS
  @FXML private Button addTeacherBtn;
  @FXML private Button removeTeacherBtn;
  @FXML private Button addStudentBtn;
  @FXML private Button removeStudentBtn;
  @FXML private Button saveCourse;
  @FXML private Button closeButton;

  //FIELDS
  @FXML private TextField courseName;
  @FXML private TextField ectsText;
  @FXML private TextField addTeacherId;
  @FXML private ListView<TeacherList> teachersList;
  @FXML private TextField addStudentId;
  @FXML private ListView<StudentList> studentsList;


  public CourseViewController() {
  }

  /**
   * Initialising view
   * @param viewHandler
   * @param scheduleModelManager
   * @param root
   */
  public void init(ViewHandler viewHandler, ScheduleModelManager scheduleModelManager, Region root) {
    this.scheduleModelManager = scheduleModelManager;
    this.root = root;
    this.viewHandler = viewHandler;

  }

  public void reset() {
    courseName.setText("");
    ectsText.setText("");
    addTeacherId.setText("");
    addStudentId.setText("");
  }

  public Region getRoot(){
    return root;
  }

  public void handleActions(ActionEvent e) {
    if (e.getSource() == saveCourse) {
      viewHandler.openView("MainView");
    }
    else if(e.getSource() == closeButton)
    {
      viewHandler.openView("MainView");
    }
    else if(e.getSource() == addTeacherBtn)
    {
     //scheduleModelManager.addTeacherToCourse(teacherId.getText(), courseName.getText());
    }

  }

  public void fillCourses(Course course){
    courseName.setText(course.getName());
    courseName.setEditable(false);
    ectsText.setText(course.getEcts()+"");
    fillTeachersTable(course);
    fillStudentsTable(course);

  }
  public void fillTeachersTable(Course course) {
    ObservableList<TeacherList> teachers = FXCollections.observableArrayList(scheduleModelManager.getCourse(course.getId()).getAllTeachers());
    teachersList.setItems(teachers);

  }

  public void fillStudentsTable(Course course) {
ObservableList<StudentList> students = FXCollections.observableArrayList(scheduleModelManager.getCourse(course.getId()).getAllStudents());
studentsList.setItems(students);
  }
}
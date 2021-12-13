package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import module.Course;
import module.ScheduleModelManager;
import module.Student;
import module.Teacher;

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
  @FXML private TableView<Teacher> teachersList;
  @FXML private TableColumn<Course, String> teacherId;
  @FXML private TableColumn<Course, String> teacherName;
  @FXML private TextField addStudentId;
  @FXML private TableView<Student> studentsList;
  @FXML private TableColumn<Course, String> studentId;
  @FXML private TableColumn<Course, String> studentName;



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

  }

  public void fillCourses(Course course){
    courseName.setText(course.getName());
    ectsText.setText(course.getEcts()+"");


  }
  public void fillTeachersTable() {

//teachersList.setCellFactory();
  }

  public void fillStudentsTable() {
  }
}
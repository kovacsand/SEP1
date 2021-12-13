package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import module.ScheduleModelManager;
import module.Student;
import module.Teacher;

public class CourseViewController {
  private Region root;
  private ScheduleModelManager scheduleModelManager;
  private ViewHandler viewHandler;

  //BUTTONS
  @FXML
  Button addTeacherBtn;
  @FXML
  Button removeTeacherBtn;
  @FXML
  Button addStudentBtn;
  @FXML
  Button removeStudentBtn;
  @FXML
  Button saveCourse;
  @FXML
  Button closeButton;

  //FIELDS
  @FXML TextField courseName;
  @FXML TextField ectsText;
  @FXML TextField addTeacherId;
  @FXML ListView<Teacher> teachersList;
  @FXML TextField addStudentId;
  @FXML ListView<Student> studentsList;

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

  public void fillTeachersTable() {
  }

  public void fillStudentsTable() {
  }
}
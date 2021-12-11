package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import module.ScheduleModelManager;
import module.Student;

public class StudentViewController {
  private Region root;
  private ScheduleModelManager scheduleModelManager;
  private ViewHandler viewHandler;
  @FXML
  Button saveStudent;
  @FXML
  Button close;
  @FXML
  TextField studentName;
  @FXML
  TextField studentId;
  @FXML
  ChoiceBox<String> studentSemester;
  @FXML
  ChoiceBox<String> studentGroup;

  public StudentViewController() {
  }

  public void init(ViewHandler viewHandler, ScheduleModelManager scheduleModelManager, Region root) {
    this.scheduleModelManager = scheduleModelManager;
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void reset() {
    this.studentName.setText("");
    this.studentId.setText("");
    this.studentGroup.setValue("");
    this.studentSemester.setValue("");
  }

  public void handleActions(ActionEvent e) {
    if (e.getSource() == this.saveStudent) {
      this.reset();
      this.viewHandler.openView("MainView");
    } else if (e.getSource() == this.close) {
      this.reset();
      this.viewHandler.openView("MainView");
    }

  }

  public Region getRoot() {
    return root;
  }

  public void fillStudentFields(Student student) {
    this.studentName.setText(student.getName());
    this.studentId.setText(student.getId());
    this.studentSemester.setValue(student.getSemester()+"");
    this.studentGroup.setValue(student.getGroup());
  }
}


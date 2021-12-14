package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import module.ScheduleModelManager;
import module.Student;
import module.StudentList;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StudentViewController {
  private Region root;
  private ScheduleModelManager scheduleModelManager;
  private ViewHandler viewHandler;

  @FXML private Button saveStudent;
  @FXML private Button close;
  @FXML private TextField studentName;
  @FXML private TextField studentId;
  @FXML private ChoiceBox<Integer> studentSemester;
  @FXML private ChoiceBox<String> studentGroup;

  public StudentViewController() {
  }

  public void init(ViewHandler viewHandler, ScheduleModelManager scheduleModelManager, Region root)
  {
    this.scheduleModelManager = scheduleModelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    reset();
  }


  public void reset()
  {
    this.studentName.setText("");
    this.studentId.setText("");
    String[] groups = {"X", "Y", "Z", "DK"};
    this.studentGroup.getItems().clear();
    this.studentGroup.getItems().addAll(groups);
    Integer[] semesters = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    this.studentSemester.getItems().clear();
    this.studentSemester.setDisable(false);
    this.studentSemester.getItems().addAll(semesters);
    studentId.setEditable(true);
  }


  public void handleActions(ActionEvent e)
  {
    //SAVE A STUDENT
    if (e.getSource() == saveStudent) {
      scheduleModelManager.editStudent(studentId.getText(), studentName.getText(), studentSemester.getValue(), studentGroup.getValue());
      reset();
      viewHandler.openView("MainView");
      viewHandler.getMainViewController().reset();
    } 
    else if (e.getSource() == close)
    {
      reset();
      viewHandler.openView("MainView");
    }

  }

  public Region getRoot()
  {
    return root;
  }

  public void fillStudentFields(Student student)
  {
    studentName.setText(student.getName());
    studentId.setText(student.getId());
    studentId.setEditable(false);
    studentSemester.getSelectionModel().select(student.getSemester());
    studentSemester.setDisable(true);
    studentGroup.getSelectionModel().select(student.getGroup());
  }
}


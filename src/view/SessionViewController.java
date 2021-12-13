package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import module.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class SessionViewController {
  private Region root;
  private ScheduleModelManager scheduleModelManager;
  private ViewHandler viewHandler;

  //SESSION BUTTONS

  @FXML private Button saveBtn;
  @FXML private Button closeBtn;
  @FXML private TextField startTimeField;
  @FXML private TextField endTimeField;
  @FXML private ChoiceBox<String> courseBox;
  @FXML private ChoiceBox<String> classroomBox;
  @FXML private DatePicker datePicker;

  public SessionViewController() {
  }

  public void init(ViewHandler viewHandler, ScheduleModelManager scheduleModelManager, Region root) {
    this.scheduleModelManager = scheduleModelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    reset();
  }


  public void reset() {
    startTimeField.setText("");
    endTimeField.setText("");
    String[] courses = {"SDJ1X", "SDJ1Y", "SDJ1Z", "SDJ1DK"};
    this.courseBox.getItems().clear();
    this.courseBox.getItems().addAll(courses);
    classroomBox.setValue("");
    datePicker.setValue(LocalDate.now());
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == saveBtn)
    {
      reset();
      viewHandler.openView("MainView");
    }
    else if (e.getSource() == closeBtn)
    {
      reset();
      viewHandler.openView("MainView");
    }
  }

  public Region getRoot() {
    return this.root;
  }

  //THIS NEEDS UPDATING 13/12/2021
  public void fillSessionFields(Session session) {
    courseBox.setValue(session.getCourse().getId());
    courseBox.isDisabled();
    datePicker.setValue(LocalDate.now());
    startTimeField.setText(session.getInterval().getStartTime()+"");
    endTimeField.setText(session.getInterval().getEndTime()+"");
    classroomBox.setValue(session.getClassroomString());
  }
}

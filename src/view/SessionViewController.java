package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import module.Classroom;
import module.ScheduleModelManager;
import module.Session;
import module.Teacher;

import java.time.LocalDate;

public class SessionViewController {
  private Region root;
  private ScheduleModelManager scheduleModelManager;
  private ViewHandler viewHandler;

  //SESSION BUTTONS

  @FXML Button saveBtn;
  @FXML Button closeBtn;
  @FXML TextField startTimeField;
  @FXML TextField endTimeField;
  @FXML ChoiceBox<String> courseBox;
  @FXML ChoiceBox<String> classroomBox;
  @FXML DatePicker datePicker;

  public SessionViewController() {
  }

  public void init(ViewHandler viewHandler, ScheduleModelManager scheduleModelManager, Region root) {
    this.scheduleModelManager = scheduleModelManager;
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void reset() {
    startTimeField.setText("");
    endTimeField.setText("");
    courseBox.setValue("");
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

  public void fillSessionFields(Session session) {
    courseBox.setValue(session.getCourse().getId());
    courseBox.isDisabled();
    datePicker.setValue(LocalDate.now());
    startTimeField.setText(session.getInterval().getStartTime()+"");
    endTimeField.setText(session.getInterval().getEndTime()+"");
    classroomBox.setValue(session.getClassroomString());
  }
}

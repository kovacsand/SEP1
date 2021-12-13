package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import module.ScheduleModelManager;
import module.Teacher;

public class TeacherViewController {
  private Region root;
  private ScheduleModelManager scheduleModelManager;
  private ViewHandler viewHandler;
  @FXML Button saveTeacher;
  @FXML Button close;
  @FXML TextField teacherName;
  @FXML TextField teacherId;

  public TeacherViewController() {
  }

  public void init(ViewHandler viewHandler, ScheduleModelManager scheduleModelManager, Region root)
  {
    this.scheduleModelManager = scheduleModelManager;
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void reset()
  {
    this.teacherId.setText("");
    this.teacherName.setText("");
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == saveTeacher)
    {
      reset();
      viewHandler.openView("MainView");
    }
    else if (e.getSource() == close)
    {
      reset();
      viewHandler.openView("MainView");
    }

  }

  public Region getRoot()
  {
    return this.root;
  }

  public void fillTeacherFields(Teacher teacher)
  {
    teacherName.setText(teacher.getName());
    teacherId.setText(teacher.getId());
    teacherId.setEditable(false);
  }
}
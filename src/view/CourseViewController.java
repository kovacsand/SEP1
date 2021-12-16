package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import module.*;

import java.util.Locale;

public class CourseViewController {
  private Region root;
  private ScheduleModelManager scheduleModelManager;
  private ViewHandler viewHandler;
  private Course thisCourse;

  //BUTTONS
  @FXML private Button addTeacherBtn;
  @FXML private Button removeTeacherBtn;
  @FXML private Button addStudentBtn;
  @FXML private Button removeStudentBtn;
  @FXML private Button closeButton;

  //FIELDS
  @FXML private TextField courseName;
  @FXML private TextField ectsText;
  @FXML private TextField addTeacherId;
  @FXML private ListView<Teacher> teachersList;
  @FXML private TextField addStudentId;
  @FXML private ListView<Student> studentsList;


  public CourseViewController()
  {
  }

  public void init(ViewHandler viewHandler, ScheduleModelManager scheduleModelManager, Region root)
  {
    this.scheduleModelManager = scheduleModelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    thisCourse = scheduleModelManager.getCourse(courseName.getText());
  }

  public void reset() {
    courseName.setText("");
    ectsText.setText("");
    addTeacherId.setText("");
    addStudentId.setText("");
    teachersList.setItems(null);
    studentsList.setItems(null);
  }

  public Region getRoot(){
    return root;
  }

  public void handleActions(ActionEvent e)
  {
    if(e.getSource() == closeButton)
    {
      viewHandler.openView("MainView");
    }
    else if(e.getSource() == addTeacherBtn)
    {
      //CONFIRMATION
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "Add " + scheduleModelManager.getTeacher(addTeacherId.getText()).getName()
          + " " + addTeacherId.getText() + "?",
          ButtonType.YES, ButtonType.NO);

      alert.showAndWait();
      if (alert.getResult() == ButtonType.YES)
      {
      scheduleModelManager.addTeacherToCourse(addTeacherId.getText().toUpperCase(Locale.ROOT),
          courseName.getText());
      System.out.println(addTeacherId.getText() + " ADD SELECTED");
      Course temp = scheduleModelManager.getCourse(courseName.getText());
      fillCourses(temp);
      fillTeachersTable(scheduleModelManager.getCourse(courseName.getText()));
      addTeacherId.setText("");
      }
    }
   else if(e.getSource() == removeTeacherBtn)
    {

      Teacher selected = teachersList.getSelectionModel().getSelectedItem();
      //CONFIRMATION
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Remove " + selected.getName() + " " + selected.getId() + "?",
          ButtonType.YES, ButtonType.NO);

      alert.showAndWait();
      if (alert.getResult() == ButtonType.YES)
      scheduleModelManager.removeTeacherFromCourse(selected.getId(), courseName.getText());
      fillTeachersTable(scheduleModelManager.getCourse(courseName.getText()));
    }
    else if(e.getSource() == addStudentBtn)
    {
      //CONFIRMATION
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Add " +
          scheduleModelManager.getStudent(addStudentId.getText()).getName() + " " + addStudentId.getText() + "?",
          ButtonType.YES, ButtonType.NO);

      alert.showAndWait();
      if (alert.getResult() == ButtonType.YES)
      scheduleModelManager.addStudentToCourse(addStudentId.getText(),
          courseName.getText());
      System.out.println(addStudentId.getText() + " ADD SELECTED");
      Course temp = scheduleModelManager.getCourse(courseName.getText());
      fillCourses(temp);
      fillStudentsTable(scheduleModelManager.getCourse(courseName.getText()));
      addStudentId.setText("");

    }
    else if(e.getSource() == removeStudentBtn)
    {
      Student selected = studentsList.getSelectionModel().getSelectedItem();
      if(selected != null)
      {
        //CONFIRMATION
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
            "Remove " + selected.getName() + " " + selected.getId() + "?",
            ButtonType.YES, ButtonType.NO);

        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES)
        scheduleModelManager.removeStudentFromCourse(selected.getId(),
            courseName.getText());
      }
      fillStudentsTable(scheduleModelManager.getCourse(courseName.getText()));
    }
  }

  public void fillCourses(Course course){
    courseName.setText(course.getId());
    courseName.setEditable(false);
    ectsText.setText(course.getEcts()+"");
    fillTeachersTable(course);
    fillStudentsTable(course);
  }
  public void fillTeachersTable(Course course) {
    ObservableList<Teacher> teachers = FXCollections.observableArrayList(course.getAllTeachers().getAllTeachers());
    teachersList.setItems(teachers);
  }

  public void fillStudentsTable(Course course) {
ObservableList<Student> students = FXCollections.observableArrayList(course.getAllStudents().getAllStudents());
studentsList.setItems(students);
  }
}
package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;
import module.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SessionViewController {
  private Region root;
  private ScheduleModelManager scheduleModelManager;
  private ViewHandler viewHandler;

  private MyDate date;
  private ClassroomList classrooms;
  private ArrayList<String> classes = new ArrayList<>();
  private CourseList coursesList;
  private ArrayList<String> courses = new ArrayList<>();
  private TimeInterval timeInterval;
  private Session tempSesh;

  //SESSION BUTTONS

  @FXML private Button saveBtn;
  @FXML private Button closeBtn;
  @FXML private TextField startTimeField;
  @FXML private TextField endTimeField;
  @FXML private ChoiceBox<String> courseBox;
  @FXML private ChoiceBox<String> classroomBox;
  @FXML private DatePicker datePicker;
  @FXML private Button checkBtn;



  public SessionViewController() {
  }

  public void init(ViewHandler viewHandler, ScheduleModelManager scheduleModelManager, Region root) {
    this.scheduleModelManager = scheduleModelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    reset();
    coursesList = scheduleModelManager.getAllCourses();
    courseBox.getItems().addAll(courses);
    classrooms = scheduleModelManager.getAllClassrooms();
    classroomBox.getItems().addAll(classes);
    //Setting Date
    String pattern = "dd/MM/yy";
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
    date = new MyDate(datePicker.getValue().getDayOfMonth(), datePicker.getValue().getMonthValue(), datePicker.getValue().getYear());
    for (int i = 0; i < classrooms.getSize(); i++)
    {
      classes.add(classrooms.getAllClassrooms().get(i).getName());
    }
    for (int i = 0; i < coursesList.getSize(); i++)
    {
      courses.add(coursesList.getAllCourses().get(i).getId());
    }

  }


  public void reset() {
    startTimeField.setText("");
    endTimeField.setText("");
    courseBox.getItems().clear();
    courseBox.getItems().addAll(courses);
    courseBox.setDisable(false);
    classroomBox.getItems().clear();
    classroomBox.getItems().addAll(classes);
    classroomBox.setDisable(true);
    datePicker.setValue(LocalDate.now());
  }

  /*public ArrayList<String> getFreeClassrooms()
  {
    ArrayList<String> freeClasses = new ArrayList<>();
    for (int i = 0; i < classrooms.getSize(); i++)
    {
      if(classrooms.getAllClassrooms().get(i).isFree(date, new TimeInterval(Integer.parseInt(startTimeField.getText()),Integer.parseInt(endTimeField.getText()))) &&  );
      {
        freeClasses.add(classrooms.getAllClassrooms().get(i).getName());
      }
    }
    return freeClasses;
  }*/

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == saveBtn)
    {
      Classroom selectedClassroom = scheduleModelManager.getAllClassrooms().getClassroom(classroomBox.getValue());
      Course selectedCourse = scheduleModelManager.getCourse(courseBox.getValue());
      Session temp = new Session(date, new TimeInterval(Integer.parseInt(startTimeField.getText()),Integer.parseInt(endTimeField.getText())), selectedClassroom, selectedCourse);
      scheduleModelManager.addSession(temp);
      scheduleModelManager.removeSession(tempSesh);
      reset();
      viewHandler.openView("MainView");
    }
    else if (e.getSource() == closeBtn)
    {
      reset();
      viewHandler.openView("MainView");
    }

    else if (e.getSource() == checkBtn)
    if(!(courseBox.getValue().isBlank() && startTimeField.getText().isEmpty() && endTimeField.getText().isEmpty()))
      {
        classroomBox.getItems().clear();
        classroomBox.getItems().addAll(classes);
        classroomBox.setDisable(false);
      }

  }

  public Region getRoot() { return root; }


  public void fillSessionFields(Session session) {
    courseBox.setValue(session.getCourse().getId());
    courseBox.setDisable(true);
    datePicker.setValue(LocalDate.now());
    startTimeField.setText(session.getInterval().getStartTime()+"");
    endTimeField.setText(session.getInterval().getEndTime()+"");
    classroomBox.setValue(session.getClassroomString());
    classroomBox.setDisable(true);
    tempSesh = scheduleModelManager.getSession(session.getId());
  }

}

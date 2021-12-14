package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
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
  private Session tempOldSession;

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

    //FILLING COURSES
    coursesList = scheduleModelManager.getAllCourses();
    for (int i = 0; i < coursesList.getSize(); i++)
      courses.add(coursesList.getAllCourses().get(i).getId());
    courseBox.getItems().addAll(courses);
  }


  public void reset() {
    startTimeField.setText("");
    endTimeField.setText("");
    courseBox.getItems().clear();
    courseBox.getItems().addAll(courses);
    courseBox.setDisable(false);
    classroomBox.getItems().clear();
    datePicker.setValue(LocalDate.now());
    tempOldSesh = null;
  }

  public ArrayList<String> getFreeClassrooms()
  {
    Course foundCourse = null;
    for (int i = 0; i < coursesList.getSize(); i++)
      if (coursesList.getAllCourses().get(i).getId().equals(courseBox.getValue()))
        foundCourse = scheduleModelManager.getCourse(courseBox.getValue());

    ArrayList<String> freeClasses = new ArrayList<>();
    ArrayList<Classroom> bigEnoughClassrooms = classrooms.getClassrooms(foundCourse.getAllStudents().getSize());

    for (int i = 0; i < bigEnoughClassrooms.size(); i++)
      if (bigEnoughClassrooms.get(i).isFree(date, timeInterval))
        freeClasses.add(bigEnoughClassrooms.get(i).getName());

      if (freeClasses.size() < 1)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION,
            "There are no available classrooms for this time or date.",
            ButtonType.OK);

        alert.showAndWait();
        startTimeField.setText("");
        endTimeField.setText("");
    }
    return freeClasses;
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == saveBtn)
    {
      if(courseBox.getValue().isBlank() || startTimeField.getText().equals("") || endTimeField.getText().equals(""))
      {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
            "Please fill in all areas first", ButtonType.OK);

        alert.showAndWait();
      }
      else
      {
        if (tempOldSession != null)
          tempOldSession = null;

        Classroom selectedClassroom = scheduleModelManager.getAllClassrooms().getClassroom(classroomBox.getValue());
        Course selectedCourse = scheduleModelManager.getCourse(courseBox.getValue());

        scheduleModelManager.addSession(new Session(date, timeInterval, selectedClassroom, selectedCourse));

        reset();
        viewHandler.openView("MainView");
      }
    }
    else if (e.getSource() == closeBtn)
    {
      reset();
      if (tempOldSession != null)
      {
        scheduleModelManager.addSession(tempOldSession);
        tempOldSession = null;
      }
      viewHandler.openView("MainView");
    }

    else if (e.getSource() == checkBtn)
    {
      if (courseBox.getValue() == null || startTimeField.getText().equals("") || endTimeField.getText().equals(""))
      {
        System.out.println("ALERT!");
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
            "Please fill in all areas first", ButtonType.OK);

        alert.showAndWait();
      }
      else
      {
        date = new MyDate(datePicker.getValue().getDayOfMonth(), datePicker.getValue().getMonthValue(), datePicker.getValue().getYear());
        timeInterval = new TimeInterval(Integer.parseInt(startTimeField.getText()), Integer.parseInt(endTimeField.getText()));
        classrooms = scheduleModelManager.getAllClassrooms();
        classroomBox.getItems().clear();
        classroomBox.getItems().addAll(getFreeClassrooms());
        classroomBox.setDisable(false);
      }
    }
  }

  public Region getRoot() { return root; }

  public void fillSessionFields(Session session) {
    date = session.getDate();
    String dateString = (2000 + date.getYear()) + "-" + date.getMonth() + "-" + date.getDay();
    LocalDate localDate = LocalDate.parse(dateString);

    courseBox.setValue(session.getCourse().getId());
    courseBox.setDisable(true);

    datePicker.setValue(localDate);
    startTimeField.setText(session.getInterval().getStartTime()+"");
    endTimeField.setText(session.getInterval().getEndTime()+"");

    tempOldSession = scheduleModelManager.getSession(session.getId());
    scheduleModelManager.removeSession(session);
  }
}

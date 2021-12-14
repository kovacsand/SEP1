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
  private Session tempOldSesh;
  private Session tempNewSession;

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

    //FILLING CLASSROOMS
    classrooms = scheduleModelManager.getAllClassrooms();
    for (int i = 0; i < classrooms.getSize(); i++)
    {
      classes.add(classrooms.getAllClassrooms().get(i).getName());
    }
    classroomBox.getItems().addAll(classes);

    //FILLING COURSES
    coursesList = scheduleModelManager.getAllCourses();
    for (int i = 0; i < coursesList.getSize(); i++)
    {
      courses.add(coursesList.getAllCourses().get(i).getId());
    }
    courseBox.getItems().addAll(courses);

    //Setting Date

   // date = new MyDate(datePicker.getValue().getDayOfMonth(), datePicker.getValue().getMonthValue(), datePicker.getValue().getYear());
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

  public ArrayList<String> getFreeClassrooms()
  {
    Course foundCourse = null;
    for (int i = 0; i < coursesList.getSize(); i++)
    {
      if (coursesList.getAllCourses().get(i).getId().equals(courseBox.getValue()))
        foundCourse = scheduleModelManager.getCourse(courseBox.getValue());
    }
    ArrayList<String> freeClasses = new ArrayList<>();
    ArrayList<Classroom> bigEnoughClassrooms = classrooms.getClassrooms(
        foundCourse.getAllStudents().getSize());
    System.out.println(foundCourse.getAllStudents().getSize());
    for (int i = 0; i < bigEnoughClassrooms.size(); i++)
    {
      if (bigEnoughClassrooms.get(i).isFree(date, timeInterval))
      {
        System.out.println(date);
        System.out.println(timeInterval);
        freeClasses.add(bigEnoughClassrooms.get(i).getName());
      }
    }
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
        System.out.println("New session created");
        Classroom selectedClassroom = scheduleModelManager.getAllClassrooms().getClassroom(classroomBox.getValue());
        Course selectedCourse = scheduleModelManager.getCourse(courseBox.getValue());

        tempNewSession = new Session(date, timeInterval, selectedClassroom,
            selectedCourse);

        if (tempOldSesh != null)
        {
          System.out.println("Remove a session, but in reality edit it");
          scheduleModelManager.removeSession(tempOldSesh);
        }
        scheduleModelManager.addSession(tempNewSession);

        reset();
        viewHandler.openView("MainView");
      }
    }
    else if (e.getSource() == closeBtn)
    {
      reset();
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
        date = new MyDate(datePicker.getValue().getDayOfMonth(),
            datePicker.getValue().getMonthValue(),
            datePicker.getValue().getYear());
        timeInterval = new TimeInterval(Integer.parseInt(startTimeField.getText()),
            Integer.parseInt(endTimeField.getText()));
        System.out.println(date);
        System.out.println(timeInterval);
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
    classroomBox.setValue(session.getClassroomString());
    classroomBox.setDisable(true);
    tempOldSesh = scheduleModelManager.getSession(session.getId());
  }

}

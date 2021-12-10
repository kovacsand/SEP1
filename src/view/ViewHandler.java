package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import module.ScheduleModelManager;

import java.io.IOException;

public class ViewHandler
{

  private Scene scene;
  private Stage window;
  private MainViewController mainViewController;
  private StudentViewController studentViewController;
  private TeacherViewController teacherViewController;
  private CourseViewController courseViewController;
  private SessionsViewController sessionViewController;

  private ScheduleModelManager scheduleModelManager;

  //CONSTRUCTOR
  public ViewHandler(ScheduleModelManager modelManager) throws IOException
  {
    this.scheduleModelManager = modelManager;
    scene = new Scene(new Region());
  }

  //STARTS AND SETS MAINVIEW
  public void start(Stage window)
  {
    this.window = window;
    openView("MainView");
  }

  //LOADS NEW VIEWS
  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "MainView":
        root = loadViewMain();
        break;
      case "StudentView":
        root = loadViewStudent();
        break;
      case "TeacherView":
        root = loadViewTeacher();
        break;
      case "CourseView":
        root = loadViewCourse();
        break;
      case "SessionView":
        root = loadViewSession;
        break;
    }
    scene.setRoot(root);
    String title = "";

    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }

    window.setTitle(title);
    window.setScene(scene);
    window.setWidth(450);
    window.setHeight(480);
    window.show();
  }

  private Region loadViewMain()
  {
    if (mainViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainView.fxml"));
        Region root = loader.load();
        mainViewController = loader.getController();
        mainViewController.init(this, scheduleModelManager, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      mainViewController.reset();
    }

    return mainViewController.getRoot();
  }

  private Region loadViewStudent()
  {
    if (studentViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("StudentView.fxml"));
        Region root = loader.load();
        studentViewController = loader.getController();
        studentViewController.init(this, scheduleModelManager, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    return studentViewController.getRoot();
  }

  private Region loadViewTeacher()
  {
    if (teacherViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TeacherView.fxml"));
        Region root = loader.load();
        teacherViewController = loader.getController();
        teacherViewController.init(this, scheduleModelManager, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    return teacherViewController.getRoot();
  }


  public StudentViewController getStudentViewController(){
    return studentViewController;
  }

  public TeacherViewController getTeacherViewController(){
    return teacherViewController;
  }

  public CourseViewController getCourseViewController()
  {
    return courseViewController;
  }

  public MainViewController getMainViewController()
  {
    return mainViewController;
  }

  public SessionsViewController getSessionViewController()
  {
    return sessionViewController;
  }
}


package view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import module.*;
import module.Class;

public class MainViewController
{
  private Region root;
  private ScheduleModelManager scheduleModelManager;
  private ViewHandler viewHandler;

  //MAINVIEW BUTTONS
  @FXML private Button addSessionBtn;
  @FXML private Button editSessionBtn;
  @FXML private Button removeSessionBtn;
  @FXML private Button editCourseBtn;
  @FXML private Button addStudentBtn;
  @FXML private Button editStudentBtn;
  @FXML private Button removeStudentBtn;
  @FXML private Button addTeacherBtn;
  @FXML private Button editTeacherBtn;
  @FXML private Button removeTeacherBtn;

  public void init(ViewHandler viewHandler, ScheduleModelManager scheduleModelManager, Region root)
  {
this.viewHandler = viewHandler;
this.scheduleModelManager = scheduleModelManager;
this.root = root;
  }

public Region getRoot()
{
  return root;
}

public void reset(){

}

public void handleAction(ActionEvent e)
{
if(e.getSource() == addSessionBtn)
{
  viewHandler.openView("SessionView");
}
else if (e.getSource() == editSessionBtn)
{
  //ENTER THE FUNCTIONALITY HERE
  viewHandler.openView("SessionView");
}
else if (e.getSource() == removeSessionBtn)
{
  //FUNCTIONALLY REMOVE SESSION
  sessionsTable.refresh();
}
else if (e.getSource() == editCourseBtn)
{
  viewHandler.openView("CourseView");
}
else if (e.getSource() == addStudentBtn)
{
  viewHandler.openView("StudentView");
}
else if (e.getSource() == editStudentBtn)
{
  Student selected = studentsTable.getSelectionModel().getSelectedItem();
  viewHandler.openView("StudentView");
}
else if (e.getSource() == removeStudentBtn)
{
  //REMOVE A STUDENT FUNCTION
  studentsTable.refresh();
}
else if (e.getSource() == addTeacherBtn)
{
  viewHandler.openView("TeacherView");
}
else if (e.getSource() == editTeacherBtn)
{
  Teacher selected = teachersTable.getSelectionModel().getSelectedItem();
  viewHandler.openView("TeacherView");
}
else if (e.getSource() == removeTeacherBtn)
{
  //REMOVE A TEACHER FUNCTION
  teachersTable.refresh();
}

}


  //GENERAL TABS
  @FXML private TabPane tabPane;
  @FXML private Tab sessionsTab;
  @FXML private Tab classesTab;
  @FXML private Tab classroomsTab;
  @FXML private Tab coursesTab;
  @FXML private Tab studentsTab;
  @FXML private Tab teachersTab;

  //SESSIONS TABLE
  @FXML private TableView<Session> sessionsTable;
  @FXML private TableColumn<Session, String> sessionsCourseColumn;
  @FXML private TableColumn<Session, String> sessionsDateColumn;
  @FXML private TableColumn<Session, String> sessionsTimeColumn;
  @FXML private TableColumn<Session, String> sessionsClassroomColumn;

  //CLASSES TABLE
  @FXML private TableView<Class> classesTable;
  @FXML private TableColumn<Class,String> classesSemesterColumn;
  @FXML private TableColumn<Class,String> classesGroupColumn;
  @FXML private TableColumn<Class,String> classesStudentCountColumn;
  @FXML private TableColumn<Class,String> classesCoursesColumn;

  //CLASSROOMS TABLE
  @FXML private TableView<Classroom> classroomsTable;
  @FXML private TableColumn<Classroom, String> classroomsNameColumn;
  @FXML private TableColumn<Classroom, String> classroomsCapacityColumn;

  //COURSES TABLE
  @FXML private TableView<Course> coursesTable;
  @FXML private TableColumn<Course, String> coursesNameColumn;
  @FXML private TableColumn<Course, String> coursesSemesterColumn;
  @FXML private TableColumn<Course, String> coursesGroupColumn;
  @FXML private TableColumn<Course, String> coursesTeacherColumn;
  @FXML private TableColumn<Course, String> coursesEctsColumn;

  //STUDENTS TABLE
  @FXML private TableView<Student> studentsTable;
  @FXML private TableColumn<Student, String> studentsIdColumn;
  @FXML private TableColumn<Student, String> studentsNameColumn;
  @FXML private TableColumn<Student, String> studentsSemesterColumn;
  @FXML private TableColumn<Student, String> studentsGroupColumn;


  //TEACHERS TABLE
  @FXML private TableView<Teacher> teachersTable;
  @FXML private TableColumn<Teacher, String> teachersIdColumn;
  @FXML private TableColumn<Teacher, String> teachersNameColumn;


  public void tabChanged(Event event)
  {
    if (scheduleModelManager != null)
    {
      if (sessionsTab.isSelected())
      {
        sessionsCourseColumn.setCellValueFactory(new PropertyValueFactory<Session, String>("courseString"));
        sessionsDateColumn.setCellValueFactory(new PropertyValueFactory<Session, String>("dateString"));
        sessionsTimeColumn.setCellValueFactory(new PropertyValueFactory<Session, String>("timeString"));
        sessionsClassroomColumn.setCellValueFactory(new PropertyValueFactory<Session, String>("classroomString"));

        sessionsTable.getItems().clear();

        SessionList sessionList = scheduleModelManager.getAllSessions();
        for (int i = 0; i < sessionList.getSize(); i++)
          sessionsTable.getItems().add(sessionList.getAllSessions().get(i));
      }
      else if (classesTab.isSelected())
      {
        classesSemesterColumn.setCellValueFactory(new PropertyValueFactory<Class, String>("semester"));
        classesGroupColumn.setCellValueFactory(new PropertyValueFactory<Class, String>("group"));
        classesStudentCountColumn.setCellValueFactory(new PropertyValueFactory<Class, String>("studentCount"));
        classesCoursesColumn.setCellValueFactory(new PropertyValueFactory<Class, String>("courses"));
        classesTable.getItems().clear();
        ClassList classList = scheduleModelManager.getAllClasses();
        for (int i = 0; i < classList.getSize(); i++)
        {
          classesTable.getItems().add(classList.getAllClasses().get(i));
        }
      }
      else if (classroomsTab.isSelected())
      {
        classroomsNameColumn.setCellValueFactory(new PropertyValueFactory<Classroom, String>("name"));
        classroomsCapacityColumn.setCellValueFactory(new PropertyValueFactory<Classroom, String>("capacity"));
        classroomsTable.getItems().clear();
        ClassroomList classroomList = scheduleModelManager.getAllClassrooms();
        for (int i = 0; i < classroomList.getSize(); i++)
        {
          classroomsTable.getItems().add(classroomList.getAllClassrooms().get(i));
        }
      }

      else if (coursesTab.isSelected())
      {
        coursesNameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        coursesSemesterColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("semester"));
        coursesGroupColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("group"));
        coursesTeacherColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("teachersId"));
        coursesEctsColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("ects"));

        coursesTable.getItems().clear();

        CourseList courseList = scheduleModelManager.getAllCourses();
        for (int i = 0; i < courseList.getSize(); i++)
        {
          coursesTable.getItems().add(courseList.getAllCourses().get(i));
        }

      }
      else if (studentsTab.isSelected())
      {
        studentsIdColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        studentsNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        studentsSemesterColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("semester"));
        studentsGroupColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("group"));

        studentsTable.getItems().clear();

        StudentList studentList = scheduleModelManager.getAllStudents();
        for (int i = 0; i < studentList.getSize(); i++)
          studentsTable.getItems().add(studentList.getAllStudents().get(i));
      }
      else if (teachersTab.isSelected())
      {
        //do teachers stuff
        teachersIdColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("id"));
        teachersNameColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("name"));
        //teachersCoursesColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("courses"));
        //getting taught courses later

        teachersTable.getItems().clear();

        TeacherList teacherList = scheduleModelManager.getAllTeachers();
        for (int i = 0; i < teacherList.getSize(); i++)
          teachersTable.getItems().add(teacherList.getAllTeachers().get(i));
      }
    }
    else
    {
      scheduleModelManager = new ScheduleModelManager();
      if (sessionsTab.isSelected())
      {
        sessionsCourseColumn.setCellValueFactory(new PropertyValueFactory<Session, String>("courseString"));
        sessionsDateColumn.setCellValueFactory(new PropertyValueFactory<Session, String>("dateString"));
        sessionsTimeColumn.setCellValueFactory(new PropertyValueFactory<Session, String>("timeString"));
        sessionsClassroomColumn.setCellValueFactory(new PropertyValueFactory<Session, String>("classroomString"));

        sessionsTable.getItems().clear();

        SessionList sessionList = scheduleModelManager.getAllSessions();
        for (int i = 0; i < sessionList.getSize(); i++)
          sessionsTable.getItems().add(sessionList.getAllSessions().get(i));
      }
    }
  }
}
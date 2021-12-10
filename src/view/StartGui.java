package view;

import javafx.application.Application;
import javafx.stage.Stage;
import module.ScheduleModelManager;

public class StartGui extends Application
{
  public void start(Stage window) throws Exception
  {
    ScheduleModelManager scheduleModelManager = new ScheduleModelManager();
    ViewHandler viewHandler = new ViewHandler(scheduleModelManager);
    viewHandler.start(window);
  }
}

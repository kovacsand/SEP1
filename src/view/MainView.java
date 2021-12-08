package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainView extends Application
{

  //Only start method and loading the fxml

  public void start(Stage window) throws Exception
  {
    window.setTitle("Scheduler");
    window.setResizable(false);

    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("MainView.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    window.setScene(scene);
    window.show();
  }

  private class MyActionListener implements EventHandler<ActionEvent>
  {
    public void handle(ActionEvent event)
    {
      //This is for switching between scenes
      //So this only matters when we click buttons, not tabs
    }
  }



  //This is Allan's tab switcher, we're going to have 6 tabs

//  private class MyTabListener implements ChangeListener<Tab>
//  {
//    public void changed(ObservableValue<? extends Tab> tab, Tab oldTab, Tab newTab)
//    {
//      if (newTab == allStudentsTab)
//      {
//        allStudentsTab.updateStudentArea();
//      }
//      else if (newTab == changeCountryTab)
//      {
//        changeCountryTab.updateStudentListView();
//      }
//    }
//  }
}

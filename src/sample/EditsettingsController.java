/*
RMIT University Vietnam
INTE2512 Final Project - News Aggregator App
Nguyen Anh Minh
S3911237
 */
package sample;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class EditsettingsController implements Initializable {
    private User user;
    @FXML
    private ComboBox settings_country;
    @FXML
    private ToggleButton settings_business;
    @FXML
    private ToggleButton settings_entertainment;
    @FXML
    private ToggleButton settings_general;
    @FXML
    private ToggleButton settings_health;
    @FXML
    private ToggleButton settings_science;
    @FXML
    private ToggleButton settings_sports;
    @FXML
    private ToggleButton settings_technology;
    //User user;
    public void init(User getuser)
    {
        user=new User();
        user=getuser;
        System.out.println("hi "+user.getUsername()); 
        ConnectDatabase con=new ConnectDatabase();
      
        Queue<Integer> prefs=new LinkedList<>();
        prefs=con.getpreference(user.getUsername());
        System.out.println(prefs); 
        int a=prefs.remove();
        if (a==1)
        {
           settings_business.setSelected(true);
        }
        a=prefs.remove();
        if (a==1)
        {
           settings_entertainment.setSelected(true);
        }
        a=prefs.remove();
        if (a==1)
        {
           settings_general.setSelected(true);
        }
        a=prefs.remove();
        if (a==1)
        {
           settings_health.setSelected(true);
        }
        a=prefs.remove();
        if (a==1)
        {
           settings_science.setSelected(true);
        }
        a=prefs.remove();
        if (a==1)
        {
           settings_sports.setSelected(true);
        }
        a=prefs.remove();
        if (a==1)
        {
           settings_technology.setSelected(true);
        }
     }
       
        
    
    @FXML
    private void edit_clicked(ActionEvent event) throws IOException, Exception
    {
        ConnectDatabase con=new ConnectDatabase();
        String country=(String)settings_country.getValue();
        if(country==null)
        {
            country=con.getcountry(user.getUsername());
        }
        Map<String,Integer> m=new HashMap<>();
        m.put("business",0);
        m.put("entertainment",0);
        m.put("general",0);
        m.put("health",0);
        m.put("science",0);
        m.put("sports",0);
        m.put("technology",0);
        if(settings_business.isSelected())
            m.put("business",1);
        if(settings_entertainment.isSelected())
            m.put("entertainment",1);
        if(settings_general.isSelected())
            m.put("general",1);
        if(settings_health.isSelected())
            m.put("health",1);
        if(settings_science.isSelected())
            m.put("science",1);
        if(settings_sports.isSelected())
            m.put("sports",1);
        if(settings_technology.isSelected())
            m.put("technology",1);
        con.editDb(user.getUsername(), country, m);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Updated Successfully!");
        a.show();
        
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        MainController controller=loader.getController();
        controller.init(user);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void back_clicked(ActionEvent event) throws IOException, Exception
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        MainController controller=loader.getController();
        controller.init(user);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String country[] = { "in","us", "uk"}; 
       settings_country.setItems(FXCollections.observableArrayList(country));
    }    
    
}

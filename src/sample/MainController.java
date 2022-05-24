/*
RMIT University Vietnam
INTE2512 Final Project - News Aggregator App
Nguyen Anh Minh
S3911237
 */
package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController implements Initializable {

    User user;
    @FXML
    Button search_button;
    @FXML
    TextField search_text;
    @FXML
    Button logout_button;
    @FXML
    Button settings_button;
    @FXML
    Button refresh_button;
    @FXML
    AnchorPane main_news;
    
    ArrayList<NewsArray> news;
    EventHandler<MouseEvent> news_clicked=new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event){
            if(event.getSource() instanceof HBox)
            {
                try {
                    System.out.println("hello");
                    VBox urlbox=(VBox)((HBox)event.getSource()).getChildren().get(1);
                    Text url_text=(Text) (urlbox.getChildren().get(2));
                    String url=url_text.getText();
                    System.out.println(url);
                    url=url.substring(6);
                    
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("web.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    
                    WebController controller=loader.getController();
                    controller.init(url);
                    
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    };
    
    public void init(User getuser) throws Exception
    {
        user=new User();
        user=getuser;
        System.out.println(user.getUsername());
        
        news=News.topheadlines(user.getUsername());
        HBox outer[] = new HBox[news.size()];
        for (int i = 0; i < news.size(); i++) {
            String path = news.get(i).image;
            Image newsimage = new Image(path, 100, 100, false, false);
            ImageView img = new ImageView(newsimage);

            Text title = new Text();
            title.setText("Title : " + news.get(i).title);
            title.setWrappingWidth(400);
            Text description = new Text();
            description.setText("Descrption : " + news.get(i).desc);
            description.setWrappingWidth(400);

            Text url = new Text();
            url.setText("URL : " + news.get(i).hyperl);
            url.setWrappingWidth(400);

            outer[i] = new HBox();
            outer[i].setSpacing(5);
            outer[i].setPadding(new Insets(5, 5, 5, 5));
            outer[i].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
            VBox anch = new VBox();
            anch.setSpacing(5);
            anch.setPadding(new Insets(10, 2, 2, 2));
            anch.getChildren().add(title);
            anch.getChildren().add(description);
            anch.getChildren().add(url);
            //anch.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,new BorderWidths(3))));
            outer[i].getChildren().add(img);
            outer[i].getChildren().add(anch);
            outer[i].setOnMouseClicked(news_clicked);
            //anch[i].getChildren().add(new Button("button"));
        }

        ScrollPane search_scroll = new ScrollPane();
        VBox searchNewsBox = new VBox();
        searchNewsBox.setSpacing(10);
        searchNewsBox.getChildren().addAll(outer);
        search_scroll.setContent(searchNewsBox);
        search_scroll.setMaxHeight(336);
        search_scroll.setMinWidth(570);
        search_scroll.setMaxWidth(570);
        main_news.getChildren().add(search_scroll);
    }
    
    @FXML
    private void search_clicked(ActionEvent event) throws Exception {
        System.out.println("Test");
        System.out.println(user.getUsername());
        String keyword=search_text.getText();
        keyword=keyword.replace(" ", "%20");
        if(keyword.equals(""))
            return;
        
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("search.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        SearchController controller=loader.getController();
        controller.init(user,keyword);
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void logout_clicked(ActionEvent event) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void settings_clicked(ActionEvent event) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("editsettings.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        EditsettingsController controller=loader.getController();
        controller.init(user);
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void refresh_clicked(ActionEvent event) throws Exception 
    {
        news=News.topheadlines(user.getUsername());
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        EditsettingsController controller = loader.getController();
        controller.init(user);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image=new Image("test.png",20,20,false,false);
        search_button.setGraphic(new ImageView(image));
    }    
    
}

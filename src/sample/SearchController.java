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

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;


public class SearchController implements Initializable {
    User user;
    String keyword;

    @FXML
    AnchorPane search_news;

    @FXML
    ComboBox search_sort_by;

    @FXML
    Button search_button;

    @FXML
    TextField search_text;

    ArrayList<NewsArray> news;
    EventHandler<MouseEvent> news_clicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getSource() instanceof HBox) {
                try {
                    System.out.println("hello");
                    VBox urlbox = (VBox) ((HBox) event.getSource()).getChildren().get(1);
                    Text url_text = (Text) (urlbox.getChildren().get(2));
                    String url = url_text.getText();
                    System.out.println(url);
                    url = url.substring(6);

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("web.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);

                    WebController controller = loader.getController();
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


    public void init(User getuser, String getkeyword) throws Exception {
        user = new User();
        user = getuser;
        System.out.println(user.getUsername());
        keyword = getkeyword;
        news = News.everything(keyword, "relevancy");

        //Text text[]=new Text[news.size()];
        HBox outer[] = new HBox[news.size()];
        //VBox anch[]=new VBox[news.size()];
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
        }

        ScrollPane search_scroll = new ScrollPane();
        VBox search_news_box = new VBox();
        search_news_box.setSpacing(10);
        search_news_box.getChildren().addAll(outer);


        search_scroll.setContent(search_news_box);
        search_scroll.setMaxHeight(336);
        search_scroll.setMinWidth(570);
        search_scroll.setMaxWidth(570);

        search_news.getChildren().add(search_scroll);

    }

    @FXML
    private void search_clicked(ActionEvent event) throws Exception {
        System.out.println("clicked");
        System.out.println(user.getUsername());
        String keyword = search_text.getText();
        keyword = keyword.replace(" ", "%20");
        if (keyword.equals("")) {
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("search.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        SearchController controller = loader.getController();
        controller.init(user, keyword);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void sort_by_changed(ActionEvent event) throws Exception {
        String sort = (String) search_sort_by.getValue();
        if (sort == null) {
            sort = "relevancy";
        }
        try {
            news = News.everything(keyword, sort);

        } catch (Exception ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HBox outer[] = new HBox[news.size()];
        //VBox anch[]=new VBox[news.size()];
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
            outer[i].getChildren().add(img);
            outer[i].getChildren().add(anch);
            outer[i].setOnMouseClicked(news_clicked);
        }

        ScrollPane search_scroll = new ScrollPane();
        VBox search_news_box = new VBox();
        search_news_box.setSpacing(10);
        search_news_box.getChildren().addAll(outer);
        search_scroll.setContent(search_news_box);
        search_scroll.setMaxHeight(336);
        search_scroll.setMinWidth(570);
        search_scroll.setMaxWidth(570);
        search_news.getChildren().add(search_scroll); }

    @FXML
    private void back_clicked(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        MainController controller = loader.getController();
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
        // TODO
        String sort_by[] = {"relevancy", "popularity", "publishedAt"};
        search_sort_by.setItems(FXCollections.observableArrayList(sort_by));

        Image image = new Image("test.png", 20, 20, false, false);
        search_button.setGraphic(new ImageView(image));
    }
}

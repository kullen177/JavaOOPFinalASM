/*
RMIT University Vietnam
INTE2512 Final Project - News Aggregator App
Nguyen Anh Minh
S3911237
 */
package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class WebController implements Initializable {

    @FXML
    AnchorPane web_pain;
    @FXML
    VBox vbox;
    
    public void init(String url) throws Exception {
        WebView webview = new WebView();
        webview.getEngine().load(url);
        vbox.getChildren().add(webview);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}

package crisscrosscrass.Tasks;

import crisscrosscrass.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ReportWindow {

    public void MyReportWindow() throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ReportInterface.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initStyle(StageStyle.UNDECORATED);

        stage.setTitle("Report Window");
        URL LogoLocation = Main.class.getClassLoader().getResource("Images/VisualMeta.png");
        Image Logo = new Image(String.valueOf(LogoLocation));
        stage.getIcons().add(Logo);
        stage.setScene(new Scene(root));
        stage.show();

    }

}

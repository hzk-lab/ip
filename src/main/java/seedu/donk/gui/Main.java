package seedu.donk.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.donk.Donk;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Donk donk = new Donk("./data/donk.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/seedu/donk/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDonk(donk);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
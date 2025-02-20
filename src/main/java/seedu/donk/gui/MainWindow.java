package seedu.donk.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import seedu.donk.Donk;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Donk donk;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/seedu/donk/images/ak47.png"));
    private Image donkImage = new Image(this.getClass().getResourceAsStream("/seedu/donk/images/Donk.png"));


    @FXML
    public void initialize() {
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        showWelcomeMessage();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }


    public void setDonk(Donk d) {
        donk = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = donk.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDonkDialog(response, donkImage)
        );

        if (input.equals("bye")) {
            // Close the window after 2 second
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            System.exit(0);
                        }
                    },
                    1000
            );
        }
        userInput.clear();
    }

    private void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Donk 🤖\nWhat can I do for you today?";
        dialogContainer.getChildren().add(DialogBox.getDonkDialog(welcomeMessage, donkImage));
    }
}

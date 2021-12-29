package GUI.loading;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class LoadingScreen {
    public Thread timer;
    public void showLoading(Stage primaryStage) {
        FlowPane pane = new FlowPane();

        Image loading = new Image("Images/loading.png",
                200, 200, false, false);
        ImageView view = new ImageView(loading);
        view.setFitHeight(200);
        view.setFitWidth(200);


        pane.getChildren().add(view);
        pane.setPadding(new Insets(0,50,0,50));

        pane.setStyle("-fx-background-color: #ffa2ff");
        timer(view);
        primaryStage.setScene(new Scene(pane, 300, 200));
    }

    private void timer(ImageView loading) {
         timer = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(50);

                    Platform.runLater(() -> rotate(loading));
                }
            } catch (InterruptedException e) {
                System.out.println();
            }
        });

        timer.start();
    }

    private void rotate(ImageView loading) {
        loading.setRotate(loading.getRotate() + 5);
    }
}

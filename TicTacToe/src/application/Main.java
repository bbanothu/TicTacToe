package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import java.io.IOException;


public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PrimaryStage");
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 700, 200);
      
        Button localPlay = new Button("Play locally with a friend");
        Button aiPlay = new Button("Play against AI");
        Button onlinePlay = new Button("Play online");
        localPlay.setOnAction(eve-> new Local());
        aiPlay.setOnAction(eve-> new Ai());
        onlinePlay.setOnAction(eve-> {
			try {
				new online();
			} catch (IOException e) {
			}
		});
        root.getChildren().add(localPlay);
        root.getChildren().add(aiPlay);
        root.getChildren().add(onlinePlay);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

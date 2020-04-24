package application;
	

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;

import Server.Server;

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
        localPlay.setOnAction(eve-> new Local());
        
        Button aiPlay = new Button("Play against AI");
        aiPlay.setOnAction(eve-> new Ai());
        
        Button onlinePlay = new Button("Play online");
        onlinePlay.setOnAction(eve-> {
			try {
				new online();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
            
        root.getChildren().add(localPlay);
        root.getChildren().add(aiPlay);
        root.getChildren().add(onlinePlay);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

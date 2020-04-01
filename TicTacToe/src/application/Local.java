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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Local extends Stage {
	
	public ImageView topLeft;
	public ImageView topMid;
	public ImageView topRight;
	public ImageView middleLeft;
	public ImageView middleMid;
	public ImageView middleRight;
	public ImageView bottomLeft;
	public ImageView bottomMid;
	public ImageView bottomRight;
	
	public Button resetBoard;
	
	public Label turn;
	public Label xScore;
	public Label oScore;
	
	private GridPane grid;
	private TicTacToe game;
	
	private String xTurn = "X's turn!";
	private String oTurn = "O's turn!";
	
	
	Local() {		
		game = new TicTacToe();
		
		grid = new GridPane();
		grid.getColumnConstraints().add(new ColumnConstraints(100));
		grid.getColumnConstraints().add(new ColumnConstraints(100));
		grid.getColumnConstraints().add(new ColumnConstraints(100));
		grid.getColumnConstraints().add(new ColumnConstraints(100));
		grid.getColumnConstraints().add(new ColumnConstraints(100));
		grid.getColumnConstraints().add(new ColumnConstraints(100));
		grid.getColumnConstraints().add(new ColumnConstraints(100));
		grid.getRowConstraints().add(new RowConstraints(100));
		grid.getRowConstraints().add(new RowConstraints(100));
		grid.getRowConstraints().add(new RowConstraints(100));
		grid.getRowConstraints().add(new RowConstraints(100));
		grid.getRowConstraints().add(new RowConstraints(100));
		
		grid.setVgap(2);
		grid.setHgap(2);
		
		topLeft = new ImageView();
		topLeft.setImage(setBlank());
		topLeft.setFitHeight(100);
		topLeft.setFitWidth(100);
		
		topMid = new ImageView();
		topMid.setImage(setBlank());
		topMid.setFitHeight(100);
		topMid.setFitWidth(100);	
		
		topRight = new ImageView();
		topRight.setImage(setBlank());
		topRight.setFitHeight(100);
		topRight.setFitWidth(100);	
		
		middleLeft = new ImageView();
		middleLeft.setImage(setBlank());
		middleLeft.setFitHeight(100);
		middleLeft.setFitWidth(100);	

		middleMid = new ImageView();
		middleMid.setImage(setBlank());
		middleMid.setFitHeight(100);
		middleMid.setFitWidth(100);	
		
		middleRight = new ImageView();
		middleRight.setImage(setBlank());
		middleRight.setFitHeight(100);
		middleRight.setFitWidth(100);		
		
		bottomLeft = new ImageView();
		bottomLeft.setImage(setBlank());
		bottomLeft.setFitHeight(100);
		bottomLeft.setFitWidth(100);
		
		bottomMid = new ImageView();
		bottomMid.setImage(setBlank());
		bottomMid.setFitHeight(100);
		bottomMid.setFitWidth(100);		
		
		bottomRight = new ImageView();
		bottomRight.setImage(setBlank());
		bottomRight.setFitHeight(100);
		bottomRight.setFitWidth(100);
		
		eventHandlers();
		
		grid.add(topLeft, 2, 1);
		grid.add(topMid, 3, 1);
		grid.add(topRight, 4, 1);
		grid.add(middleLeft, 2, 2);
		grid.add(middleMid, 3, 2);
		grid.add(middleRight, 4, 2);
		grid.add(bottomLeft, 2, 3);
		grid.add(bottomMid, 3, 3);
		grid.add(bottomRight, 4, 3);
		
		turn = new Label();
		turn.setText(xTurn);
		turn.setMaxWidth(100);
		turn.setWrapText(true);
		turn.setAlignment(Pos.CENTER);
		grid.add(turn, 3, 0);
		
		Button printGameBoard = new Button("Print Board");
		printGameBoard.setMaxWidth(100);
		printGameBoard.setAlignment(Pos.CENTER);
		printGameBoard.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println(game.toString());
			}
		});
		printGameBoard.setVisible(false);
		grid.add(printGameBoard, 4, 4);
		
		resetBoard = new Button("Reset");
		resetBoard.setMaxWidth(100);
		resetBoard.setAlignment(Pos.CENTER);
		resetBoard.setVisible(false);
		resetBoard.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				game.init();
				turn.setText(xTurn);
				topLeft.setImage(setBlank());
				topMid.setImage(setBlank());
				topRight.setImage(setBlank());
				middleLeft.setImage(setBlank());
				middleMid.setImage(setBlank());
				middleRight.setImage(setBlank());
				bottomLeft.setImage(setBlank());
				bottomMid.setImage(setBlank());
				bottomRight.setImage(setBlank());
				resetBoard.setVisible(false);
			}
		});
		grid.add(resetBoard, 3, 4);
		grid.setGridLinesVisible(true);
		
		Scene scene = new Scene(grid, 700, 500);

		Stage subStage = new Stage();
		subStage.setTitle("Tic-Tac-Toe");
		subStage.setScene(scene);
		subStage.show();
	}
	
	/**
	 * Handles all the onMouseClicks for the ImageViews and buttons
	 */
	public void eventHandlers() {
		topLeft.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if(!game.getIsOver()) {
					if(game.isOpen(0, 0)) {
						if(game.getTurn() % 2 == 0) {
							//if even
							game.setTile(0, 0, "O");
							target.setImage(setO());
							turn.setText(xTurn);
						} else {
							//if odd
							game.setTile(0, 0, "X");
							target.setImage(setX());
							turn.setText(oTurn);
						}
						if(game.getIsOver()) {
							resetBoard.setVisible(true);
							turn.setText(game.getWinner());
						}
					}
				} else {
					//GAME IS OVER GET WINNER
					System.out.println("GAME OVER");
				}
			}
		});
		
		topMid.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (!game.getIsOver()) {
					if (game.isOpen(0, 1)) {
						if (game.getTurn() % 2 == 0) {
							// if even
							game.setTile(0, 1, "O");
							target.setImage(setO());
							turn.setText(xTurn);
						} else {
							// if odd
							game.setTile(0, 1, "X");
							target.setImage(setX());
							turn.setText(oTurn);
						}
						if(game.getIsOver()) {
							resetBoard.setVisible(true);
							turn.setText(game.getWinner());
						}
					}
				} else {
					//GAME IS OVER GET WINNER
					System.out.println("GAME OVER");
				}
			}
		});
		
		topRight.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (!game.getIsOver()) {
					if (game.isOpen(0, 2)) {
						if (game.getTurn() % 2 == 0) {
							// if even
							game.setTile(0, 2, "O");
							target.setImage(setO());
							turn.setText(xTurn);
						} else {
							// if odd
							game.setTile(0, 2, "X");
							target.setImage(setX());
							turn.setText(oTurn);
						}
						if(game.getIsOver()) {
							resetBoard.setVisible(true);
							turn.setText(game.getWinner());
						}
					}
				} else {
					//GAME IS OVER GET WINNER
					System.out.println("GAME OVER");
				}
			}
		});
		
		middleLeft.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (!game.getIsOver()) {
					if (game.isOpen(1, 0)) {
						if (game.getTurn() % 2 == 0) {
							// if even
							game.setTile(1, 0, "O");
							target.setImage(setO());
							turn.setText(xTurn);
						} else {
							// if odd
							game.setTile(1, 0, "X");
							target.setImage(setX());
							turn.setText(oTurn);
						}
						if(game.getIsOver()) {
							resetBoard.setVisible(true);
							turn.setText(game.getWinner());
						}
					}
				} else {
					//GAME IS OVER GET WINNER
					System.out.println("GAME OVER");
				}
			}
		});
		
		middleMid.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (!game.getIsOver()) {
					if (game.isOpen(1, 1)) {
						if (game.getTurn() % 2 == 0) {
							// if even
							game.setTile(1, 1, "O");
							target.setImage(setO());
							turn.setText(xTurn);
						} else {
							// if odd
							game.setTile(1, 1, "X");
							target.setImage(setX());
							turn.setText(oTurn);
						}
						if(game.getIsOver()) {
							resetBoard.setVisible(true);
							turn.setText(game.getWinner());
						}
					}
				} else {
					//GAME IS OVER GET WINNER
					System.out.println("GAME OVER");
				}
			}
		});
		
		middleRight.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (!game.getIsOver()) {
					if (game.isOpen(1, 2)) {
						if (game.getTurn() % 2 == 0) {
							// if even
							game.setTile(1, 2, "O");
							target.setImage(setO());
							turn.setText(xTurn);
						} else {
							// if odd
							game.setTile(1, 2, "X");
							target.setImage(setX());
							turn.setText(oTurn);
						}
						if(game.getIsOver()) {
							resetBoard.setVisible(true);
							turn.setText(game.getWinner());
						}
					}
				} else {
					//GAME IS OVER GET WINNER
					System.out.println("GAME OVER");
				}
			}
		});
		
		bottomLeft.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (!game.getIsOver()) {
					if (game.isOpen(2, 0)) {
						if (game.getTurn() % 2 == 0) {
							// if even
							game.setTile(2, 0, "O");
							target.setImage(setO());
							turn.setText(xTurn);
						} else {
							// if odd
							game.setTile(2, 0, "X");
							target.setImage(setX());
							turn.setText(oTurn);
						}
						if(game.getIsOver()) {
							resetBoard.setVisible(true);
							turn.setText(game.getWinner());
						}
					}
				} else {
					//GAME IS OVER GET WINNER
					System.out.println("GAME OVER");
				}
			}
		});
		
		bottomMid.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (!game.getIsOver()) {
					if (game.isOpen(2, 1)) {
						if (game.getTurn() % 2 == 0) {
							// if even
							game.setTile(2, 1, "O");
							target.setImage(setO());
							turn.setText(xTurn);
						} else {
							// if odd
							game.setTile(2, 1, "X");
							target.setImage(setX());
							turn.setText(oTurn);
						}
						if(game.getIsOver()) {
							resetBoard.setVisible(true);
							turn.setText(game.getWinner());
						}
					}
				} else {
					//GAME IS OVER GET WINNER
					System.out.println("GAME OVER");
				}
			}
		});
		
		bottomRight.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (!game.getIsOver()) {
					if (game.isOpen(2, 2)) {
						if (game.getTurn() % 2 == 0) {
							// if even
							game.setTile(2, 2, "O");
							target.setImage(setO());
							turn.setText(xTurn);
						} else {
							// if odd
							game.setTile(2, 2, "X");
							target.setImage(setX());
							turn.setText(oTurn);
						}
						if(game.getIsOver()) {
							resetBoard.setVisible(true);
							turn.setText(game.getWinner());
						}
					}
				} else {
					//GAME IS OVER GET WINNER
					System.out.println("GAME OVER");
				}
			}
		});
	}
	
	private Image setBlank() {
		return new Image("blank.jpg");
	}
	
	private Image setO() {
		return new Image("o.jpg");
	}
	
	private Image setX() {
		return new Image("x.jpg");
	}
	
}
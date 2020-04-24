package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class online extends Stage {
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

	// Online Specific Variables
	Socket newSocket;
	Client usr;
	Listerner incomingMessage;
	boolean myTurn = false;
	public String p1OrP2 = "";

	public HashMap<Pair<Integer, Integer>, ImageView> images = new HashMap<Pair<Integer, Integer>, ImageView>();

	public void initHashMap() {
		images.put(new Pair<Integer, Integer>(0, 0), topLeft);
		images.put(new Pair<Integer, Integer>(0, 1), topMid);
		images.put(new Pair<Integer, Integer>(0, 2), topRight);
		images.put(new Pair<Integer, Integer>(1, 0), middleLeft);
		images.put(new Pair<Integer, Integer>(1, 1), middleMid);
		images.put(new Pair<Integer, Integer>(1, 2), middleRight);
		images.put(new Pair<Integer, Integer>(2, 0), bottomLeft);
		images.put(new Pair<Integer, Integer>(2, 1), bottomMid);
		images.put(new Pair<Integer, Integer>(2, 2), bottomRight);
	}

	online() throws UnknownHostException, IOException {
		game = new TicTacToe();
		usr = new Client();
		usr.start();

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

		initHashMap();
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

		p1OrP2 = usr.getMessage();
		System.out.println(p1OrP2);

		if (p1OrP2.contentEquals("p2")) {
			myTurn = false;

			aiMove(usr.getMessage());
			p1OrP2 = "";
		} else {
			myTurn = true;
		}

		incomingMessage = new Listerner(usr.in, this);
		new Thread(incomingMessage).start();

	}

	/**
	 * Handles all the onMouseClicks for the ImageViews and buttons
	 */
	public void eventHandlers() {
		topLeft.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (myTurn) {
					if (!game.getIsOver()) {
						if (game.isOpen(0, 0)) {
							// if odd
							game.setTile(0, 0, "X");
							target.setImage(setX());
							turn.setText(xTurn);
							usr.sendMessage("0-0");
							myTurn = false;
							if (game.getIsOver()) {
								resetBoard.setVisible(true);
								turn.setText(game.getWinner());
							}
						}
					} else {
						System.out.println("GAME OVER");
					}
				} else {
					turn.setText("Not your Turn");
				}
			}
		});

		topMid.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (myTurn) {
					if (!game.getIsOver()) {
						if (game.isOpen(0, 1)) {
							// if odd
							game.setTile(0, 1, "X");
							target.setImage(setX());
							turn.setText(xTurn);
							usr.sendMessage("0-1");
							myTurn = false;
							if (game.getIsOver()) {
								resetBoard.setVisible(true);
								turn.setText(game.getWinner());
							}
						}
					} else {
						System.out.println("GAME OVER");
					}
				} else {
					turn.setText("Not your Turn");
				}
			}
		});

		topRight.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (myTurn) {
					if (!game.getIsOver()) {
						if (game.isOpen(0, 2)) {
							// if odd
							game.setTile(0, 2, "X");
							target.setImage(setX());
							turn.setText(xTurn);
							usr.sendMessage("0-2");
							myTurn = false;
							if (game.getIsOver()) {
								resetBoard.setVisible(true);
								turn.setText(game.getWinner());
							}
						}
					} else {
						System.out.println("GAME OVER");
					}
				} else {
					turn.setText("Not your Turn");
				}
			}
		});

		middleLeft.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (myTurn) {
					if (!game.getIsOver()) {
						if (game.isOpen(1, 0)) {
							// if odd
							game.setTile(1, 0, "X");
							target.setImage(setX());
							turn.setText(xTurn);
							usr.sendMessage("1-0");
							myTurn = false;
							if (game.getIsOver()) {
								resetBoard.setVisible(true);
								turn.setText(game.getWinner());
							}
						}
					} else {
						System.out.println("GAME OVER");
					}
				} else {
					turn.setText("Not your Turn");
				}
			}
		});

		middleMid.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (myTurn) {
					if (!game.getIsOver()) {
						if (game.isOpen(1, 1)) {
							// if odd
							game.setTile(1, 1, "X");
							target.setImage(setX());
							turn.setText(xTurn);
							usr.sendMessage("1-1");
							myTurn = false;
							if (game.getIsOver()) {
								resetBoard.setVisible(true);
								turn.setText(game.getWinner());
							}
						}
					} else {
						System.out.println("GAME OVER");
					}
				} else {
					turn.setText("Not your Turn");
				}
			}
		});

		middleRight.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (myTurn) {
					if (!game.getIsOver()) {
						if (game.isOpen(1, 2)) {
							// if odd
							game.setTile(1, 2, "X");
							target.setImage(setX());
							turn.setText(xTurn);
							usr.sendMessage("1-2");
							myTurn = false;
							if (game.getIsOver()) {
								resetBoard.setVisible(true);
								turn.setText(game.getWinner());
							}
						}
					} else {
						System.out.println("GAME OVER");
					}
				} else {
					turn.setText("Not your Turn");
				}
			}
		});

		bottomLeft.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (myTurn) {
					if (!game.getIsOver()) {
						if (game.isOpen(2, 0)) {
							// if odd
							game.setTile(2, 0, "X");
							target.setImage(setX());
							turn.setText(xTurn);
							usr.sendMessage("2-0");
							myTurn = false;
							if (game.getIsOver()) {
								resetBoard.setVisible(true);
								turn.setText(game.getWinner());
							}
						}
					} else {
						System.out.println("GAME OVER");
					}
				} else {
					turn.setText("Not your Turn");
				}
			}
		});

		bottomMid.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (myTurn) {
					if (!game.getIsOver()) {
						if (game.isOpen(2, 1)) {
							// if odd
							game.setTile(2, 1, "X");
							target.setImage(setX());
							turn.setText(xTurn);
							usr.sendMessage("2-1");
							myTurn = false;
							if (game.getIsOver()) {
								resetBoard.setVisible(true);
								turn.setText(game.getWinner());
							}
						}
					} else {
						System.out.println("GAME OVER");
					}
				} else {
					turn.setText("Not your Turn");
				}
			}
		});

		bottomRight.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ImageView target = (ImageView) event.getTarget();
				if (myTurn) {
					if (!game.getIsOver()) {
						if (game.isOpen(2, 2)) {
							// if odd
							game.setTile(2, 2, "X");
							target.setImage(setX());
							turn.setText(xTurn);
							usr.sendMessage("2-2");
							myTurn = false;
							if (game.getIsOver()) {
								resetBoard.setVisible(true);
								turn.setText(game.getWinner());
							}
						}
					} else {
						System.out.println("GAME OVER");
					}
				} else {
					turn.setText("Not your Turn");
				}
			}
		});
	}

	public void aiMove(String move) {
		if (!game.getIsOver()) {
			myTurn = true;
			move = move.substring(move.length() - 3);
			int x = Integer.parseInt(move.substring(0, move.indexOf("-")));
			int y = Integer.parseInt(move.substring(move.indexOf("-") + 1));
			game.setTile(x, y, "O");
			Pair<Integer, Integer> temp = new Pair<Integer, Integer>(x, y);
			ImageView target = (ImageView) images.get(new Pair<Integer, Integer>(temp.getKey(), temp.getValue()));
			target.setImage(setO());
			//turn.setText(xTurn);
		}
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

	class Listerner implements Runnable {
		private BufferedReader in;
		online myUI;

		public Listerner(BufferedReader in, online myUI) {
			this.in = in;
			this.myUI = myUI;
		}

		@Override
		public void run() {
			while (true) {
				String message = "";
				try {
					message = in.readLine();
					myUI.aiMove(message);
					System.out.println(message + "in Listener");
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	class Client {
		private PrintWriter out;
		public BufferedReader in;
		private String hostName = "localhost";
		private Socket conn;

		public Client() {
		}

		public void start() {
			try {
				conn = new Socket(hostName, 1234);
				setup();
			} catch (IOException e) {
			}
		}

		private void setup() throws IOException {
			out = new PrintWriter(conn.getOutputStream());
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}

		private void sendMessage(String msg) {
			out.println(msg);
			out.write(msg);
			out.flush();
		}

		protected String getMessage() throws IOException {
			String message = "";
			message = in.readLine();
			System.out.println(message);
			return message;
		}

	}
}
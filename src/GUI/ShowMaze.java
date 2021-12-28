package GUI;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import logic.Process;

public class ShowMaze {
    public void show(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        for (int i = 0; i < Process.width + 2; i++) {
            for (int j = 0; j < Process.height + 2; j++) {
                gridPane.add(makeCell(i, j), i, j);
            }
        }

        primaryStage.setScene(new Scene(gridPane, 700, 800));
    }

    public GridPane makeCell(int superI, int superJ) {
        GridPane pane = new GridPane();
        Rectangle rectangle;
        String direction = Process.board[superI][superJ];

        if (direction.equals("0")) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i % 2 == 0 && j == 1) {
                        rectangle = new Rectangle(4, 33, Color.BLACK);
                        rectangle.setStroke(Color.WHITE);
                        pane.add(rectangle, i, j);
                    } else if (j % 2 == 0 && i == 1) {
                        rectangle = new Rectangle(33, 4, Color.BLACK);
                        rectangle.setStroke(Color.WHITE);
                        pane.add(rectangle, i, j);
                    } else if (j % 2 == 1) {
                        rectangle = new Rectangle(33, 33, Color.BLACK);
                        rectangle.setStroke(Color.WHITE);
                        pane.add(rectangle, i, j);
                    }
                }
            }
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i % 2 == 0 && j == 1) {
                        rectangle = new Rectangle(4, 33, Color.BLUE);
                        rectangle.setStroke(Color.WHITE);
                        pane.add(rectangle, i, j);
                    } else if (j % 2 == 0 && i == 1) {
                        rectangle = new Rectangle(33, 4, Color.BLUE);
                        rectangle.setStroke(Color.WHITE);
                        pane.add(rectangle, i, j);
                    } else if (j % 2 == 1) {
                        rectangle = new Rectangle(33, 33, Color.WHITE);
                        rectangle.setStroke(Color.WHITE);
                        pane.add(rectangle, i, j);
                    }
                }
            }

            if (direction.contains("2")) {
                pane.add(new Rectangle(4, 33, Color.WHITE), 0, 1);
            }

            if (direction.contains("3")) {
                pane.add(new Rectangle(33, 4, Color.WHITE), 1, 2);
            }


            if (direction.contains("4")) {
                pane.add(new Rectangle(4, 33, Color.WHITE), 2, 1);
            }


            if (direction.contains("5")) {
                pane.add(new Rectangle(33, 4, Color.WHITE), 1, 0);
            }
        }

        check(superI, superJ, pane);

        return pane;
    }

    public void check(int i, int j, GridPane pane) {
        if (i > 0 && i < Process.width) {
            if (j > 0 && j < Process.height) {
                if (Process.board[i + 1][j].contains("2")) {
                    pane.add(new Rectangle(4, 33, Color.WHITE), 2, 1);
                }

                if (Process.board[i][j - 1].contains("3")) {
                    pane.add(new Rectangle(33, 4, Color.WHITE), 1, 0);
                }

                if (Process.board[i - 1][j].contains("4")) {
                    pane.add(new Rectangle(4, 33, Color.WHITE), 0, 1);
                }

                if (Process.board[i][j + 1].contains("5")) {
                    pane.add(new Rectangle(33, 4, Color.WHITE), 1, 2);
                }
            }
        }
    }
}
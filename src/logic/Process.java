package logic;

import GUI.ShowMaze;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Process {
    public static int height, width;
    public static String[][] board;
    public static ArrayList<Point> seen;
    public static ArrayList<Point> remaining;
    public static ArrayList<Integer> selectDirection = new ArrayList<>();


    public static void process(Stage primaryStage, int height, int width) {
        Process.height = height;
        Process.width = width;
        createBoards();

        int randInt=new Random().nextInt((height*width));

        seen.add(remaining.get(randInt));
        remaining.remove(randInt);
        board[seen.get(0).i][seen.get(0).j]="1";
        while (!remaining.isEmpty()){
            searchResult();
            selectDirection.clear();
        }


        // print board
        for (int i = 0; i < width+2; i++) {
            for (int j = 0; j < height+2; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }

        new ShowMaze().show(primaryStage);
    }
    public static void createBoards(){
        board = new String[width+2][height+2];
        remaining=new ArrayList<>(width*height);
        seen=new ArrayList<>(width*height);

        for (int i = 0; i < width+2; i++) {
            for (int j = 0; j < height+2; j++) {
                if (i*j==0 || i==width+1 || j==height+1){
                    board[i][j]="0";
                }else {
                    remaining.add(new Point(i,j));
                    board[i][j]="-1";
                }
            }
        }
    }

    public static void  searchResult(){
        int randInt = new Random().nextInt(seen.size());

        Point point= seen.get(randInt);
        checkDirection(point.i ,point.j);
        if (!selectDirection.isEmpty()){

            randInt=new Random().nextInt(selectDirection.size());
            if ( board[point.i][point.j]=="1")
                 board[point.i][point.j]=String.valueOf(selectDirection.get(randInt));
            else
                board[point.i][point.j]+=","+selectDirection.get(randInt);

            addPoint(selectDirection.get(randInt),point.i ,point.j);
        }


    }
    public static void  addPoint(int direction , int i , int j){
        switch (direction){
            case 2:
                for (Point point: remaining) {
                    if (point.i==i-1 && point.j==j){
                        seen.add(point);
                        remaining.remove(point);
                        board[i-1][j]="1";
                        break;
                    }
                }
                break;
            case 3:
                for (Point point: remaining) {
                    if (point.i==i && point.j==j+1){
                        seen.add(point);
                        remaining.remove(point);
                        board[i][j+1]="1";

                        break;
                    }
                }
                break;
            case 4:
                for (Point point: remaining) {
                    if (point.i==i+1 && point.j==j){
                        seen.add(point);
                        remaining.remove(point);
                        board[i+1][j]="1";

                        break;
                    }
                }
                break;
            case 5:
                for (Point point: remaining) {
                    if (point.i==i && point.j==j-1){
                        seen.add(point);
                        remaining.remove(point);
                        board[i][j-1]="1";
                        break;
                    }
                }
                break;
        }
    }
    public static  void  checkDirection(int i , int j){
        if (board[i-1][j]=="-1")
            selectDirection.add(2);
        if (board[i][j+1]=="-1")
            selectDirection.add(3);
        if (board[i+1][j]=="-1")
            selectDirection.add(4);
        if (board[i][j-1]=="-1")
            selectDirection.add(5);

    }
}


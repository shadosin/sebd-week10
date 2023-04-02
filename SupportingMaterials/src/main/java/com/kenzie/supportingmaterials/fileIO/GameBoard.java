package com.kenzie.supportingmaterials.fileIO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

class GameBoard {
    static int rows = 3;
    static int columns = 3;
    static char[][] board = new char[rows][columns];

    public GameBoard() {

    }

    public void readBoard(String boardFile) throws IOException {
        // TODO read file line by line
        //      each line in the file is a row on the game board
        //      insert into the board variable
    Path file = Path.of(boardFile);
    String bigString = Files.readString(file);

    String[] lineArray = bigString.split("\n");

    for(int rowIndex=0; rowIndex < rows; rowIndex++){
        String currentLine = lineArray[rowIndex];
        String [] cells = currentLine.split(",");
        for(int colIndex = 0; colIndex < columns; colIndex++){
            board[rowIndex][colIndex] = cells[colIndex].charAt(0);
        }
    }

        System.out.println("done!");
    }

    public void printBoard() {
        // TODO read the board variable line by line
        //      print each line separated by a new line
        for(int i = 0; i < board.length; i++){
            for(int j=0; j < board[i].length; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
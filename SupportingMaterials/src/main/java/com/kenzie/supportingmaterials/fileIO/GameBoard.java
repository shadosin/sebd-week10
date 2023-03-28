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


        System.out.println("done!");
    }

    public void printBoard() {
        // TODO read the board variable line by line
        //      print each line separated by a new line

    }
}
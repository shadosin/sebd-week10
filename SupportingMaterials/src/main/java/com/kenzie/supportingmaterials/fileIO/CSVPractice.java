package com.kenzie.supportingmaterials.fileIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVPractice {

    public void practiceOne() throws IOException {
        Path filePath = Path.of("input.csv");
        System.out.println(Files.readString(filePath));

        // TODO call readEachLine
    }

    public static void readEachLine(String csvContent){
        // Split the content line by line
        // String[] csvLines = ... ;

        // Get each of the column names from the header (first line)
        // String[] headers = ... ;

        // Loop through each line of the CSV file, starting AFTER the header at index 1
        // for (int i = 1; i < csvLines.length; i++) {
            // Get a line of data
            // String dataItem = ... ;

            // Turn the string into an array
            // String[] dataArray = ... ;

            // Print the column name next to its value
            // We are using j, the "column" index to get the elements in the same place from the header
            // for(int j = 0; j < headers.length; j++){
                // System.out.println(headers[j] + ": " + dataArray[j]);
            // }

            // Print a blank line after printing the whole data item
            // System.out.println("");
    }

    public static void practiceTwo() throws IOException {
        // Don't change this, do your work in GameBoard
        GameBoard board = new GameBoard();
        board.readBoard("SupportingMaterials/src/main/java/com/kenzie/supportingmaterials/fileIO/savedBoard.txt");
        board.printBoard();
    }

    public static void practiceThree() throws IOException {

        final String fileName = "SupportingMaterials/src/main/java/com/kenzie/supportingmaterials/fileIO/famousDuos.txt";

        // A multidimensional arraylist will be easier, since we don't know how long the file is
        ArrayList<ArrayList<String>> twoDimensionalList = new ArrayList<>();

        // Open the file and read the contents into a String using Files and Path
        Path file = Path.of(fileName);
        String fileString = Files.readString(file);

        // Split the entire file into lines
        String[] lineArray = fileString.split("\n");

        // Skippy for loop to grab pairs!
        for(int i = 1; i < lineArray.length; i += 2) {
            String first = lineArray[i];
            String second = lineArray[i - 1];
            twoDimensionalList.add(new ArrayList<>(List.of(first, second)));
        }

        twoDimensionalArrayPrint(twoDimensionalList);
    }

    public static void twoDimensionalArrayPrint(ArrayList<ArrayList<String>> list){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            ArrayList<String> row = list.get(i);
            for(int j = 0; j < row.size(); j++) {
                String column = row.get(j);
                result.append(column);
            }
            result.append("\n");
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
    }
}

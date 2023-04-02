package com.kenzie.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;


/*
Instructions -

This program reads Lottery Winning Numbers from a CSV file
The user can view the winning lottery number for a specific date,
or, the user can see all winning lottery numbers for a year

Valid years are 2010 to 2022. If the user enters an invalid year, print
"Invalid year - must be between 2010 and 2022"

Valid format is 12/31/2011 (month, date, year).

Complete all TODOs within the file. Do not change any methods without a TODO
 */

public class Application {
    static String INPUT_FILENAME = "Lottery_Winning_Numbers.csv";

    public static String[] readCSVLines(String filename) throws IOException {
        // TODO read in the file
        //  Each line will be a row in the array
        //  Remove the header row (skip the first line in the CSV file)
        Path path = Paths.get(filename);
        String filePath = Files.readString(path).replace("\r", "");

        String[] lotteryWins = filePath.split("\n");
        String[] noHead = Arrays.copyOfRange(lotteryWins, 1, lotteryWins.length);


        return noHead;
    }

    public static String[][] saveToMultidimensionalArray(String[] csvContent){

        // TODO
        //  Create a multidimensional array to store your data
        //      The rows will be the number of CSV lines
        //      The columns will be each value in the line (separated by a comma)
        //  Use a nested loop to loop through each line of the CSV
        //     Add the line to the array
        //  Return the multidimensional array
        //  HINT: if the row is missing data (doesn't have three values), add a null to the empty spot
        String[][] data = new String[csvContent.length][3];
        for (int i = 0; i < csvContent.length; i++) {
            String line = csvContent[i];
            String[] values = line.split(",");

            for (int j = 0; j < values.length; j++) {
                if (values[j].isEmpty()) {
                    data[i][j] = null; // Replace empty value with null
                } else {
                    data[i][j] = values[j];
                }
            }
        }

        return data;

    }

    // Gathers the year the user wants to view data from
    public static String getYearToSelectFromData(Scanner scanner, String[][] lottoData) throws InvalidYearException{
        System.out.print("Enter a year after 2010 to view winning numbers - ");
        String year = scanner.nextLine();

        // TODO throw custom exception
        //  InvalidYearException (the custom exception you made)
        //  Check whether the user entered a valid year (between 2010 and 2022)
        //  If they entered an invalid year, throw an InvalidYearException with the message -
        //  "Invalid year - must be between 2010 and 2022"
        if(Integer.parseInt(year) < 2010 || Integer.parseInt(year) > 2022){
            throw new InvalidYearException("Invalid year - must be between 2010 and 2022");
        }
        // TODO return all data from the selected year
        //  You can use a for loop on each line of the csv
        //      - See if the first cell contains the year
        //      - Add it to your result
        StringBuilder result = new StringBuilder();
        for(String[]row:lottoData){
            if(row[0].endsWith(year)){
               result.append(Arrays.toString(row)).append("\n");
            }
        }
        return result.toString();
    }

    // Creates a date object based on user input
    // Will throw an exception if the user doesn't enter the data in the correct format
    public static String getDayToSelectFromData(Scanner scanner, String[][] lottoData) throws ParseException, IllegalArgumentException {
        System.out.print("Enter a specific date to view winning number - ");
        String userDateInput = scanner.nextLine();

        // TODO add the exception to the method declaration
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = dateFormat.parse(userDateInput);

        for(String[] row : lottoData) {
            // TODO add the exception to the method declaration
            if(dateFormat.parse(row[0]).equals(date)) {
                return Arrays.toString(row);
            }
        }

        throw new IllegalArgumentException("Date not found in lotto data - " + userDateInput);
    }

    // Don't change this method
    public static void main(String[] args) throws IOException {
        String[] csvContent = readCSVLines(INPUT_FILENAME);
        String[][] lottoData = saveToMultidimensionalArray(csvContent);

        Scanner scanner = new Scanner(System.in);
        String keepGoing = "yes";

        while(keepGoing.equalsIgnoreCase("yes")) {
            System.out.println("Would you like view a year or specific day? (year/day)");
            String yearOrDay = scanner.nextLine();

            try {

                if(yearOrDay.equalsIgnoreCase("year")) {
                    System.out.println(getYearToSelectFromData(scanner, lottoData));
                } else if(yearOrDay.equalsIgnoreCase("day")) {
                    System.out.println(getDayToSelectFromData(scanner, lottoData));
                } else {
                    throw new IllegalArgumentException("Invalid Input - (year/day)");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("\n\nWould you like to read more data? (yes/no)");
                keepGoing = scanner.nextLine();
            }

        }

        System.out.println("Thanks for playing!");
    }
}
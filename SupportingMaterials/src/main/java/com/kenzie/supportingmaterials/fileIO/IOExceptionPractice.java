package com.kenzie.supportingmaterials.fileIO;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class IOExceptionPractice {
    final static String INPUT_FILENAME = "SupportingMaterials/src/main/java/com/kenzie/supportingmaterials/fileIO/input.txt";

    final static Path FILE_PATH = Path.of(INPUT_FILENAME);

    public static void inputPractice() {
        // TODO write code to read the contents of the input file
        //      print the contents
        //      Call this code in the main method

        // TODO next, update the contents of the file to say
        //      "I found the file and made a change!"
        //      print the contents
    }

    public static void outputPractice() {

        // TODO - write Hello World to a file named output.txt
        //      this file should be located in the same directory
        //      as this class
    }

    public static void streamPractice() throws IOException {

        // TODO - read the contents of multilineInput.txt using streams
        //      convert to upper case and print

    }

    public static void headerPractice() throws Exception {
        // TODO Read in the file line-by-line
        //      disregard the header
        //      copy the rest of the lines of the `menu_order_items.txt` file into an `ArrayList`.
        //      print when complete!
    }

    public static void writeStreamPractice() throws Exception {
        final String OUTPUT_FILE = "weekly_schedule.txt";
        ArrayList<String> weeklyScheduleList = new ArrayList<>(Arrays.asList(
                "Monday-Turn in assignments",
                "Tuesday-Kickoff call",
                "Wednesday-Attend class",
                "Thursday-Study Hall"
        ));

        // TODO Add code to write the contents of the ArrayList weeklyScheduleList
        //      to the weekly_schedule.txt file
    }

    public static void main(String[] args) throws Exception {
        // Call your methods here
    }
}

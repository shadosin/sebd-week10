package com.kenzie.supportingmaterials.fileIO;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class IOExceptionPractice {
    final static String INPUT_FILENAME = "I found and made the change.";

    final static Path FILE_PATH = Path.of(INPUT_FILENAME);

    public static void inputPractice() throws IOException {
        // TODO write code to read the contents of the input file
        //      print the contents
        //      Call this code in the main method
        String fileContents = Files.readString(FILE_PATH);
        System.out.println(fileContents);

        // TODO next, update the contents of the file to say
        //      "I found the file and made a change!"
        //      print the contents
    }

    public static void outputPractice() throws IOException {

        // TODO - write Hello World to a file named output.txt
        //      this file should be located in the same directory
        //      as this class
        String filename = "SupportingMaterials/src/main/java/com/kenzie/supportingmaterials/fileIO/output.txt";
        String content = "Hello World!";

        Path filePath = Path.of(filename);
        Files.writeString(filePath, content);
    }

    public static void streamPractice() throws IOException {

        // TODO - read the contents of multilineInput.txt using streams
        //      convert to upper case and print
        String fileName = "SupportingMaterials/src/main/java/com/kenzie/supportingmaterials/fileIO/multilineInput.txt";

        FileInputStream inputStream = new FileInputStream(fileName);
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader buffReader = new BufferedReader(inputReader);

        String line = buffReader.readLine();

        while(line != null){
            System.out.println(line.toUpperCase());
            line = buffReader.readLine();
        }

    inputStream.close();
    }

    public static void headerPractice() throws Exception {
        // TODO Read in the file line-by-line
        //      disregard the header
        //      copy the rest of the lines of the `menu_order_items.txt` file into an `ArrayList`.
        //      print when complete!
        String fileName = "SupportingMaterials/src/main/java/com/kenzie/supportingmaterials/fileIO/withHeader.txt";
        ArrayList<String> menuList = new ArrayList<>();
        String menuLine;
        String header;

        FileInputStream inputStream = new FileInputStream(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        header = bufferedReader.readLine();

        while((menuLine = bufferedReader.readLine()) != null){
            menuList.add(menuLine);
        }
        System.out.println(menuList);

        inputStream.close();
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
        FileWriter fileWriter = new FileWriter(OUTPUT_FILE);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);

        for(int index = 0; index < weeklyScheduleList.size(); index++){
            bWriter.write(weeklyScheduleList.get(index));
            bWriter.write("\n");
        }

        bWriter.close();
    }

    public static void main(String[] args) throws Exception {
        // Call your methods here
        outputPractice();
        streamPractice();
        headerPractice();
    }
}

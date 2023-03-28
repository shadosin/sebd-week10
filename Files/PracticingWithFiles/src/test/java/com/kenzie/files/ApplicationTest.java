package com.kenzie.files;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationTest {
    private static final String FILE = "src/test/java/com/kenzie/files/Lottery_Winning_Numbers_TEST.csv";

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    private String[] learnerCSVLines;
    private String[][] learnerContentArray;
    private String[] testFileContents;
    private String[][] testContentArray;

    private final int TIMEOUT = 10;

    @BeforeAll
    public void setUpStreams() throws IOException {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @BeforeEach
    public void setTestInput() throws IOException {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        learnerCSVLines = Application.readCSVLines(FILE);
        learnerContentArray = Application.saveToMultidimensionalArray(learnerCSVLines);

        testFileContents = Files.readAllLines(Paths.get(FILE), StandardCharsets.UTF_8).toArray(new String[0]);
        testFileContents = Arrays.copyOfRange(testFileContents, 1, testFileContents.length);

        testContentArray = Arrays.stream(testFileContents).map(l -> l.split(",")).toArray(String[][]::new);

    }

    @Order(1)
    @Test
    public void testReadCSVFromFile() throws IOException {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT), () -> {
            assertEquals(testFileContents.length, learnerCSVLines.length,
                    "Number of lines in CSV doesn't match expected");

            for (int i = 0; i < testFileContents.length; i++) {

                String learnerLine = learnerCSVLines[i];
                String testLine = testFileContents[i];

                assertTrue(testLine.equals(learnerLine),
                        "One line doesn't match between test and the file reader - \n" +
                                "Your line - " + learnerLine + "\n" +
                                "Test line - " + testLine + "\n");
            }
        });

    }

    @Order(2)
    @Test
    public void testCreateMultidimensionalArray() throws IOException {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT), () -> {

            for (int i = 0; i < testContentArray.length; i++) {
                String[] testLine = testContentArray[i];
                String[] learnerLine = learnerContentArray[i];

                if (testLine.length > 2) {
                    assertEquals(Arrays.toString(testLine), Arrays.toString(learnerLine), "Line doesn't match");
                }
            }
        });

    }

    @Order(3)
    @Test
    public void testGoodYear_1() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT), () -> {

            runMainWithInput("year\n2010\n");

            String all2010s = Arrays.stream(testContentArray)
                    .filter(c -> c[0].contains("2010"))
                    .map(Arrays::toString).collect(Collectors.joining("\n"));

            assertThat(outContent.toString(), containsString(all2010s));
        });

    }

    @Order(4)
    @Test
    public void testGoodYear_2() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT), () -> {

            runMainWithInput("year\n2011\n");

            String all2011s = Arrays.stream(testContentArray)
                    .filter(c -> c[0].contains("2011"))
                    .map(Arrays::toString).collect(Collectors.joining("\n"));

            assertThat(outContent.toString(), containsString(all2011s));
        });

    }

    @Order(5)
    @Test
    public void testBadDay_1() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT), () -> {

            runMainWithInput("day\n12-31-2022\n");
            assertThat(outContent.toString(), containsString("Unparseable date: \"12-31-2022\""));
        });

    }

    @Order(6)
    @Test
    public void testGoodDay_1() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT), () -> {

            runMainWithInput("day\n12/12/2018\n");
            assertThat(outContent.toString(), containsString("[12/12/2018, 04 09 21 29 64 26, 2]"));
        });

    }

    @Order(7)
    @Test
    public void testGoodDay_2() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT), () -> {

            runMainWithInput("day\n06/13/2018\n");
            assertThat(outContent.toString(), containsString("[06/13/2018, 13 20 38 45 55 01, 2]"));
        });

    }

    @Order(8)
    @Test
    public void testRunMainBadWeekInput_Word() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT), () -> {

            runMainWithInput("Oranges\nyes\nyear\n2014\n");
            assertThat(outContent.toString(), containsString("Invalid Input - (year/day)"));
            assertThat(outContent.toString(), containsString("Would you like view a year or specific day? (year/day)"));

            List<String> all2014s = Arrays.stream(testContentArray)
                    .filter(c -> c[0].contains("2014"))
                    .map(Arrays::toString)
                    .map(l -> l.replaceAll("\\[", "").replaceAll("\\]", ""))
                    .collect(Collectors.toList());

            for (String line : all2014s) {
                assertThat(outContent.toString(), containsString(line));
            }

        });

    }

    @Order(9)
    @Test
    public void testRunBadYear() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT), () -> {
            runMainWithInput("year\n1901");
            assertThat(outContent.toString(), containsString("Invalid year - must be between 2010 and 2022"));
        });
    }

    @Order(10)
    @Test
    public void testRunBadDateFormat() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TIMEOUT), () -> {
            runMainWithInput("day\n2022-100-13");
            assertThat(outContent.toString(), containsString("Unparseable date: \"2022-100-13\""));
        });
    }


    private static void runMainWithInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try {
            Application.INPUT_FILENAME = FILE;
            Application.main(new String[]{});
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
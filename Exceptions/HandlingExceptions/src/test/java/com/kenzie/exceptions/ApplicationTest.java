package com.kenzie.exceptions;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.lang.reflect.Method;
import java.time.Duration;


// DO NOT attempt to run these tests until you have resolved the compiler issues!
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationTest {

    private final int TestTimeOut = 10;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @BeforeEach
    public void setTestInput() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Order(1)
    @DisplayName("Test: isInputValid handles valid input")
    @Test
    public void testIsInputValid_ValidInput() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TestTimeOut), () -> {
            try {
                int inputNumber = getRandomNumber(1, 3);
                assertTrue(Application.isInputValid(inputNumber), "Double check that your isInputValid method returns true when a number between 1-3 is entered.");
            }
            catch(Exception e) {
                assertFalse(true, "Something went wrong while running your test, See Exception thrown:" + e.getMessage());
            }
        });
    }

    @Order(2)
    @DisplayName("Test: isInputValid throws IllegalArgumentException for numbers too large")
    @Test
    public void testIsInputValid_InvalidInput() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TestTimeOut), () -> {
            try {
                int inputNumber = getRandomNumber(4, 500);
                assertThrows(IllegalArgumentException.class, () -> {
                    Application.isInputValid(inputNumber);
                }, "When a number is entered that is not between 1-3 a IllegalArgumentException should be thrown.");
            }
            catch(Exception e) {
                assertFalse(true, "Something went wrong while running your test, See Exception thrown:" + e.getMessage());
            }
        });
    }

    @Order(3)
    @DisplayName("Test: isInputValid throws IllegalArgumentException for negative numbers")
    @Test
    public void testIsInputValid_InvalidNegativeInput(){
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TestTimeOut), () -> {
            try {
                int inputNumber = getRandomNumber(-40, -1);
                assertThrows(IllegalArgumentException.class, () -> {
                    Application.isInputValid(inputNumber);
                });
            }
            catch(Exception e) {
                assertFalse(true, "Something went wrong while running your test, See Exception thrown:" + e.getMessage());
            }
        });
    }

    @Order(4)
    @DisplayName("Test: convertToString handles valid input")
    @Test
    public void testConvertToString_ValidInput() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TestTimeOut), () -> {
            try {
                Integer randomInput = getRandomNumber(1, 3);
                String input = randomInput.toString();
                assertEquals(randomInput, Application.convertStringInput(input), "When a valid number between 1-3 is entered we should be able to convert the String input to an int output.");
            }
            catch(Exception e) {
                assertFalse(true, "Something went wrong while running your test, See Exception thrown:" + e.getMessage());
            }
        });
    }

    @Order(5)
    @DisplayName("Test: convertToString throws NumberFormatException")
    @Test
    public void testConvertToString_InvalidInput() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TestTimeOut), () -> {
            try {
                Integer randomInput = getRandomNumber(1, 3);
                String input = randomInput.toString() + "apples";
                assertThrows(NumberFormatException.class, () -> {
                    Application.convertStringInput(input);
                },"When a non-numerical value is passed into your convertStringInput method a NumberFormatException should be thrown");
            }
            catch(Exception e) {
                assertFalse(true, "Something went wrong while running your test, See Exception thrown:" + e.getMessage());
            }
        });
    }

    @Order(6)
    @DisplayName("Test: checkForEmptyString throws CustomEmptyStringException")
    @Test
    public void testCustomException() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TestTimeOut), () -> {
            try {
                String input = "";
                assertThrows(CustomEmptyStringException.class, () -> {
                    Application.checkForEmptyString(input);
                }, "When a empty string is entered we should throw a CustomEmptyStringException but the correct Exception type was not thrown");
            }
            catch(Exception e) {
                assertFalse(true, "Something went wrong while running your test, See Exception thrown:" + e.getMessage());
            }
        });
    }

    @Order(5)
    @Test
    public void runMain_incorrectInput_OutOfBoundsInput_PrintsCorrectErrorMessage_Test(){
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TestTimeOut), () -> {
            try {
                Integer randomOutOfBoundsNumber = getRandomNumber(4,100);

                runMainWithInput(String.valueOf(randomOutOfBoundsNumber) + "\n");

                String output = outContent.toString();

                assertThat(output, containsString("Number must be between 1 and 3") );
            }
            catch(Exception e) {
                assertFalse(true, "Something went wrong while running your test, See Exception thrown:" + e.getMessage());
            }
        });
    }
    @Order(6)
    @Test
    public void runMain_incorrectInput_EmptyStringInput_PrintsCorrectErrorMessage_Test(){
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TestTimeOut), () -> {
            try {
                runMainWithInput("\n");

                String output = outContent.toString();

                assertThat(output, containsString("Please try again"));
            }
            catch(Exception e) {
                assertFalse(true, "Something went wrong while running your test, See Exception thrown:" + e.getMessage());
            }
        });
    }

    @Order(7)
    @Test
    public void runMain_incorrectInput_NonNumericInput_PrintsCorrectErrorMessage_Test(){
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TestTimeOut), () -> {
            try {
                runMainWithInput("This format is not correct\n");

                String output = outContent.toString();

                assertThat(output, containsString("Non-numeric string was entered. Please try again"));
            }
            catch(Exception e) {
                assertFalse(true, "Something went wrong while running your test, See Exception thrown:" + e.getMessage());
            }
        });
    }

    private static void runMainWithInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try {
            Application.main(new String[]{});
        }
        catch(Exception e){

        }
    }
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public class PreemptiveTimeoutInvocationInterceptor implements InvocationInterceptor {
        @Override
        public void interceptTestMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) {
            Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), invocation::proceed);
        }
    }

}
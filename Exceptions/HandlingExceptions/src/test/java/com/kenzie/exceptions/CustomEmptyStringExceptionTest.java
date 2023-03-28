package com.kenzie.exceptions;

import org.junit.jupiter.api.*;

import java.lang.reflect.Constructor;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import java.lang.reflect.Method;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomEmptyStringExceptionTest {

    private static final int TestTimeOut = 10;

    @SuppressWarnings("unchecked")
    public static CustomEmptyStringException getCustomException() {
        try {
            @SuppressWarnings("unchecked")
            Constructor constructor = CustomEmptyStringException.class.getConstructor(String.class);
            @SuppressWarnings("unchecked")
            CustomEmptyStringException exception = (CustomEmptyStringException) constructor.newInstance("test message");
            return exception;
        }
        catch(Exception e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Order(1)
    @SuppressWarnings("unchecked")
    @DisplayName("Test: Can CustomEmptyStringException be instantiated with one argument")
    @Test
    void canCreateCustomEmptyStringException() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TestTimeOut), () -> {
            try {
                try {
                    @SuppressWarnings("unchecked")
                    Constructor bookConstructor = CustomEmptyStringException.class.getConstructor(String.class);
                    @SuppressWarnings("unchecked")

                    CustomEmptyStringException exception = (CustomEmptyStringException) getCustomException();

                }
                catch(Exception e){
                    fail("Missing or incorrectly defined constructor: " + e.getMessage());
                }
            }
            catch(Exception e) {
                assertFalse(true, "Something went wrong while running your test, See Exception thrown:" + e.getMessage());
            }
        });
    }

    @DisplayName("Test: CustomEmptyStringException uses super")
    @Test
    void doesCustomEmptyStringExceptionUsesSuper() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TestTimeOut), () -> {
            try {
                try {
                    @SuppressWarnings("unchecked")
                    Constructor bookConstructor = CustomEmptyStringException.class.getConstructor(String.class);
                    @SuppressWarnings("unchecked")

                    CustomEmptyStringException exception = (CustomEmptyStringException) getCustomException();

                    assertEquals("test message", exception.getMessage(), "Be sure to use super in conjunction with your parameter");
                }
                catch(Exception e){
                    fail("Missing or incorrectly defined constructor: " + e.getMessage());
                }
            }
            catch(Exception e) {
                assertFalse(true, "Something went wrong while running your test, See Exception thrown:" + e.getMessage());
            }
        });
    }

    @Order(2)
    @Test
    void customEmptyStringExceptionClass_Extends_RuntimeExceptionClass_Test() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(TestTimeOut), () -> {
            try {
                assertTrue(doesChildClassExtendParentClass(CustomEmptyStringException.class, RuntimeException.class), "The CustomEmptyStringException class should extend the RunTimeException class but does not");
            }
            catch(Exception e) {
                assertFalse(true, "Something went wrong while running your test, See Exception thrown:" + e.getMessage());
            }
        });
    }

    private <T> T getClassToConstruct(Object[] params, Class<?>[] paramTypes, Class<?> classToConstruct) throws Exception {

        if(params == null || paramTypes == null || classToConstruct == null) {
            throw new Exception("params, paramTypes, and classToConstruct parameters must be provided to use this method.");
        }
        else {
            Constructor constructor = classToConstruct.getConstructor(paramTypes);
            return (T) constructor.newInstance(params);
        }
    }

    private boolean doesChildClassExtendParentClass(Class<?> childClass, Class<?> parentClassToCheck) throws NoSuchElementException {
        try {
            if(childClass == null || parentClassToCheck == null) {
                throw new Exception("You need to provide a parent and child class to check if a child class extends a given parent class.");
            }

            if(childClass.getSuperclass() == parentClassToCheck) {
                return true;
            }
        }
        catch(Exception e){
            throw new NoSuchElementException(e.getMessage());
        }

        return false;
    }

    public class PreemptiveTimeoutInvocationInterceptor implements InvocationInterceptor {
        @Override
        public void interceptTestMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) {
            Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), invocation::proceed);
        }
    }
}
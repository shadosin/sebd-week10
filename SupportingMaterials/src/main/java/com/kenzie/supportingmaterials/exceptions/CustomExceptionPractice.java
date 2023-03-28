package com.kenzie.supportingmaterials.exceptions;

public class CustomExceptionPractice {

    public static int doMath() {
      try {
          int a = 5;
          int b = 0;
          int c;

          // TODO Write a try/catch around this line

          c = a / b;

          return c;
      }catch(Exception e){
          return 0;
      }
    }

    public static void makeDeposit(double amount) {
        if (amount >= 0 ){
            System.out.println("Deposit complete.");
        } else {
            throw new NegativeAmountException("Negative dollar amount entered:[ " + amount + "]");
            // TODO Create a custom exception in a different file
            //      Throw that exception here
        }
    }

    public static void main(String[] args) {

        /* Use for makeDeposit()*/
        try {
            // Change this value to test
            makeDeposit(-1.00);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }


        /* Use for doMath()*/
        doMath();

    }
}

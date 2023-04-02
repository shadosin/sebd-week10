package com.kenzie.supportingmaterials.multidimensionalarrays;

public class ArrayPractice {
    public static void practiceOne() {
        // TODO Declare an int array with the following values
        // 1 100 1000
        // 4 200 2000
        // 9 300 3000
        // 16 400 4000
        // 25 500 5000
        int[][] threeDimensions = {
                {1, 100, 1000},
                {4, 200, 2000},
                {9, 300, 3000},
                {16, 400, 4000},
                {25, 500, 5000}
        };

        // TODO Write a nested loop that loops through and prints out the 2D array with a space in between each value. Include a newline "\n" after each row.
        for(int i = 0; i < threeDimensions.length; i++){
            for(int j = 0; j < threeDimensions[i].length; j++){
                System.out.print(threeDimensions[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }

        // TODO Write a nested loop that loops through and only prints out the line that starts with the value 9
        for(int i = 0; i < threeDimensions.length; i++){
            for(int j=0; j< threeDimensions[i].length; j++){
                if(threeDimensions[i][0]==9){
                    System.out.print(threeDimensions[i][j]);
                    System.out.print(" ");
                }
            }
        }
        System.out.print("\n");
    }

    public static void practiceTwo() {
        // TODO Declare a multi-dimensional String array with the following values
        // mammal cat dog pig
        // insect bee fly butterfly
        // reptile snake lizard alligator
        String[][] animals = {
                {"mammal", "cat", "dog", "pig"},
                {"insect", "bee", "fly", "butterfly"},
                {"reptile", "snake", "lizard", "alligator"}
        };

        // TODO Write a nested loop that loops through and prints out the 2D array. Include a newline "\n" after each row.
        for(int i = 0; i < animals.length; i++){
            for(int j=0; j < animals[i].length; j++){
                System.out.print(animals[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        // TODO Write a nested loop that loops through and only prints out the line that starts with "insect"
        for(int i=0; i< animals.length;i++){
            for(int j=0; j< animals[i].length; j++){
                if(animals[i][0].equals("insect")){
                    System.out.print(animals[i][j]);
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }

    }
}
